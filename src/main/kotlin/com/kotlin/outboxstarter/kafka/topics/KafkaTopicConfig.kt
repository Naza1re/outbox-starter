package com.kotlin.outboxstarter.kafka.topics

import com.kotlin.outboxstarter.config.KafkaProperties
import org.apache.kafka.clients.admin.AdminClientConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaTopicConfig(
    private val properties: KafkaProperties
) {

    @Bean("outboxKafkaAdmin")
    fun outboxKafkaAdmin(): KafkaAdmin {
        val configs = mapOf<String, Any>(
            AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to properties.bootstrapServers
        )
        return KafkaAdmin(configs)
    }

}
