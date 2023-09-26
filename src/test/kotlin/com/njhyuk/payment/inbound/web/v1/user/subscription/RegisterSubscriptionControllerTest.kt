package com.njhyuk.payment.inbound.web.v1.user.subscription

import com.fasterxml.jackson.databind.ObjectMapper
import com.njhyuk.payment.core.subscription.command.SubscriptionRegister
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
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDate

@WebMvcTest(RegisterSubscriptionController::class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration::class)
class RegisterSubscriptionControllerTest(
    private val objectMapper: ObjectMapper,
    @MockBean
    private var subscriptionRegister: SubscriptionRegister,
    private val mockMvc: MockMvc
) : DescribeSpec({
    describe("정기결제 등록 API") {
        it("200 OK. 정기결제를 등록한다.") {
            val paymentDate = LocalDate.now()

            given(
                subscriptionRegister.register(
                    SubscriptionRegister.Command(
                        cardId = 1,
                        userId = "010-1234-5678",
                        amount = 100,
                        paymentDate = paymentDate,
                        serviceKey = "COMMERCE",
                        serviceTransactionId = "3134324343225"
                    )
                )
            ).willReturn(
                SubscriptionRegister.Response(
                    subscriptionId = 1
                )
            )

            mockMvc.perform(
                RestDocumentationRequestBuilders.post("/v1/user/subscription")
                    .header("user-id", "010-1234-5678")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        objectMapper.writeValueAsString(
                            RegisterSubscriptionController.Request(
                                cardId = 1,
                                amount = 100,
                                paymentDate = paymentDate,
                                serviceKey = "COMMERCE",
                                serviceTransactionId = "3134324343225"
                            )
                        )
                    )
            ).andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(
                    MockMvcRestDocumentation.document(
                        "v1/user/subscription_register",
                        HeaderDocumentation.requestHeaders(
                            HeaderDocumentation.headerWithName("user-id").description("유저 식별자")
                        ),
                        PayloadDocumentation.requestFields(
                            PayloadDocumentation.fieldWithPath("cardId").description("카드 ID"),
                            PayloadDocumentation.fieldWithPath("amount").description("결제금액"),
                            PayloadDocumentation.fieldWithPath("paymentDate").description("겨제일"),
                            PayloadDocumentation.fieldWithPath("serviceKey").description("서비스키"),
                            PayloadDocumentation.fieldWithPath("serviceTransactionId").description("서비스 거래키")
                        ),
                        PayloadDocumentation.responseFields(
                            *RestDocsUtil.webResponse(),
                            PayloadDocumentation.fieldWithPath("data.subscriptionId").description("정기결제 ID")
                        )
                    )
                )
        }
    }
})
