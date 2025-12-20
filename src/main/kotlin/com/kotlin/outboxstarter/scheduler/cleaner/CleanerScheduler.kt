package com.kotlin.outboxstarter.scheduler.cleaner

import com.kotlin.outboxstarter.config.OutboxCleanerProperties
import com.kotlin.outboxstarter.model.MessageStatus
import com.kotlin.outboxstarter.service.OutboxMessageService
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@ConditionalOnProperty(
    prefix = "outbox.cleaner",
    name = ["enable"],
    havingValue = "true",
    matchIfMissing = false
)
@Component
class CleanerScheduler(
    private val outboxMessageService: OutboxMessageService,
    private val outboxMessageCleanerProperties: OutboxCleanerProperties
) {

    @Scheduled(cron = "#{@outboxSchedulerCleanerCron}", zone = "Europe/Moscow")
    @Transactional
    fun publish() {
        val messages = outboxMessageService.findMessagesWithBatchByStatus(
            outboxMessageCleanerProperties.batch,
            MessageStatus.PROCEED
        )

        outboxMessageService.deleteMessages(messages)
    }
}