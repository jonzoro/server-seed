package com.company.project.common

import com.alibaba.fastjson.JSON
import com.company.project.base.loggerFor
import okhttp3.FormBody
import okhttp3.Request
import okhttp3.RequestBody
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.net.URLEncoder

@Component
@ConfigurationProperties(prefix = "push")
class PushUtil {

    @Value("\${push.sms.url}")
    lateinit var smsUrl: String
    @Value("\${push.sms.corp_id}")
    lateinit var smsCorpId: String
    @Value("\${push.sms.corp_pwd}")
    lateinit var smsCorpPwd: String
    @Value("\${push.sms.corp_service}")
    lateinit var smsCorpService: String
    @Value("\${push.sms.ext}")
    lateinit var smsExt: String


    @Value("\${push.jpush.url}")
    lateinit var jpushUrl: String
    @Value("\${push.jpush.key}")
    lateinit var jpushKey: String
    @Value("\${push.jpush.secret}")
    lateinit var jpushSecret: String

    private val log = loggerFor(this.javaClass)

    fun sendSMS(mobile: String, content: String) {

        val body = with(FormBody.Builder()) {

            add("corp_id", smsCorpId)
            add("corp_pwd", smsCorpPwd)
            add("corp_service", smsCorpService)
            add("corp_msg_id", "")
            add("ext", smsExt)
            add("mobile", mobile)

            addEncoded("msg_content", URLEncoder.encode(content, "GBK"))
            this.build()
        }

        val request = Request.Builder().url(smsUrl).post(body).build()
        val response = HttpUtil.getRawResponse(request)
        val result = response.body().string()

        val code = result.split("#")[1]
        log.debug("send SMS content:$content to $mobile, response:$code")
    }

    fun sendPush(alias: String, title: String, content: String, extras: Map<String, String>) {

        val android = mapOf(
                "alert" to title,
                "title" to content,
                "builder_id" to 1,
                "intent" to mapOf("url" to "intent:#Intent;component=com.jiguang.push/com.example.jpushdemo.SettingActivity;end"),
                "extras" to extras.toMap()
        )
        val ios = mapOf(
                "alert" to mapOf(
                        "title" to title,
                        "subTitle" to content
                ),
                "sound" to "default",
                "badge" to "+1",
                "thread-id" to "default",
                "extras" to extras.toMap()
        )
        val message = mapOf(
                "msg_content" to title,
                "content_type" to "text",
                "title" to content,
                "extras" to extras.toMap()
        )
        val body = mutableMapOf(
                "platform" to "all",
                "audience" to mapOf("alias" to arrayListOf(alias)),
                "notification" to mapOf(
                        "android" to android,
                        "ios" to ios
                ),
                "message" to message
        )

        val jsonBody = JSON.toJSONString(body)
        val request = Request.Builder()
                .url(jpushUrl)
                .addHeader("Authorization", "Basic ${MessageDigestUtil.base64("$jpushKey:$jpushSecret")}")
                .post(RequestBody.create(JSON_TYPE, jsonBody))
                .build()
        val jsonObject = HttpUtil.sendRequest(request)
        log.debug("jpush end - ${jsonObject.toJSONString()}")

    }
}


