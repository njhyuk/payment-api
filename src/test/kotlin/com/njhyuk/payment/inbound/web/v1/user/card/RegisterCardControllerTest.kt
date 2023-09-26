package com.njhyuk.payment.inbound.web.v1.user.card

import com.fasterxml.jackson.databind.ObjectMapper
import com.njhyuk.payment.TesterCardConfig
import com.njhyuk.payment.restdoc.RestDocsConfiguration
import com.njhyuk.payment.restdoc.RestDocsUtil.Companion.webResponse
import com.njhyuk.payment.inbound.web.v1.user.card.RegisterCardController.Request
import io.kotest.core.spec.style.DescribeSpec
import org.springframework.boot.context.properties.EnableConfigurationProperties
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

@SpringBootTest
@ActiveProfiles("local", "test")
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration::class)
@EnableConfigurationProperties(TesterCardConfig::class)
class RegisterCardControllerTest(
    private val objectMapper: ObjectMapper,
    private val mockMvc: MockMvc,
    private val testerCardConfig: TesterCardConfig
) : DescribeSpec({
    describe("카드 등록 API") {
        context("카드 등록 데이터가 정상이라면") {
            it("200 OK. 카드를 등록한다.") {
                val requestBody = Request(
                    cardNo = testerCardConfig.cardNo,
                    expiry = testerCardConfig.expiry,
                    password = testerCardConfig.password,
                    birth = testerCardConfig.birth
                )

                mockMvc.perform(
                    post("/v1/user/card")
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
                                *webResponse(),
                                fieldWithPath("data.cardId").description("카드 ID")
                            )
                        )
                    )
            }
        }
    }
})
