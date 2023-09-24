package com.njhyuk.payment.external.portone

import com.njhyuk.payment.external.portone.dto.BillingKeyRequest
import com.njhyuk.payment.external.portone.dto.GetTokenRequest
import com.njhyuk.payment.external.portone.dto.PaymentRequest
import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.UUID

@Ignored("포트원 API 학습을 위한 테스트")
@SpringBootTest
@ActiveProfiles("local", "test")
@EnableConfigurationProperties(PortOneConfig::class)
class PortOneApiClientTest(
    private val portOneApiClient: PortOneApiClient,
    private val portOneConfig: PortOneConfig
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
                        cardNumber = "0000-0000-0000-0000",
                        expiry = "2099-01",
                        birth = "800101",
                        pwd2Digit = "00"
                    )
                )

                data.code shouldBe 0 // 실제 카드데이터가 아니기 때문에 실패한다.
            }
        }
    }

    describe("payment") {
        context("빌링키 발급 데이터가 정상이라면") {
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
                        cardNumber = "0000-0000-0000-0000",
                        expiry = "2099-01",
                        birth = "800101",
                        pwd2Digit = "00"
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

                data.code shouldBe 0 // 실제 카드데이터가 아니기 때문에 실패한다.
            }
        }
    }
})
