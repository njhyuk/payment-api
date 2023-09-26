package com.njhyuk.payment.core.card.command

import com.njhyuk.payment.core.card.domain.Card
import com.njhyuk.payment.core.card.domain.CardRepository
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import java.time.LocalDateTime
import java.util.UUID

class CardRegisterTest(
    private val repository: CardRepository = mock(),
    private val register: CardRegister = CardRegister(repository)
) : DescribeSpec({
    describe("register 메서드는") {
        it("카드를 등록한다") {
            given(
                repository.save(any<Card>())
            ).willReturn(
                Card(
                    id = 1,
                    userId = "010-1234-5678",
                    billingKey = UUID.randomUUID().toString(),
                    maskedCardNo = "2222",
                    cardName = "하나카드",
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.now()
                )
            )

            val response = register.register(
                CardRegister.Command(
                    userId = "010-0000-0000",
                    cardNo = "0000-0000-0000-0000",
                    billingKey = "TEST_BILLING_KEY",
                    cardName = "AA카드"
                )
            )

            response.cardId shouldBe 1L
        }
    }
})
