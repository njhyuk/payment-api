package com.njhyuk.payment.external.portone

import com.fasterxml.jackson.annotation.JsonProperty

data class BillingKeyResponse(
    @JsonProperty("customer_uid")
    val customerUid: String,

    @JsonProperty("pg_provider")
    val pgProvider: String,

    @JsonProperty("pg_id")
    val pgId: String,

    @JsonProperty("inserted")
    val inserted: Long,

    @JsonProperty("updated")
    val updated: Long,

    @JsonProperty("card_name")
    val cardName: String,

    @JsonProperty("customer_id")
    val customerId: String? = null,

    @JsonProperty("card_code")
    val cardCode: String? = null,

    @JsonProperty("card_number")
    val cardNumber: String? = null,

    @JsonProperty("card_type")
    val cardType: Int? = null,

    @JsonProperty("customer_name")
    val customerName: String? = null,

    @JsonProperty("customer_tel")
    val customerTel: String? = null,

    @JsonProperty("customer_email")
    val customerEmail: String? = null,

    @JsonProperty("customer_addr")
    val customerAddress: String? = null,

    @JsonProperty("customer_postcode")
    val customerPostcode: String? = null
)
