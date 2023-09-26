package com.njhyuk.payment.inbound.web.v1.user.card

import com.njhyuk.payment.core.card.domain.Card
import com.njhyuk.payment.core.card.query.GetUserCards
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
import java.time.LocalDateTime
import java.util.UUID

@WebMvcTest(GetCardsController::class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration::class)
class GetCardsControllerTest(
    @MockBean
    private var getUserCards: GetUserCards,
    private val mockMvc: MockMvc
) : DescribeSpec({
    describe("카드 목록 조회 API") {
        it("200 OK. 카드를 등록한다.") {
            given(
                getUserCards.getCards("010-1234-5678")
            ).willReturn(
                listOf(
                    Card(
                        id = 1,
                        userId = "010-1234-5678",
                        billingKey = UUID.randomUUID().toString(),
                        maskedCardNo = "2222",
                        cardName = "하나카드",
                        createdAt = LocalDateTime.now(),
                        updatedAt = LocalDateTime.now()
                    )
                )
            )

            mockMvc.perform(
                RestDocumentationRequestBuilders.get("/v1/user/card")
                    .header("user-id", "010-1234-5678")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(
                    MockMvcRestDocumentation.document(
                        "v1/user/card_list",
                        HeaderDocumentation.requestHeaders(
                            HeaderDocumentation.headerWithName("user-id").description("유저 식별자")
                        ),
                        PayloadDocumentation.responseFields(
                            *RestDocsUtil.webResponse(),
                            PayloadDocumentation.fieldWithPath("data.cards[].cardId").description("카드 ID"),
                            PayloadDocumentation.fieldWithPath("data.cards[].maskedCardNo").description("마스킨된 카드 번호"),
                            PayloadDocumentation.fieldWithPath("data.cards[].cardName").description("카드 이름"),
                            PayloadDocumentation.fieldWithPath("data.cards[].createdAt").description("카드 생성일"),
                            PayloadDocumentation.fieldWithPath("data.cards[].updatedAt").description("카드 수정일")
                        )
                    )
                )
        }
    }
})
