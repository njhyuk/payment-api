package com.njhyuk.payment.core.payment.command

import com.njhyuk.payment.core.card.command.CardRegister
import com.njhyuk.payment.core.card.command.CardRegisterCommand
import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@Ignored("실제 카드정보가 필요하여 실패하는 테스트")
@SpringBootTest
@ActiveProfiles("local", "test")
class SinglePaymentorTest(
    register: CardRegister,
    singlePaymentor: SinglePaymentor
) : DescribeSpec({
    describe("payment 메서드는") {
        it("단건 결제를 처리한다") {
            val card = register.register(
                CardRegisterCommand(
                    userId = "010-0000-0000",
                    cardNo = "0000-0000-0000-0000",
                    expiry = "2099-01",
                    birth = "800101",
                    password = "00"
                )
            )

            val response = singlePaymentor.payment(
                SinglePaymentCommand(
                    cardId = card.cardId,
                    userId = "010-0000-0000",
                    amount = 100,
                    reason = "반바지"
                )
            )

            response.paymentId shouldBe 1L
        }
    }
})
