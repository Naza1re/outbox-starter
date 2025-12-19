package com.kotlin.outboxstarter.kafka.topics

import com.kotlin.outboxstarter.config.KafkaProperties
import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaTopicConfig(
    private val properties: KafkaProperties
) {

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val configs = mapOf<String, Any>(
            AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to properties.bootstrapServers
        )
        return KafkaAdmin(configs)
    }

    @Bean
    fun testTopic(): NewTopic = NewTopic("test-topic", 1, 1)

    @Bean
    fun userEventsTopic(): NewTopic = NewTopic("user-events", 1, 1)

    @Bean
    fun orderEventsTopic(): NewTopic = NewTopic("order-events", 1, 1)

}