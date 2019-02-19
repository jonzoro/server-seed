package com.company.project.base

import com.company.project.service.ISysDictTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component


/**
 * @program: server-seed
 *
 * @description: 项目启动事件监听类
 *
 * @author: Jonz
 *
 * @create: 2019-01-03 16:58
 **/
@Component
class ApplicationStartedEventListener : ApplicationListener<ApplicationStartedEvent> {

    var log = loggerFor(this.javaClass)

    @Autowired
    lateinit var sysDictTypeService: ISysDictTypeService

    override fun onApplicationEvent(event: ApplicationStartedEvent) {
        log.debug("application started .....")
        //取出所有字典类型及其数据项
        sysDictTypeService.refreshCache()
    }
}
