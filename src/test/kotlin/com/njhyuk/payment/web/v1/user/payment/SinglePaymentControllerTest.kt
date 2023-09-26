package com.njhyuk.payment.web.v1.user.payment

import com.fasterxml.jackson.databind.ObjectMapper
import com.njhyuk.payment.TesterCardConfig
import com.njhyuk.payment.core.card.command.BillingRegister
import com.njhyuk.payment.core.card.command.CardRegister
import com.njhyuk.payment.core.payment.command.SinglePaymentor.Command
import com.njhyuk.payment.restdoc.RestDocsConfiguration
import com.njhyuk.payment.restdoc.RestDocsUtil
import io.kotest.core.spec.style.DescribeSpec
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@ActiveProfiles("local", "test")
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration::class)
@EnableConfigurationProperties(TesterCardConfig::class)
class SinglePaymentControllerTest(
    private val objectMapper: ObjectMapper,
    private val mockMvc: MockMvc,
    private val cardRegister: CardRegister,
    private val billingRegister: BillingRegister,
    private val testerCardConfig: TesterCardConfig
) : DescribeSpec({
    describe("단건 결제 API") {
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

        context("단건 결제 데이터가 정상이라면") {
            it("200 OK. 정상 결제된다.") {
                val requestBody = Command(
                    cardId = card.cardId,
                    userId = userId,
                    amount = 100,
                    reason = "반바지"
                )

                mockMvc.perform(
                    post("/v1/user/payment")
                        .header("user-id", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody))
                ).andExpect(MockMvcResultMatchers.status().isOk)
                    .andDo(
                        MockMvcRestDocumentation.document(
                            "v1/user/payment_register",
                            HeaderDocumentation.requestHeaders(
                                HeaderDocumentation.headerWithName("user-id").description("유저 식별자")
                            ),
                            PayloadDocumentation.responseFields(
                                *RestDocsUtil.webResponse(),
                                PayloadDocumentation.fieldWithPath("data.paymentId").description("결제 ID")
                            )
                        )
                    )
            }
        }
    }
})
