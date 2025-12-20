package com.kotlin.outboxstarter.scheduler
import com.kotlin.outboxstarter.kafka.producer.KafkaProducer
import com.kotlin.outboxstarter.model.MessageStatus
import com.kotlin.outboxstarter.model.OutboxMessage
import com.kotlin.outboxstarter.repository.OutboxMessageRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class OutboxScheduler(
    private val repository: OutboxMessageRepository,
    private val producer: KafkaProducer,
) {

    @Scheduled(cron = "#{@outboxSchedulerCron}", zone = "Europe/Moscow")
    @Transactional
    fun publish() {
        val messages = repository.findBatchForUpdate(MessageStatus.CREATED)

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
