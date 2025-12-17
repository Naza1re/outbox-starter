package com.kotlin.outboxstarter.kafka.objects

import com.kotlin.outboxstarter.kafka.KafkaObject

class ExampleObject(
    private val id: Int,
    private val name: String,
    private val surname: String
) : KafkaObject<ExampleObject> {
    override fun key(): String {
        return id.toString()
    }

    override fun value(): ExampleObject {
        return this
    }
}