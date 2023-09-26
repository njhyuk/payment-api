package com.njhyuk.payment

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "tester-card")
class TesterCardConfig(
    val cardNo: String,
    val expiry: String,
    val password: String,
    val birth: String
)
