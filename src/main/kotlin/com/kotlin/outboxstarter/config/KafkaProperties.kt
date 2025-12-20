package com.kotlin.outboxstarter.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("outbox.kafka")
class KafkaProperties {
    lateinit var bootstrapServers : String
}