package com.njhyuk.payment.inbound.web.v1.user.subscription

import com.fasterxml.jackson.databind.ObjectMapper
import com.njhyuk.payment.core.subscription.command.SubscriptionCanceler
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

@WebMvcTest(CancelSubscriptionController::class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration::class)
class CancelSubscriptionControllerTest(
    private val objectMapper: ObjectMapper,
    @MockBean
    private var subscriptionCanceler: SubscriptionCanceler,
    private val mockMvc: MockMvc
) : DescribeSpec({
    describe("정기결제 취소 API") {
        it("200 OK. 정기결제를 취소한다.") {
            given(
                subscriptionCanceler.cancel(1)
            ).willReturn(
                SubscriptionCanceler.Response(1)
            )

            mockMvc.perform(
                RestDocumentationRequestBuilders.post("/v1/user/subscription/cancel")
                    .header("user-id", "010-1234-5678")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        objectMapper.writeValueAsString(
                            CancelSubscriptionController.Request(1)
                        )
                    )
            ).andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(
                    MockMvcRestDocumentation.document(
                        "v1/user/subscription_cancel",
                        HeaderDocumentation.requestHeaders(
                            HeaderDocumentation.headerWithName("user-id").description("유저 식별자")
                        ),
                        PayloadDocumentation.requestFields(
                            PayloadDocumentation.fieldWithPath("subscriptionId").description("정기결제 ID")
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
