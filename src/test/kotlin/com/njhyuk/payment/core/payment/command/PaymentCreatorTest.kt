package com.njhyuk.payment.core.payment.command

import com.njhyuk.payment.core.payment.command.PaymentCreator.Command
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

class PaymentCreatorTest(
    private val paymentExecutor: PaymentCreateExecutor = mock(),
    private val paymentRecorder: PaymentCreateRecorder = mock(),
    private val transactionIdGenerator: TransactionIdGenerator = TransactionIdGenerator(),
    private val paymentCreator: PaymentCreator = PaymentCreator(
        paymentExecutor,
        paymentRecorder,
        transactionIdGenerator
    )
) : DescribeSpec({
    describe("payment 메서드는") {
        it("단건 결제를 처리한다") {
            given(
                paymentExecutor.payment(
                    PaymentCreateExecutor.Command(
                        cardId = 1,
                        transactionId = "COMMERCE_4354665654",
                        userId = "010-0000-0000",
                        amount = 100,
                        productName = "반바지"
                    )
                )
            ).willReturn(
                PaymentCreateExecutor.Response(
                    partnerPaymentId = "1111133",
                    amount = 100
                )
            )

            given(
                paymentRecorder.record(
                    PaymentCreateRecorder.Command(
                        cardId = 1,
                        serviceKey = "COMMERCE",
                        transactionId = "COMMERCE_4354665654",
                        partnerPaymentId = "1111133",
                        userId = "010-0000-0000",
                        amount = 100,
                        productName = "반바지"
                    )
                )
            ).willReturn(
                PaymentCreateRecorder.Response(
                    paymentId = 1
                )
            )

            val response = paymentCreator.create(
                Command(
                    serviceKey = "COMMERCE",
                    serviceTransactionId = "4354665654",
                    userId = "010-0000-0000",
                    cardId = 1,
                    amount = 100,
                    productName = "반바지"
                )
            )

            response.paymentId shouldBe 1L
        }
    }
})
