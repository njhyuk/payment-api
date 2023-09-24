package com.njhyuk.payment.external.portone.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PaymentResponse(
    @JsonProperty("imp_uid")
    val impUid: String,

    @JsonProperty("merchant_uid")
    val merchantUid: String,

    @JsonProperty("pay_method")
    val payMethod: String?,

    @JsonProperty("channel")
    val channel: String?,

    @JsonProperty("pg_provider")
    val pgProvider: String?,

    @JsonProperty("emb_pg_provider")
    val embPgProvider: String?,

    @JsonProperty("pg_tid")
    val pgTid: String?,

    @JsonProperty("pg_id")
    val pgId: String?,

    @JsonProperty("escrow")
    val escrow: Boolean?,

    @JsonProperty("apply_num")
    val applyNum: String?,

    @JsonProperty("bank_code")
    val bankCode: String?,

    @JsonProperty("bank_name")
    val bankName: String?,

    @JsonProperty("card_code")
    val cardCode: String?,

    @JsonProperty("card_name")
    val cardName: String?,

    @JsonProperty("card_quota")
    val cardQuota: Int?,

    @JsonProperty("card_number")
    val cardNumber: String?,

    @JsonProperty("card_type")
    val cardType: Int?,

    @JsonProperty("vbank_code")
    val vbankCode: String?,

    @JsonProperty("vbank_name")
    val vbankName: String?,

    @JsonProperty("vbank_num")
    val vbankNum: String?,

    @JsonProperty("vbank_holder")
    val vbankHolder: String?,

    @JsonProperty("vbank_date")
    val vbankDate: Int?,

    @JsonProperty("vbank_issued_at")
    val vbankIssuedAt: Int?,

    @JsonProperty("name")
    val name: String?,

    @JsonProperty("amount")
    val amount: Number,

    @JsonProperty("cancel_amount")
    val cancelAmount: Number,

    @JsonProperty("currency")
    val currency: String,

    @JsonProperty("buyer_name")
    val buyerName: String?,

    @JsonProperty("buyer_email")
    val buyerEmail: String?,

    @JsonProperty("buyer_tel")
    val buyerTel: String?,

    @JsonProperty("buyer_addr")
    val buyerAddr: String?,

    @JsonProperty("buyer_postcode")
    val buyerPostcode: String?,

    @JsonProperty("custom_data")
    val customData: String?,

    @JsonProperty("user_agent")
    val userAgent: String?,

    @JsonProperty("status")
    val status: String,

    @JsonProperty("started_at")
    val startedAt: Int?,

    @JsonProperty("paid_at")
    val paidAt: Int?,

    @JsonProperty("failed_at")
    val failedAt: Int?,

    @JsonProperty("cancelled_at")
    val cancelledAt: Int?,

    @JsonProperty("fail_reason")
    val failReason: String?,

    @JsonProperty("cancel_reason")
    val cancelReason: String?,

    @JsonProperty("receipt_url")
    val receiptUrl: String?,

    @JsonProperty("cancel_receipt_urls")
    val cancelReceiptUrls: List<String>?,

    @JsonProperty("cash_receipt_issued")
    val cashReceiptIssued: Boolean?,

    @JsonProperty("customer_uid")
    val customerUid: String?,

    @JsonProperty("customer_uid_usage")
    val customerUidUsage: String?
)
