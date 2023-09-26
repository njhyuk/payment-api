package com.njhyuk.payment.core.payment.command

import com.njhyuk.payment.external.portone.PortOneConfig
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service


@Service
@EnableConfigurationProperties(PortOneConfig::class)
class PaymentCanceler {
}
