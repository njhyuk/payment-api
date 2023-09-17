package com.njhyuk.payment.web.v1.card

import com.njhyuk.payment.core.card.command.CardRegister
import com.njhyuk.payment.core.card.command.CardRegisterCommand
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class RegisterCardController(
    private val cardRegister: CardRegister
) {
    @PostMapping("/v1/card")
    fun register(
        @RequestHeader(name = "user-id") userId: String,
        @RequestBody request: RegisterCardRequest
    ) {
        cardRegister.register(
            CardRegisterCommand(
                userId = userId,
                cardNo = request.cardNo,
                expiry = request.expiry,
                password = request.password,
                birth = request.birth
            )
        )
    }
}
