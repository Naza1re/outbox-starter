package com.kotlin.outboxstarter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OutboxStarterApplication

fun main(args: Array<String>) {
    runApplication<OutboxStarterApplication>(*args)
}
