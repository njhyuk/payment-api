package com.njhyuk.payment.external.portone

import com.fasterxml.jackson.annotation.JsonProperty

data class GetTokenRequest(
    @JsonProperty("imp_key")
    val impKey: String,
    @JsonProperty("imp_secret")
    val impSecret: String
)
