package com.njhyuk.payment.core.card.command

import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@Ignored("실제 카드정보가 필요하여 실패하는 테스트")
@SpringBootTest
@ActiveProfiles("local", "test")
class CardRegisterTest(
    register: CardRegister
) : DescribeSpec({
    describe("register 메서드는") {
        it("카드를 등록한다") {
            val response = register.register(
                CardRegister.Command(
                    userId = "010-0000-0000",
                    cardNo = "0000-0000-0000-0000",
                    billingKey = "TEST_BILLING_KEY",
                    cardName = "AA카드",
                )
            )

            response.cardId shouldBe 1L
        }
    }
})
