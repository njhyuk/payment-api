package com.njhyuk.payment.web.v1.user.card

import com.njhyuk.payment.core.card.command.BillingRegister
import com.njhyuk.payment.core.card.command.CardRegister
import com.njhyuk.payment.web.WebResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class RegisterCardController(
    private val cardRegister: CardRegister,
    private val billingRegister: BillingRegister
) {
    @PostMapping("/v1/user/card")
    fun register(
        @RequestHeader(name = "user-id") userId: String,
        @RequestBody request: Request
    ): WebResponse<Response> {
        val billing = billingRegister.register(
            command = BillingRegister.Command(
                userId = userId,
                cardNo = request.cardNo,
                expiry = request.expiry,
                password = request.password,
                birth = request.birth
            )
        )

        val card = cardRegister.register(
            CardRegister.Command(
                userId = userId,
                cardNo = request.cardNo,
                billingKey = billing.billingKey,
                cardName = billing.cardName
            )
        )

        return WebResponse.success(Response(card.cardId))
    }

    data class Response(
        val cardId: Long
    )

    data class Request(
        val cardNo: String,
        val expiry: String,
        val password: String,
        val birth: String
    )
}
