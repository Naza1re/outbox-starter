package com.kotlin.outboxstarter.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
@Configuration
@ConfigurationProperties("app.scheduler")
class SchedulerProperties {
    var cron: String = "*/10 * * * * *"

    @Bean
    fun outboxSchedulerCron() : String {
        return cron
    }
}