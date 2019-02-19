package com.company.project.schedule

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*


@Component
class ScheduledTasks {
    val logger = LoggerFactory.getLogger(javaClass)

    @Scheduled(cron = "0 0/5 * * * *")
    fun testScheduled() {
        logger.info(SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date()))
    }

}
