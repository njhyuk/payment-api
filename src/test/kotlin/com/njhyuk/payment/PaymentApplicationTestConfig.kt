package com.njhyuk.payment

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringExtension
import io.kotest.extensions.spring.SpringTestExtension

class PaymentApplicationTestConfig {
    object ProjectConfig : AbstractProjectConfig() {
        override fun extensions(): List<SpringTestExtension> = listOf(SpringExtension)
    }
}
