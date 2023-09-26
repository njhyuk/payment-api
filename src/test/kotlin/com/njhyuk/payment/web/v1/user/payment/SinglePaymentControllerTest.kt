package com.njhyuk.payment.web.v1.user.payment

import com.fasterxml.jackson.databind.ObjectMapper
import com.njhyuk.payment.core.payment.command.SinglePaymentor.Command
import com.njhyuk.payment.restdoc.RestDocsConfiguration
import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.style.DescribeSpec
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@Ignored("실제 카드정보가 필요하여 실패하는 테스트")
@SpringBootTest
@ActiveProfiles("local", "test")
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration::class)
class SinglePaymentControllerTest(
    private val objectMapper: ObjectMapper,
    private val mockMvc: MockMvc
) : DescribeSpec({
    describe("단건 결제 API") {
        context("단건 결제 데이터가 정상이라면") {
            it("200 OK. 카드를 등록한다.") {
                val requestBody = Command(
                    cardId = 1,
                    userId = "010-1234-5678",
                    amount = 100,
                    reason = "반바지"
                )

                mockMvc.perform(
                    RestDocumentationRequestBuilders.post("/v1/payment")
                        .header("user-id", "010-1234-5678")
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
                                PayloadDocumentation.fieldWithPath("data.paymentId").description("결제 ID")
                            )
                        )
                    )
            }
        }
    }
})
