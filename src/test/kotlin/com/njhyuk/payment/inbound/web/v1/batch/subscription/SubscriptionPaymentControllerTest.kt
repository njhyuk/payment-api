package com.njhyuk.payment.inbound.web.v1.batch.subscription

import com.fasterxml.jackson.databind.ObjectMapper
import com.njhyuk.payment.core.subscription.command.SubscriptionDailyPaymentor
import com.njhyuk.payment.restdoc.RestDocsConfiguration
import io.kotest.core.spec.style.DescribeSpec
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(SubscriptionPaymentController::class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration::class)
class SubscriptionPaymentControllerTest(
    private val objectMapper: ObjectMapper,
    @MockBean
    private var subscriptionDailyPaymentor: SubscriptionDailyPaymentor,
    private val mockMvc: MockMvc
) : DescribeSpec({
    describe("정기결제 일배치 API") {
        it("200 OK. 정기결제를 실행한다.") {
            mockMvc.perform(
                post("/batch/v1/subscription/payment/{paymentDate}", "2023-09-27")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(MockMvcRestDocumentation.document("v1/batch/subscription_daily"))
        }
    }
})
