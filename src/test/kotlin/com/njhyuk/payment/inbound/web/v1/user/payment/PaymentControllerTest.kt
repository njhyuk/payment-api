package com.njhyuk.payment.inbound.web.v1.user.payment

import com.fasterxml.jackson.databind.ObjectMapper
import com.njhyuk.payment.core.payment.command.PaymentCreator
import com.njhyuk.payment.restdoc.RestDocsConfiguration
import com.njhyuk.payment.restdoc.RestDocsUtil
import io.kotest.core.spec.style.DescribeSpec
import org.mockito.kotlin.given
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(PaymentController::class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration::class)
class PaymentControllerTest(
    private val objectMapper: ObjectMapper,
    private val mockMvc: MockMvc,
    @MockBean
    private var paymentCreator: PaymentCreator
) : DescribeSpec({
    describe("단건 결제 API") {
        val userId = "010-1234-5678"

        given(
            paymentCreator.create(
                PaymentCreator.Command(
                    serviceKey = "COMMERCE",
                    serviceTransactionId = "4354665654",
                    cardId = 1,
                    userId = userId,
                    amount = 100,
                    productName = "반바지"
                )
            )
        ).willReturn(
            PaymentCreator.Response(
                paymentId = 1,
                transactionId = "COMMERCE_4354665654"
            )
        )

        context("단건 결제 데이터가 정상이라면") {
            it("200 OK. 정상 결제된다.") {
                val requestBody = PaymentController.Request(
                    serviceKey = "COMMERCE",
                    serviceTransactionId = "4354665654",
                    cardId = 1,
                    amount = 100,
                    productName = "반바지"
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
                            PayloadDocumentation.requestFields(
                                PayloadDocumentation.fieldWithPath("serviceKey").description("서비스키"),
                                PayloadDocumentation.fieldWithPath("serviceTransactionId").description("서비스 거래키"),
                                PayloadDocumentation.fieldWithPath("cardId").description("카드 ID"),
                                PayloadDocumentation.fieldWithPath("amount").description("결제금액"),
                                PayloadDocumentation.fieldWithPath("productName").description("제품명")
                            ),
                            PayloadDocumentation.responseFields(
                                *RestDocsUtil.webResponse(),
                                PayloadDocumentation.fieldWithPath("data.paymentId").description("결제 ID"),
                                PayloadDocumentation.fieldWithPath("data.transactionId").description("거래 ID")
                            )
                        )
                    )
            }
        }
    }
})
