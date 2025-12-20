package com.kotlin.outboxstarter.scheduler.outbox

import com.kotlin.outboxstarter.config.OutboxSchedulerProperties
import com.kotlin.outboxstarter.kafka.producer.KafkaProducer
import com.kotlin.outboxstarter.model.MessageStatus
import com.kotlin.outboxstarter.model.OutboxMessage
import com.kotlin.outboxstarter.service.OutboxMessageService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class OutboxScheduler(
    private val service: OutboxMessageService,
    private val properties: OutboxSchedulerProperties,
    private val producer: KafkaProducer,
) {
    @Scheduled(cron = "#{@outboxSchedulerCron}", zone = "Europe/Moscow")
    @Transactional
    fun publish() {
        val messages = service.findMessagesWithBatchByStatus(properties.batch, MessageStatus.CREATED)

        messages.forEach { message ->

            val clazz = Class.forName(message.type)
            val payloadObject = OutboxMessage.deserialize(message.payload, clazz)

            producer.send(
                topic = message.topic,
                key = message.key!!,
                payload = payloadObject,
            )

            message.status = MessageStatus.PROCEED
        }
    }
}
