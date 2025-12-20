package com.kotlin.outboxstarter.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
@Configuration
@ConfigurationProperties("outbox.cleaner")
class OutboxCleanerProperties {
    var cron: String = "*/10 * * * * *"
    var batch: Int = 5

    @Bean
    fun outboxSchedulerCleanerCron() : String {
        return cron
    }
}