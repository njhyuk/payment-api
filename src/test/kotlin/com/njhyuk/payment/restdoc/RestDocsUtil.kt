package com.njhyuk.payment.restdoc

import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath

class RestDocsUtil {
    companion object {
        fun webResponse(): Array<FieldDescriptor> {
            return arrayOf(
                fieldWithPath("success").description("처리 성공 여부"),
                fieldWithPath("code").description("실패 예외 코드").optional(),
                fieldWithPath("message").description("실패 예외 메세지").optional(),
                fieldWithPath("data").description("응답 데이터")
            )
        }
    }
}
