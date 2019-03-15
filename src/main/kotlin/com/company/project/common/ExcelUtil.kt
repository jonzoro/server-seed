package com.company.project.common

import com.company.project.base.CustomizeException
import org.apache.poi.hssf.usermodel.HSSFRichTextString
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletResponse

object ExcelUtil {

    fun <T : Any> responseExcel(dataList: List<T>, title: String?, headers: Array<String>, response: HttpServletResponse) {
        val workbook = HSSFWorkbook()
        val sheet = workbook.createSheet()
        // 设置导出的文件名
        val fileName = buildFileName(title)
        // 写入数据
        buildSingleSheet(sheet, dataList, headers)

        // 响应回前端，弹出下载提示
        response.contentType = "application/octet-stream"
        response.setHeader("Content-disposition", "attachment;filename=$fileName")
        response.flushBuffer()
        workbook.write(response.outputStream)
    }

    fun <T : Any> saveExcelFile(dataList: List<T>, title: String?, headers: Array<String>): String {
        val workbook = HSSFWorkbook()
        val sheet = workbook.createSheet()
        // 设置导出的文件名
        val fileName = buildFileName(title)
        // 写入数据
        buildSingleSheet(sheet, dataList, headers)

        // 保存文件到临时目录
        val directory = File("")
        val filePath = directory.absolutePath+File.separator+"excel"+File.separator+fileName
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

    private fun buildFileName(title: String?): String {
        var titleDefault = title
        if (title == null || "" == title) {
            titleDefault = SimpleDateFormat("yyyyMMddHHmmss").format(Date()).toString()
        }
        return "$titleDefault.xls"
    }

    private fun <T: Any> buildSingleSheet(sheet: HSSFSheet, dataList: List<T>, headers: Array<String>) {
        // 添加表头
        val headRow = sheet.createRow(0)
        for (i in headers.indices) {
            val cell = headRow.createCell(i)
            val text = HSSFRichTextString(headers[i])
            cell.setCellValue(text)
        }

        // 新增数据行，并且设置单元格数据，在表中存放查询到的数据放入对应的列
        var rowNum = 1
        for (data in dataList) {
            val fields = data.javaClass.declaredFields
            val num = headers.size - 1
            val dataRow = sheet.createRow(rowNum)
            if(data is Map<*, *>) {
                for ((index, value) in data.values.withIndex()) {
                    dataRow.createCell(index).setCellValue(value.toString())
                }
            } else {
                for (index in 0..num) {
                    fields[index].isAccessible = true
                    if (fields[index].name == "serialVersionUID") {
                        continue
                    } else if (null != fields[index] && fields[index].type.isInstance(Date())) {
                        val rawData = fields[index].get(data)
                        val fmtDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rawData).toString()
                        dataRow.createCell(index).setCellValue(fmtDate)
                    } else {
                        dataRow.createCell(index).setCellValue(fields[index].get(data).toString())
                    }
                }
            }
            rowNum++
        }
    }
}