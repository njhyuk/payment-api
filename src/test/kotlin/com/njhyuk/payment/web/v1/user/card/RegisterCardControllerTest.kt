package com.njhyuk.payment.web.v1.user.card

import com.fasterxml.jackson.databind.ObjectMapper
import com.njhyuk.payment.restdoc.RestDocsConfiguration
import com.njhyuk.payment.web.v1.user.card.RegisterCardController.Request
import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.style.DescribeSpec
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Ignored("실제 카드정보가 필요하여 실패하는 테스트")
@SpringBootTest
@ActiveProfiles("local", "test")
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration::class)
class RegisterCardControllerTest(
    private val objectMapper: ObjectMapper,
    private val mockMvc: MockMvc
) : DescribeSpec({
    describe("카드 등록 API") {
        context("카드 등록 데이터가 정상이라면") {
            it("200 OK. 카드를 등록한다.") {
                val requestBody = Request(
                    cardNo = "0000-0000-0000-000",
                    expiry = "2027-10",
                    password = "0000",
                    birth = "0101"
                )

                mockMvc.perform(
                    post("/v1/card")
                        .header("user-id", "010-1234-5678")
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
                                fieldWithPath("cardId").description("카드 ID")
                            )
                        )
                    )
            }
        }
    }
})
