package com.company.project.common

import com.alibaba.fastjson.annotation.JSONField
import com.github.pagehelper.Page


/**
 * 分页类
 * @author Jonz
 *
 * @param <T>
 */

class PageInfo<T>(
        /**
         * 保存查询的结果集合
         */
        var list: List<T>? = null,
        /**
         * 总记录数
         */
        @JSONField(name="total_record")
        var totalRecord: Int = 0,
        /**
         * 页面显示的数目
         */
        @JSONField(name="page_size")
        var pageSize: Int = 10,
        /**
         * 总页码数
         */
        @JSONField(name="total_page")
        var totalPage: Int = 1,
        /**
         * 当前页码
         */
        @JSONField(name="current_page")
        var currentPage: Int = 1
) {
    val previousPage: Int
        get() = if (currentPage - 1 < 1) 1 else currentPage - 1


    val nextPage: Int
        get() = if (currentPage + 1 >= totalPage) totalPage else currentPage + 1

    var startIndex: Int = 0
        get() = (currentPage - 1) * pageSize


    //从数据库中获取的结束索引，供页面使用
    //不包含最后一条记录-1
    var endIndex: Int = 0
        get() {
            var end = startIndex + pageSize
            if (end > totalRecord) {
                end = startIndex + totalRecord % pageSize
            }
            return end
        }


    val pageBar: List<Int>            //条目数
        get() {
            var startPage: Int      //记住页码的起始位置
            var endPage: Int        //记住页码的结束位置，因为页码条目是既定的，由startpage，endpage两个变量通过循环就可以得全部页码
            if (totalPage <= PAGE_ITEM_COUNT) {    //当总页码不足或等于既定页面大小时
                startPage = 1
                endPage = totalPage
            } else {                    //当总页码大于既定页面大小时
                startPage = currentPage - (PAGE_ITEM_COUNT / 2 - 1)    //为了保证当前页在中间
                endPage = currentPage + PAGE_ITEM_COUNT / 2

                if (startPage < 1) {
                    startPage = 1
                    endPage = PAGE_ITEM_COUNT
                }

                if (endPage > totalPage) {
                    endPage = totalPage
                    startPage = totalPage - (PAGE_ITEM_COUNT - 1)
                }
            }
            return startPage.rangeTo(endPage).toList()
        }


    companion object {
        const val PAGE_ITEM_COUNT = 10  //显示页码条目数，即页码数量顶多是10个
    }

}

fun <T> Page<T>.getPageInfo(): PageInfo<T> {
    return PageInfo(this, total.toInt(), pageSize, pages, pageNum)
}

fun <T> List<T>.getPageInfo(pageSize: Int, pageNum: Int, total: Int): PageInfo<T> {
    return PageInfo(this, total, pageSize, pageNum)
}
