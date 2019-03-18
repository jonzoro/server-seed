package com.company.project.common

import com.company.project.base.CustomizeException
import org.apache.poi.hssf.usermodel.*
import org.apache.poi.hssf.util.HSSFColor
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.util.CellRangeAddress
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletResponse

object ExcelUtil {

    /***
     * 前端下载Excel文件
     */
    fun <T : Any> responseExcel(
        dataList: List<T>,
        sumData: Map<String, Any?>?,
        title: String,
        headers: Array<String>,
        response: HttpServletResponse
    ) {
        val workbook = HSSFWorkbook()
        // 设置导出的文件名
        val fileName = buildFileName(title)
        // 写入数据
        if (null == sumData || sumData.isEmpty()) {
            buildSimpleSheet(workbook, dataList, headers)
        } else {
            buildMultiPartSheet(workbook, dataList, sumData, headers)
        }
        // 响应回前端，弹出下载提示
        response.contentType = "application/octet-stream"
        response.setHeader("Content-disposition", "attachment;filename=$fileName")
        response.setHeader("filename", fileName)
        response.flushBuffer()
        workbook.write(response.outputStream)
    }

    /***
     * 保存Excel文件
     */
    fun <T : Any> saveExcel(
        dataList: List<T>,
        sumData: Map<String, Any?>?,
        title: String,
        headers: Array<String>
    ): String {
        val workbook = HSSFWorkbook()
        // 设置导出的文件名
        val fileName = buildFileName(title)
        // 写入数据
        if (null == sumData || sumData.isEmpty()) {
            buildSimpleSheet(workbook, dataList, headers)
        } else {
            buildMultiPartSheet(workbook, dataList, sumData, headers)
        }
        // 保存文件到临时目录
        val directory = File("")
        val filePath = directory.absolutePath + File.separator + "excel" + File.separator + fileName
        val file = File(filePath)
        if (!file.parentFile.exists()) {
            if (!file.parentFile.mkdirs()) {
                throw CustomizeException("创建excel目录失败！")
            }
        }
        val out = FileOutputStream(filePath)
        workbook.write(out)
        out.close()
        return filePath
    }

    /***
     * 单页数据表+表头带样式
     */
    private fun <T : Any> buildSimpleSheet(workbook: HSSFWorkbook, dataList: List<T>, headers: Array<String>) {
        val sheet = workbook.createSheet()
        // 添加表头
        val headRow = sheet.createRow(0)
        buildHeadRow(headRow, sheet, workbook, headers)
        // 新增数据行，并且设置单元格数据，在表中存放查询到的数据放入对应的列
        buildDataContent(1, dataList, sheet)
    }

    /***
     * 单页数据表+第一行为汇总数据+表头带样式
     */
    private fun <T : Any> buildMultiPartSheet(
        workbook: HSSFWorkbook,
        dataList: List<T>,
        sumData: Map<String, Any?>,
        headers: Array<String>
    ) {
        val sheet = workbook.createSheet()
        // 添加汇总信息
        if (!sumData.isEmpty()) {
            val sumRow = sheet.createRow(0)
            // 写入汇总信息
            val sumContent = StringBuilder()
            sumData.forEach {
                sumContent.append(it.key + ": " + it.value + " ")
            }
            val cell = sumRow.createCell(0)
            val text = HSSFRichTextString(sumContent.toString())
            cell.setCellValue(text)
            // 添加样式
            val style = workbook.createCellStyle()
            style.verticalAlignment = HSSFCellStyle.VERTICAL_CENTER
            cell.setCellStyle(style)
            // 合并单元格
            sheet.addMergedRegion(CellRangeAddress(0, 1, 0, headers.size - 1))
        }
        // 添加表头
        val headRow = sheet.createRow(2)
        buildHeadRow(headRow, sheet, workbook, headers)
        // 新增数据行，并且设置单元格数据，在表中存放查询到的数据放入对应的列
        buildDataContent(3, dataList, sheet)
    }

    private fun buildFileName(title: String): String {
        var titleDefault = title
        if (title.isEmpty()) {
            titleDefault = SimpleDateFormat("yyyyMMddHHmmss").format(Date()).toString()
        }
        return "$titleDefault.xls"
    }

    private fun buildHeadRow(headRow: HSSFRow, sheet: HSSFSheet, workbook: HSSFWorkbook, headers: Array<String>) {
        // 表头样式
        val style = workbook.createCellStyle()
        style.fillPattern = HSSFCellStyle.SOLID_FOREGROUND
        style.fillForegroundColor = HSSFColor.GREY_25_PERCENT.index
        style.borderTop = CellStyle.BORDER_THIN
        style.topBorderColor = IndexedColors.BLACK.index
        style.borderBottom = CellStyle.BORDER_THIN
        style.bottomBorderColor = IndexedColors.BLACK.index
        style.borderLeft = CellStyle.BORDER_THIN
        style.leftBorderColor = IndexedColors.BLACK.index
        style.borderRight = CellStyle.BORDER_THIN
        style.rightBorderColor = IndexedColors.BLACK.index

        for (i in headers.indices) {
            // 写入值
            val cell = headRow.createCell(i)
            val text = HSSFRichTextString(headers[i])
            cell.setCellValue(text)
            // 添加样式
            sheet.setColumnWidth(i, 8000)
            cell.setCellStyle(style)
        }
    }

    private fun <T : Any> buildDataContent(startRowNum: Int, dataList: List<T>, sheet: HSSFSheet) {
        var rowNum = startRowNum
        for (data in dataList) {
            val fields = data.javaClass.declaredFields
            val dataRow = sheet.createRow(rowNum)
            if (data is Map<*, *>) {
                for ((index, value) in data.values.withIndex()) {
                    dataRow.createCell(index).setCellValue(value.toString())
                }
            } else {
                for ((index, field) in fields.withIndex()) {
                    field.isAccessible = true
                    if (field.name == "serialVersionUID") {
                        continue
                    } else if (null != field && field.type.isInstance(Date())) {
                        // 时间格式化
                        val rawData = field.get(data)
                        val fmtDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rawData).toString()
                        dataRow.createCell(index).setCellValue(fmtDate)
                    } else if (null != field.getAnnotation(DictType::class.java)) {
                        // 数据字典翻译
                        val rawData = field.get(data).toString()
                        val dictType = field.getAnnotation(DictType::class.java).type
                        val fmtData = SysDictUtil.findDict(dictType, rawData)
                        dataRow.createCell(index).setCellValue(fmtData)
                    } else if (null != field.get(data)) {
                        dataRow.createCell(index).setCellValue(field.get(data).toString())
                    } else {
                        continue
                    }
                }
            }
            rowNum++
        }
    }
}

/***
 * 字段名注解
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class DictType(val type: String = "")