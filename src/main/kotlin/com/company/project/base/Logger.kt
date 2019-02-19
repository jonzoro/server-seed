package com.company.project.base

import com.company.project.MyApplication
import org.slf4j.LoggerFactory

/**
 * Created by Jonz on 2018/12/4.
 */
fun <T> loggerFor(clazz: Class<T>) = LoggerFactory.getLogger(clazz)

val logger = LoggerFactory.getLogger(MyApplication::class.java)
    
