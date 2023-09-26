package com.njhyuk.payment.external.portone.dto

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 결제/취소 API Response
 *
 * @see <a href="https://developers.portone.io/api/rest-v1/type-def#PaymentAnnotation">PaymentAnnotation 문서</a>
 */
data class PaymentResponse(
    /**
     * 포트원 거래고유번호 (필수)
     */
    @JsonProperty("imp_uid")
    val impUid: String,

    /**
     * 가맹점 주문번호 (필수)
     */
    @JsonProperty("merchant_uid")
    val merchantUid: String,

    /**
     * 결제수단 구분코드 (선택)
     */
    @JsonProperty("pay_method")
    val payMethod: String?,

    /**
     * 결제환경 구분코드 (선택)
     */
    @JsonProperty("channel")
    val channel: String?,

    /**
     * PG사 구분코드 (선택)
     */
    @JsonProperty("pg_provider")
    val pgProvider: String?,

    /**
     * 허브형결제 PG사 구분코드 (선택)
     */
    @JsonProperty("emb_pg_provider")
    val embPgProvider: String?,

    /**
     * PG사 거래번호 (선택)
     */
    @JsonProperty("pg_tid")
    val pgTid: String?,

    /**
     * PG사 상점아이디 (선택)
     */
    @JsonProperty("pg_id")
    val pgId: String?,

    /**
     * 에스크로결제 여부 (선택)
     */
    @JsonProperty("escrow")
    val escrow: Boolean?,

    /**
     * 승인번호 (선택)
     */
    @JsonProperty("apply_num")
    val applyNum: String?,

    /**
     * 은행 표준코드 (선택)
     */
    @JsonProperty("bank_code")
    val bankCode: String?,

    /**
     * 은행명 (선택)
     */
    @JsonProperty("bank_name")
    val bankName: String?,

    /**
     * 카드사 코드번호 (선택)
     */
    @JsonProperty("card_code")
    val cardCode: String?,

    /**
     * 카드사명 (선택)
     */
    @JsonProperty("card_name")
    val cardName: String?,

    /**
     * 할부개월 수 (선택)
     */
    @JsonProperty("card_quota")
    val cardQuota: Int?,

    /**
     * 결제에 사용된 마스킹된 카드번호 (선택)
     */
    @JsonProperty("card_number")
    val cardNumber: String?,

    /**
     * 결제건에 사용된 카드 구분코드 (선택)
     */
    @JsonProperty("card_type")
    val cardType: Int?,

    /**
     * 가상계좌 은행 표준코드 (선택)
     */
    @JsonProperty("vbank_code")
    val vbankCode: String?,

    /**
     * 가상계좌 은행명 (선택)
     */
    @JsonProperty("vbank_name")
    val vbankName: String?,

    /**
     * 가상계좌 계좌번호 (선택)
     */
    @JsonProperty("vbank_num")
    val vbankNum: String?,

    /**
     * 가상계좌 예금주 (선택)
     */
    @JsonProperty("vbank_holder")
    val vbankHolder: String?,

    /**
     * 가상계좌 입금기한 (선택)
     */
    @JsonProperty("vbank_date")
    val vbankDate: Int?,

    /**
     * 가상계좌 생성시각 UNIX timestamp (선택)
     */
    @JsonProperty("vbank_issued_at")
    val vbankIssuedAt: Int?,

    /**
     * 제품명 (선택)
     */
    @JsonProperty("name")
    val name: String?,

    /**
     * 결제금액 (필수)
     */
    @JsonProperty("amount")
    val amount: Long,

    /**
     * 취소금액 (필수)
     */
    @JsonProperty("cancel_amount")
    val cancelAmount: Long,

    /**
     * 결제통화 구분코드 (필수)
     */
    @JsonProperty("currency")
    val currency: String,

    /**
     * 주문자명 (선택)
     */
    @JsonProperty("buyer_name")
    val buyerName: String?,

    /**
     * 주문자 Email주소 (선택)
     */
    @JsonProperty("buyer_email")
    val buyerEmail: String?,

    /**
     * 주문자 전화번호 (선택)
     */
    @JsonProperty("buyer_tel")
    val buyerTel: String?,

    /**
     * 주문자 주소 (선택)
     */
    @JsonProperty("buyer_addr")
    val buyerAddr: String?,

    /**
     * 주문자 우편번호 (선택)
     */
    @JsonProperty("buyer_postcode")
    val buyerPostcode: String?,

    /**
     * 결제 요청시 가맹점에서 전달한 추가정보 (JSON string으로 전달) (선택)
     */
    @JsonProperty("custom_data")
    val customData: String?,

    /**
     * 구매자가 결제시 사용한 단말기의 UserAgent 문자열 (선택)
     */
    @JsonProperty("user_agent")
    val userAgent: String?,

    /**
     * 결제상태 (필수)
     */
    @JsonProperty("status")
    val status: String,

    /**
     * 결제요청 시각 UNIX timestamp (선택)
     */
    @JsonProperty("started_at")
    val startedAt: Int?,

    /**
     * 결제완료 시각 UNIX timestamp (선택)
     */
    @JsonProperty("paid_at")
    val paidAt: Int?,

    /**
     * 결제실패시각 UNIX timestamp (선택)
     */
    @JsonProperty("failed_at")
    val failedAt: Int?,

    /**
     * 결제취소시각 UNIX timestamp (선택)
     */
    @JsonProperty("cancelled_at")
    val cancelledAt: Int?,

    /**
     * 결제실패 사유 (선택)
     */
    @JsonProperty("fail_reason")
    val failReason: String?,

    /**
     * 결제취소 사유 (선택)
     */
    @JsonProperty("cancel_reason")
    val cancelReason: String?,

    /**
     * 매출전표 URL로 PG사 또는 결제 수단에 따라 매출전표가 없을 수 있습니다. (선택)
     */
    @JsonProperty("receipt_url")
    val receiptUrl: String?,

    /**
     * 결제건의 취소/부분취소 내역 (선택)
     */
    @JsonProperty("cancel_history")
    val cancelHistory: List<PaymentCancelResponse>?,

    /**
     * 취소 매출전표 URL (선택)
     */
    @JsonProperty("cancel_receipt_urls")
    val cancelReceiptUrls: List<String>?,

    /**
     * 현금영수증 발급 여부 (선택)
     */
    @JsonProperty("cash_receipt_issued")
    val cashReceiptIssued: Boolean?,

    /**
     * 구매자의 결제 수단 식별 고유번호 (선택)
     */
    @JsonProperty("customer_uid")
    val customerUid: String?,

    /**
     * 구매자의 결제 수단 식별 고유번호 사용 구분코드 (선택)
     */
    @JsonProperty("customer_uid_usage")
    val customerUidUsage: String?
)

/**
 * 결제 취소 내역
 * @see <a href="https://developers.portone.io/api/rest-v1/type-def#PaymentCancelAnnotation">PaymentCancelAnnotation 문서</a>
 */
data class PaymentCancelResponse(
    /**
     * PG사 승인취소번호
     */
    @JsonProperty("pg_tid")
    val pgTid: String,

    /**
     * 결제건의 취소 금액
     */
    @JsonProperty("amount")
    val amount: Double,

    /**
     * 결제건의 결제취소된 시각 UNIX timestamp
     */
    @JsonProperty("cancelled_at")
    val cancelledAt: Long,

    /**
     * 결제건의 결제취소 사유
     */
    @JsonProperty("reason")
    val reason: String,

    /**
     * 결제건의 취소 매출전표 확인 URL로 PG사, 결제 수단에 따라 제공되지 않을 수 있습니다.
     */
    @JsonProperty("receipt_url")
    val receiptUrl: String?
)
