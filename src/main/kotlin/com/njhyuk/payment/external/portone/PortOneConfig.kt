package com.njhyuk.payment.external.portone

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "portone")
class PortOneConfig(
    val impKey: String,
    val impSecret: String
)
