package com.njhyuk.payment.core.card.command

import com.njhyuk.payment.core.card.domain.Card
import com.njhyuk.payment.core.card.domain.CardRepository
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import java.time.LocalDateTime

class CardRegisterTest : DescribeSpec({
    describe("register 메서드는") {
        it("카드를 등록한다") {
            val repository: CardRepository = mock()
            val register = CardRegister(repository)

            val card = Card(
                id = 1L,
                userId = "",
                billingKey = "",
                cardIssuerId = "",
                cardName = "",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
                pgId = ""
            )

            given(repository.save(any<Card>()))
                .willReturn(card)

            val response = register.register(
                CardRegisterCommand(
                    userId = "",
                    cardNo = "",
                    expiry = "",
                    password = "",
                    birth = ""
                )
            )

            response.cardId shouldBe 1L
        }
    }
})
