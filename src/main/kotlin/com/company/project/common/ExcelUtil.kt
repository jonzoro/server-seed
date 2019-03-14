package com.company.project.common

import org.apache.poi.hssf.usermodel.HSSFRichTextString
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletResponse

object ExcelUtil {

    fun <T : Any> responseExcel(dataList: List<T>, title: String?, headers: Array<String>, response: HttpServletResponse) {
        // 设置要导出的文件的名字
        var titleDefault = title
        if (title == null || "" == title) {
            titleDefault = SimpleDateFormat("yyyyMMddHHmmss").format(Date()).toString()
        }
        val workbook = HSSFWorkbook()
        val sheet = workbook.createSheet("信息表")
        val fileName = "$titleDefault.xls"

        // 在Excel表中添加表头
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
            rowNum++
        }

        // 响应回前端，弹出下载提示
        response.contentType = "application/octet-stream"
        response.setHeader("Content-disposition", "attachment;filename=$fileName")
        response.flushBuffer()
        workbook.write(response.outputStream)
    }

    fun <T : Any> saveExcelFile(dataList: List<T>, title: String?, headers: Array<String>): String {
        // 设置要导出的文件的名字
        var titleDefault = title
        if (title == null || "" == title) {
            titleDefault = SimpleDateFormat("yyyyMMddHHmmss").format(Date()).toString()
        }
        val workbook = HSSFWorkbook()
        val sheet = workbook.createSheet("信息表")
        val fileName = "$titleDefault.xls"

        // 在Excel表中添加表头
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
            rowNum++
        }

        // 保存文件到临时目录
        val directory = File("")
        val filePath = directory.absolutePath+ File.separator+"excel"+ File.separator+fileName
        val file = File(filePath)
        if (!file.parentFile.exists()) {
            if (!file.parentFile.mkdirs()) {
                throw Exception("创建excel目录失败！")
            }
        }
        val out = FileOutputStream(filePath)
        workbook.write(out)
        out.close()
        return filePath
    }
}