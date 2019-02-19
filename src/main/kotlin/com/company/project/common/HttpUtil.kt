package com.company.project.common

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import okhttp3.*
import java.io.IOException
import kotlin.collections.set


val client: OkHttpClient = OkHttpClient()
val JSON_TYPE: MediaType = MediaType.parse("application/json; charset=utf-8")

/***
 * 静态类-http请求工具
 */
object HttpUtil {
    // 官方示例
    fun get(url: String): String {
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()
        return response.body().string()
    }

    fun post(url: String, json: String): String {
        val body = RequestBody.create(JSON_TYPE, json)
        val request = Request.Builder().url(url).post(body).build()
        val response = client.newCall(request).execute()
        return response.body().string()
    }

    // 二次封装
    fun postRequest(url: String, params: Map<String, Any>): Request {
        val bodyBuilder = FormBody.Builder()
        params.forEach {
            bodyBuilder.add(it.key, it.value.toString())
        }
        val body = bodyBuilder.build()
        return Request.Builder().url(url).post(body).build()
    }

    fun getRequest(url: String, params: Map<String, Any>): Request {
        val urlBuilder = StringBuilder(url).append("?")
        params.forEach {
            urlBuilder.append(it.key).append("=").append(it.value.toString()).append("&")
        }
        return Request.Builder().url(urlBuilder.substring(0, urlBuilder.length - 1)).get().build()
    }

    fun sendRequest(request: Request): JSONObject {
        val response = client.newCall(request).execute()
        val result = response.body().string()
        return JSON.parseObject(result)
    }

    fun getRawResponse(request: Request): Response {
        return client.newCall(request).execute()
    }

    // 带回调函数
    fun sendRequest(request: Request, callback: Callback) {
        client.newCall(request).enqueue(callback)
    }
}

interface JsonCallback : Callback {

    override fun onFailure(call: Call, e: IOException) {
        println("ERROR")
    }

    override fun onResponse(call: Call, response: Response) {
        println("SUCCESS")
    }

}
