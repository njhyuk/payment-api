package com.njhyuk.payment.external.portone.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class BillingKeyRequest(
    @JsonProperty("card_number")
    val cardNumber: String,

    @JsonProperty("expiry")
    val expiry: String,

    @JsonProperty("pg")
    val pg: String? = null,

    @JsonProperty("customer_id")
    val customerId: String? = null,

    @JsonProperty("birth")
    val birth: String? = null,

    @JsonProperty("pwd_2digit")
    val pwd2Digit: String? = null,

    @JsonProperty("cvc")
    val cvc: String? = null,

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
