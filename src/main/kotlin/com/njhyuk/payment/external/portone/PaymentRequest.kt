package com.njhyuk.payment.external.portone

import com.fasterxml.jackson.annotation.JsonProperty

data class PaymentRequest(
    @JsonProperty("customer_uid")
    val customerUid: String,

    @JsonProperty("merchant_uid")
    val merchantUid: String,

    @JsonProperty("amount")
    val amount: Number,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("currency")
    val currency: String? = "KRW",

    @JsonProperty("tax_free")
    val taxFree: Number? = null,

    @JsonProperty("vat_amount")
    val vatAmount: Number? = null,

    @JsonProperty("vat")
    @Deprecated("This field is deprecated")
    val vat: Number? = null,

    @JsonProperty("buyer_name")
    val buyerName: String? = null,

    @JsonProperty("buyer_email")
    val buyerEmail: String? = null,

    @JsonProperty("buyer_tel")
    val buyerTel: String? = null,

    @JsonProperty("buyer_addr")
    val buyerAddr: String? = null,

    @JsonProperty("buyer_postcode")
    val buyerPostcode: String? = null,

    @JsonProperty("card_quota")
    val cardQuota: Int? = 0,

    @JsonProperty("interest_free_by_merchant")
    val interestFreeByMerchant: Boolean? = null,

    @JsonProperty("use_card_point")
    val useCardPoint: Boolean? = null,

    @JsonProperty("custom_data")
    val customData: String? = null,

    @JsonProperty("notice_url")
    val noticeUrl: String? = null,

    @JsonProperty("browser_ip")
    val browserIp: String? = null,

    @JsonProperty("product_type")
    val productType: String? = null,

    @JsonProperty("product_count")
    val productCount: Int? = 1,

    @JsonProperty("bypass")
    val bypass: String? = null
)
