package com.kotlin.outboxstarter.model

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.Instant

@Entity
@Table(name = "outbox_message")
class OutboxMessage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val topic: String,

    val key: String?,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    val payload: String,

    @Column(name = "created_at")
    val createdAt: Instant = Instant.now(),

    @Enumerated(EnumType.STRING)
    var status: MessageStatus,

    @Column(name = "type")
    val type: String
)
{
    companion object {
        private val mapper = jacksonObjectMapper()
        fun <T> serialize(obj: T): String = mapper.writeValueAsString(obj)
        fun <T> deserialize(json: String, clazz: Class<T>): T = mapper.readValue(json, clazz)
    }
}
