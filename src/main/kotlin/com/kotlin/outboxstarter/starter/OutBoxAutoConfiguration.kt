package com.kotlin.outboxstarter.starter

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@AutoConfiguration
@ComponentScan("com.kotlin.outboxstarter")
@EntityScan("com.kotlin.outboxstarter.model")
@EnableJpaRepositories("com.kotlin.outboxstarter.repository")
class OutBoxAutoConfiguration