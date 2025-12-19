package com.kotlin.outboxstarter.repository

import com.kotlin.outboxstarter.model.MessageStatus
import com.kotlin.outboxstarter.model.OutboxMessage
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface OutboxMessageRepository : JpaRepository<OutboxMessage, Long>, JpaSpecificationExecutor<OutboxMessage> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        SELECT o FROM OutboxMessage o
        WHERE o.status = :status
        ORDER BY o.createdAt
    """)
    fun findBatchForUpdate(
        @Param("status") status: MessageStatus
    ): List<OutboxMessage>
}