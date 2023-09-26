package com.njhyuk.payment.external.portone.dto

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 결제 취소 API Request
 *
 * @see <a href="https://developers.portone.io/api/rest-v1/payment#post%20%2Fpayments%2Fcancel">결제취소 API 문서</a>
 */
data class PaymentCancelRequest(
    /**
     * 포트원 거래고유번호.
     */
    @JsonProperty("imp_uid")
    val impUid: String? = null,

    /**
     * 가맹점 주문번호.
     */
    @JsonProperty("merchant_uid")
    val merchantUid: String? = null,

    /**
     * (부분)취소 요청금액. 누락되면 전액취소를 요청합니다.
     */
    @JsonProperty("amount")
    val amount: Number? = null,

    /**
     * (부분)취소요청금액 중 면세금액. 누락되면 0원처리합니다.
     */
    @JsonProperty("tax_free")
    val taxFree: Number? = null,

    /**
     * (부분)취소요청금액 중 부가세 금액. 기본값은 null입니다.
     */
    @JsonProperty("vat_amount")
    val vatAmount: Number? = null,

    /**
     * 취소 트랜잭션 수행 전, 현재시점의 취소 가능한 잔액.
     */
    @JsonProperty("checksum")
    val checksum: Number? = null,

    /**
     * 결제건을 취소하려는 사유.
     */
    @JsonProperty("reason")
    val reason: String? = null,

    /**
     * 환불계좌 예금주.
     */
    @JsonProperty("refund_holder")
    val refundHolder: String? = null,

    /**
     * 환금계좌 은행코드.
     */
    @JsonProperty("refund_bank")
    val refundBank: String? = null,

    /**
     * 환불계좌 계좌번호.
     */
    @JsonProperty("refund_account")
    val refundAccount: String? = null,

    /**
     * 환불계좌 예금주 연락처. 가상계좌 취소인 경우 필수.
     */
    @JsonProperty("refund_tel")
    val refundTel: String? = null
)
