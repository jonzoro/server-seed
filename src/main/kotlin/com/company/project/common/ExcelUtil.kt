package com.company.project.common

import org.apache.poi.hssf.usermodel.HSSFRichTextString
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletResponse

object ExcelUtil{

    fun<T : Any> responseExcel(classmateList: List<T>, title:String?, headers: Array<String>, response: HttpServletResponse){
        var title1 = title
        if(title1==null || "".equals(title1)){
           title1= SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date()).toString()
        }
        val workbook = HSSFWorkbook()
        val sheet = workbook.createSheet("信息表")
        val fileName = title1+ ".xls"//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据
        var rowNum = 1
        val row = sheet.createRow(0)
        //在excel表中添加表头
        for (i in headers.indices) {
            val cell = row.createCell(i)
            val text = HSSFRichTextString(headers[i])
            cell.setCellValue(text)
        }
        //在表中存放查询到的数据放入对应的列
        for (sd in classmateList) {
            val fields = sd.javaClass.declaredFields
            val num = headers.size-1
            val row1 = sheet.createRow(rowNum)
            for(index in 0..num){
                fields[index].setAccessible(true);
                if(!fields[index].getName().equals("serialVersionUID")){
                    row1.createCell(index).setCellValue(fields[index].get(sd).toString())
                }

            }
            rowNum++
        }
        response.contentType = "application/octet-stream"
        response.setHeader("Content-disposition", "attachment;filename=$fileName")
        response.flushBuffer()
        workbook.write(response.outputStream)
    }
}