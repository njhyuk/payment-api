package com.njhyuk.payment.external.portone

import com.fasterxml.jackson.annotation.JsonProperty

data class GetTokenResponse(
    @JsonProperty("access_token")
    val accessToken: String
)
