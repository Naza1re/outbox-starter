package com.kotlin.outboxstarter.service

import com.kotlin.outboxstarter.kafka.KafkaObject

interface OutboxMessageService {
    fun <T : Any> send(obj: KafkaObject<T>, topic: String)
    fun <T : Any> send(key: String?, obj: KafkaObject<T>, topic: String)
}