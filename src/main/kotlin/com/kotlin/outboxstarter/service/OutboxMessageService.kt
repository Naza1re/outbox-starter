package com.kotlin.outboxstarter.service

import com.kotlin.outboxstarter.kafka.KafkaObject
import com.kotlin.outboxstarter.model.MessageStatus
import com.kotlin.outboxstarter.model.OutboxMessage

interface OutboxMessageService {
    fun <T : Any> send(obj: KafkaObject<T>, topic: String)
    fun <T : Any> send(key: String?, obj: KafkaObject<T>, topic: String)
    fun findMessagesWithBatchByStatus(batch: Int, status: MessageStatus): List<OutboxMessage>
    fun deleteMessages(messages: List<OutboxMessage>)
}