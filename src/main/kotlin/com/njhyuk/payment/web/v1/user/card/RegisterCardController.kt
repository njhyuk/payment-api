package com.njhyuk.payment.web.v1.user.card

import com.njhyuk.payment.core.card.command.CardRegister
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class RegisterCardController(
    private val cardRegister: CardRegister
) {
    @PostMapping("/user/v1/card")
    fun register(
        @RequestHeader(name = "user-id") userId: String,
        @RequestBody request: Request
    ): Response {
        val card = cardRegister.register(
            CardRegister.Command(
                userId = userId,
                cardNo = request.cardNo,
                expiry = request.expiry,
                password = request.password,
                birth = request.birth
            )
        )

        return Response(card.cardId)
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
