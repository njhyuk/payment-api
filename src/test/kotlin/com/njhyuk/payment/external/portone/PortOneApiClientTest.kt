package com.njhyuk.payment.external.portone

import com.njhyuk.payment.TesterCardConfig
import com.njhyuk.payment.external.portone.dto.BillingKeyRequest
import com.njhyuk.payment.external.portone.dto.GetTokenRequest
import com.njhyuk.payment.external.portone.dto.PaymentCancelRequest
import com.njhyuk.payment.external.portone.dto.PaymentRequest
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.UUID

@SpringBootTest
@ActiveProfiles("local", "test")
@EnableConfigurationProperties(PortOneConfig::class, TesterCardConfig::class)
class PortOneApiClientTest(
    private val portOneApiClient: PortOneApiClient,
    private val portOneConfig: PortOneConfig,
    private val testerCardConfig: TesterCardConfig
) : DescribeSpec({
    describe("getToken") {
        context("토큰 등록 데이터가 정상이라면") {
            it("200 OK. 토큰을 발급받는다.") {
                val token = portOneApiClient.getToken(
                    GetTokenRequest(
                        impKey = portOneConfig.impKey,
                        impSecret = portOneConfig.impSecret
                    )
                )

                token.response.accessToken.length shouldBeGreaterThan 0
            }
        }
    }

    describe("billingKey") {
        context("비인증 결제 (빌링키) 데이터가 정상이라면") {
            it("200 OK. 빌링키를 발급받는다.") {
                val token = portOneApiClient.getToken(
                    GetTokenRequest(
                        impKey = portOneConfig.impKey,
                        impSecret = portOneConfig.impSecret
                    )
                )

                val data = portOneApiClient.billingKey(
                    authorization = token.response.accessToken,
                    customerUid = UUID.randomUUID().toString(),
                    request = BillingKeyRequest(
                        cardNumber = testerCardConfig.cardNo,
                        expiry = testerCardConfig.expiry,
                        birth = testerCardConfig.birth,
                        pwd2Digit = testerCardConfig.password
                    )
                )

                data.code shouldBe 0
            }
        }
    }

    describe("payment") {
        context("결제 데이터가 정상이라면") {
            it("200 OK. 결제를 실행한다.") {
                val token = portOneApiClient.getToken(
                    GetTokenRequest(
                        impKey = portOneConfig.impKey,
                        impSecret = portOneConfig.impSecret
                    )
                )

                val data = portOneApiClient.billingKey(
                    authorization = token.response.accessToken,
                    customerUid = UUID.randomUUID().toString(),
                    request = BillingKeyRequest(
                        cardNumber = testerCardConfig.cardNo,
                        expiry = testerCardConfig.expiry,
                        birth = testerCardConfig.birth,
                        pwd2Digit = testerCardConfig.password
                    )
                )

                portOneApiClient.payment(
                    authorization = token.response.accessToken,
                    request = PaymentRequest(
                        customerUid = data.response.customerUid,
                        merchantUid = portOneConfig.storeId,
                        amount = 100,
                        name = "아이폰 15 프로"
                    )
                )

                data.code shouldBe 0
            }
        }
    }

    describe("paymentCancel") {
        context("결제 취소 데이터가 정상이라면") {
            it("200 OK. 결제취소를 실행한다.") {
                val token = portOneApiClient.getToken(
                    GetTokenRequest(
                        impKey = portOneConfig.impKey,
                        impSecret = portOneConfig.impSecret
                    )
                )

                val data = portOneApiClient.billingKey(
                    authorization = token.response.accessToken,
                    customerUid = UUID.randomUUID().toString(),
                    request = BillingKeyRequest(
                        cardNumber = testerCardConfig.cardNo,
                        expiry = testerCardConfig.expiry,
                        birth = testerCardConfig.birth,
                        pwd2Digit = testerCardConfig.password
                    )
                )

                val payment = portOneApiClient.payment(
                    authorization = token.response.accessToken,
                    request = PaymentRequest(
                        customerUid = data.response.customerUid,
                        merchantUid = portOneConfig.storeId,
                        amount = 100,
                        name = "아이폰 15 프로"
                    )
                )

                val cancel = portOneApiClient.cancelPayment(
                    authorization = token.response.accessToken,
                    request = PaymentCancelRequest(
                        impUid = payment.response.impUid,
                    )
                )

                cancel.code shouldBe 0
            }
        }
    }
})
