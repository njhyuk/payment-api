package com.njhyuk.payment.core.payment.command

import com.njhyuk.payment.TesterCardConfig
import com.njhyuk.payment.core.card.command.BillingRegister
import com.njhyuk.payment.core.card.command.CardRegister
import com.njhyuk.payment.core.payment.command.PaymentCreator.Command
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("local", "test")
@EnableConfigurationProperties(TesterCardConfig::class)
class PaymentCreatorTest(
    private val cardRegister: CardRegister,
    private val billingRegister: BillingRegister,
    private val testerCardConfig: TesterCardConfig,
    private val paymentCreator: PaymentCreator
) : DescribeSpec({
    describe("payment 메서드는") {
        it("단건 결제를 처리한다") {
            val userId = "010-1234-5678"

            val billing = billingRegister.register(
                command = BillingRegister.Command(
                    userId = userId,
                    cardNo = testerCardConfig.cardNo,
                    expiry = testerCardConfig.expiry,
                    password = testerCardConfig.password,
                    birth = testerCardConfig.birth
                )
            )

            val card = cardRegister.register(
                CardRegister.Command(
                    userId = userId,
                    cardNo = testerCardConfig.cardNo,
                    billingKey = billing.billingKey,
                    cardName = billing.cardName
                )
            )

            val response = paymentCreator.create(
                Command(
                    serviceKey = "COMMERCE",
                    orderId = "4354665654",
                    cardId = card.cardId,
                    userId = userId,
                    amount = 100,
                    productName = "반바지"
                )
            )

            response.paymentId shouldBe 1L
        }
    }
})
