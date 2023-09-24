package com.njhyuk.payment.external.portone

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "portone")
data class PortOneConfig(
    val storeId: String,
    val impKey: String,
    val impSecret: String
)
