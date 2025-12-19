package com.kotlin.outboxstarter.kafka

interface KafkaObject<T : Any> {
    fun key(): String
    fun value(): T
}
