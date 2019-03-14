package com.company.project.common

object SysDictUtil {

    var sysDict: MutableMap<String, List<Map<String, String?>>> = mutableMapOf()

    fun mapOf(vararg keys: String): MutableMap<String, List<Map<String, String?>>> {
        val result = mutableMapOf<String, List<Map<String, String?>>>()
        keys.forEach { sysDict[it]?.apply { result[it] = this } }
        return result
    }

    fun initDict(sysDictTypes: List<Map<String, String>>) {
        val dicMap = sysDictTypes.groupBy { it["code"] }

        dicMap.forEach { code, list ->
            code?.apply {
                sysDict[code] = list.map {
                    mapOf(
                            "value" to it["value"],
                            "showVal" to it["show_val"]
                    )
                }
            }
        }
    }

    fun findType(typeCode: String, itemCode: String): String? {
        sysDict[typeCode]?.apply {
            for (map in this) {
                if (map["value"] == itemCode) {
                    return map["showVal"]
                }
            }
        }
        return null
    }

    /***
     * 查询数据字典（不存在时返回传入值）
     */
    fun findDict(typeCode: String, itemCode: String): String {
        val type = sysDict[typeCode]
        var result = itemCode
        if (null != type) {
            for (item in type) {
                if (itemCode == item["value"]) {
                    result = item["showVal"].toString()
                    break
                }
            }
        }
        return result
    }
}
