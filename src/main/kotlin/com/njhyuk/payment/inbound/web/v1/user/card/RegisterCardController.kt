package com.njhyuk.payment.inbound.web.v1.user.card

import com.fasterxml.jackson.annotation.JsonFormat
import com.njhyuk.payment.core.card.command.BillingRegister
import com.njhyuk.payment.core.card.command.CardRegister
import com.njhyuk.payment.inbound.web.WebResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import javax.validation.Valid
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.Size

@RestController
class RegisterCardController(
    private val cardRegister: CardRegister,
    private val billingRegister: BillingRegister
) {
    @PostMapping("/v1/user/card")
    fun register(
        @RequestHeader(name = "user-id") userId: String,
        @Valid @RequestBody request: Request
    ): WebResponse<Response> {
        val billing = billingRegister.register(
            command = BillingRegister.Command(
                userId = userId,
                cardNo = request.cardNo,
                expiry = request.expiry,
                password = request.password,
                birth = request.birth,
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
        @JsonFormat(pattern = "yyyy-MM")
        val expiry: YearMonth,
        @Size(min = 2, max = 2)
        val password: String,
        @JsonFormat(pattern = "yyMMdd")
        val birth: LocalDate
    ) {
        @AssertTrue(message = "카드번호는 16자리로 구성되어야 합니다.")
        private fun isConditionCarNo(): Boolean {
            return cardNo.replace("-", "").length == 16
        }
    }
}
