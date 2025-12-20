package com.kotlin.outboxstarter.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.kotlin.outboxstarter.kafka.KafkaObject
import com.kotlin.outboxstarter.model.MessageStatus
import com.kotlin.outboxstarter.model.OutboxMessage
import com.kotlin.outboxstarter.repository.OutboxMessageRepository
import com.kotlin.outboxstarter.service.OutboxMessageService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OutboxMessageServiceImpl(
    private val repository: OutboxMessageRepository,
) : OutboxMessageService {
    @Transactional
    override fun <T : Any> send(obj: KafkaObject<T>, topic: String) {
        send(null, obj, topic)
    }

    @Transactional
    override fun <T : Any> send(key: String?, obj: KafkaObject<T>, topic: String) {
        val jsonPayload = OutboxMessage.serialize(obj.value())
        val typeObObject = obj.value()::class.qualifiedName!!
        val outboxMessage = OutboxMessage(
            topic = topic,
            key = key,
            payload = jsonPayload,
            status = MessageStatus.CREATED,
            type = typeObObject,
        )
        repository.save(outboxMessage)
    }
}
