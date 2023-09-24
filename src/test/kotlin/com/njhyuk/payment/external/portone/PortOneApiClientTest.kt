package com.njhyuk.payment.external.portone

import com.njhyuk.payment.external.portone.PortOneApiClient.GetTokenRequest
import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest

@Ignored
@SpringBootTest
@EnableConfigurationProperties(PortOneConfig::class)
class PortOneApiClientTest(
    private val portOneApiClient: PortOneApiClient,
    private val portOneConfig: PortOneConfig
) : DescribeSpec({
    describe("getToken") {
        context("토큰 등록 데이터가 정상이라면") {
            it("200 OK. 토큰을 발급받는다.") {
                val data = portOneApiClient.getToken(
                    GetTokenRequest(
                        impKey = portOneConfig.impKey,
                        impSecret = portOneConfig.impSecret
                    )
                )

                data.response.accessToken.length shouldBeGreaterThan 0
            }
        }
    }
})
