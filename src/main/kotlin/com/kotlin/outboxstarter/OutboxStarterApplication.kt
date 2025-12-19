package com.kotlin.outboxstarter

import com.kotlin.outboxstarter.kafka.objects.ExampleObject
import com.kotlin.outboxstarter.model.OutboxMessage
import com.kotlin.outboxstarter.service.OutboxMessageService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.bind.annotation.*

@EnableScheduling
@SpringBootApplication
@RestController
class OutboxStarterApplication(
    private val outboxMessageService: OutboxMessageService,
){
    @GetMapping("/hello")
    fun hello(): String {
        return "Hello World"
    }

    @PostMapping()
    fun createMessage(@RequestBody message: ExampleObject
    ) {
        outboxMessageService!!.send(message.key(), message, "test-topic" )
    }
}



fun main(args: Array<String>) {
    runApplication<OutboxStarterApplication>(*args)
}
