package com.njhyuk.payment.inbound.web.v1.user.card

import com.fasterxml.jackson.databind.ObjectMapper
import com.njhyuk.payment.core.card.command.BillingRegister
import com.njhyuk.payment.core.card.command.CardRegister
import com.njhyuk.payment.inbound.web.v1.user.card.RegisterCardController.Request
import com.njhyuk.payment.restdoc.RestDocsConfiguration
import com.njhyuk.payment.restdoc.RestDocsUtil.Companion.webResponse
import io.kotest.core.spec.style.DescribeSpec
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate
import java.time.YearMonth
import java.util.*

@WebMvcTest(RegisterCardController::class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration::class)
class RegisterCardControllerTest(
    private val objectMapper: ObjectMapper,
    private val mockMvc: MockMvc,
    @MockBean
    private var billingRegister: BillingRegister,
    @MockBean
    private var cardRegister: CardRegister
) : DescribeSpec({
    describe("카드 등록 API") {
        context("카드 등록 데이터가 정상이라면") {
            it("200 OK. 카드를 등록한다.") {
                val userId = "010-1234-5678"
                val billingKey = UUID.randomUUID().toString()

                given(
                    billingRegister.register(any())
                ).willReturn(
                    BillingRegister.Response(
                        billingKey = billingKey,
                        cardName = "하나카드"
                    )
                )

                given(
                    cardRegister.register(
                        CardRegister.Command(
                            userId = userId,
                            cardNo = "0000-0000-0000-0000",
                            billingKey = billingKey,
                            cardName = "하나카드"
                        )
                    )
                ).willReturn(
                    CardRegister.Response(
                        cardId = 1
                    )
                )

                val requestBody = Request(
                    cardNo = "0000-0000-0000-0000",
                    expiry = YearMonth.of(2020, 1),
                    password = "00",
                    birth = LocalDate.of(1996, 1, 1)
                )

                mockMvc.perform(
                    post("/v1/user/card")
                        .header("user-id", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody))
                ).andExpect(status().isOk)
                    .andDo(
                        document(
                            "v1/user/card_register",
                            requestHeaders(
                                headerWithName("user-id").description("유저 식별자")
                            ),
                            responseFields(
                                *webResponse(),
                                fieldWithPath("data.cardId").description("카드 ID")
                            )
                        )
                    )
            }
        }
    }
})
