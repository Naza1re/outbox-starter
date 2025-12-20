package com.kotlin.outboxstarter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.bind.annotation.RestController

@EnableScheduling
@SpringBootApplication
@RestController
class OutboxStarterApplication

fun main(args: Array<String>) {
    runApplication<OutboxStarterApplication>(*args)
}
