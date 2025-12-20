package com.kotlin.outboxstarter.kafka.objects

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.kotlin.outboxstarter.kafka.KafkaObject

data class ExampleObject @JsonCreator constructor(
    @JsonProperty("id") val id: Int,
    @JsonProperty("name") val name: String,
    @JsonProperty("surname") val surname: String,
    @JsonProperty("address") val address: Address,
) : KafkaObject<ExampleObject> {
    override fun key() = id.toString()
    override fun value() = this
}