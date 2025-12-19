package com.kotlin.outboxstarter.kafka.objects

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Address@JsonCreator constructor(
    @JsonProperty("street") val street: String,
    @JsonProperty("number") val number: String,
)