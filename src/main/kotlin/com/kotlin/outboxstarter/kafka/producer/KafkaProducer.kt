package com.kotlin.outboxstarter.kafka.producer
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {
    fun send(topic: String, key: String, payload: Any) {
        kafkaTemplate.send(topic, key, payload)
    }
}
