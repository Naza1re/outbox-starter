package com.kotlin.outboxstarter.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.*
import com.vladmihalcea.hibernate.type.json.JsonType
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "outbox_message")
class OutboxMessage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val topic: String,

    val eventType: String,

    @Type(JsonType::class)
    @Column(columnDefinition = "jsonb")
    val payload: Any,

    val createdAt: Instant = Instant.now(),

    @Enumerated(EnumType.STRING)
    val status: MessageStatus

)