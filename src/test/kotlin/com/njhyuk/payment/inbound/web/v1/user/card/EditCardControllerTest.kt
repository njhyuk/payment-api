package com.njhyuk.payment.inbound.web.v1.user.card

import com.fasterxml.jackson.databind.ObjectMapper
import com.njhyuk.payment.core.card.command.CardEditor
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

@WebMvcTest(EditCardController::class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration::class)
class EditCardControllerTest(
    private val objectMapper: ObjectMapper,
    @MockBean
    private var cardEditor: CardEditor,
    private val mockMvc: MockMvc
) : DescribeSpec({
    describe("카드 수정 조회 API") {
        it("200 OK. 카드를 수정한다.") {
            given(
                cardEditor.edit(
                    CardEditor.Command(
                        cardId = 1,
                        cardName = "용돈카드"
                    )
                )
            ).willReturn(
                CardEditor.Response(
                    cardId = 1
                )
            )

            val request = EditCardController.Request(
                cardName = "용돈카드"
            )

            mockMvc.perform(
                RestDocumentationRequestBuilders.put("/v1/user/card/{cardId}", 1)
                    .header("user-id", "010-1234-5678")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            ).andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(
                    MockMvcRestDocumentation.document(
                        "v1/user/card_edit",
                        HeaderDocumentation.requestHeaders(
                            HeaderDocumentation.headerWithName("user-id").description("유저 식별자")
                        ),
                        PayloadDocumentation.responseFields(
                            *RestDocsUtil.webResponse(),
                            PayloadDocumentation.fieldWithPath("data.cardId").description("카드 ID")
                        )
                    )
                )
        }
    }
})
