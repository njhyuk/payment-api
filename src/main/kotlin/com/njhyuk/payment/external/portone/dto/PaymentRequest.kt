package com.njhyuk.payment.external.portone.dto

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 비 인증 결제(빌링키) API Request
 * @see <a href="https://developers.portone.io/api/rest-v1/nonAuthPayment#post%20%2Fsubscribe%2Fpayments%2Fagain">비 인증 결제(빌링키) API 문서</a>
 */
data class PaymentRequest(
    /**
     * 빌링키와 매핑되며 가맹점에서 채번하는 구매자의 결제 수단 식별 고유번호
     */
    @JsonProperty("customer_uid")
    val customerUid: String,

    /**
     * 가맹점 주문번호
     */
    @JsonProperty("merchant_uid")
    val merchantUid: String,

    /**
     * 결제 통화 코드
     * 통화 e.g.) KRW, USD, VND, ... Default: KRW
     */
    @JsonProperty("currency")
    val currency: String? = "KRW",

    /**
     * 결제금액
     */
    @JsonProperty("amount")
    val amount: Number,

    /**
     * 면세금액
     * amount 중 면세공급가액
     */
    @JsonProperty("tax_free")
    val taxFree: Number? = null,

    /**
     * 부가세
     * amount 중 부가세 금액
     */
    @JsonProperty("vat_amount")
    val vatAmount: Number? = null,

    /**
     * 제품명
     * 결제 요청할 결제건의 제품명
     */
    @JsonProperty("name")
    val name: String,

    /**
     * 주문자명
     */
    @JsonProperty("buyer_name")
    val buyerName: String? = null,

    /**
     * 주문자 E-mail 주소
     */
    @JsonProperty("buyer_email")
    val buyerEmail: String? = null,

    /**
     * 주문자 전화번호
     */
    @JsonProperty("buyer_tel")
    val buyerTel: String? = null,

    /**
     * 주문자 주소
     */
    @JsonProperty("buyer_addr")
    val buyerAddr: String? = null,

    /**
     * 주문자 우편번호
     */
    @JsonProperty("buyer_postcode")
    val buyerPostcode: String? = null,

    /**
     * 카드 할부 개월수
     * 결제건의 카드 할부 개월 수로 기본값은 0(일시불)입니다.
     */
    @JsonProperty("card_quota")
    val cardQuota: Int? = null,

    /**
     * 가맹점부담 무이자 할부여부
     * 카드할부처리할 때, 할부이자가 발생하는 경우(카드사 무이자 프로모션 제외) 부과되는 할부이자를 고객대신 가맹점이 지불하는지에 대한 여부 (PG사와 사전 계약이 필요)
     */
    @JsonProperty("interest_free_by_merchant")
    val interestFreeByMerchant: Boolean? = null,

    /**
     * 카드포인트 사용여부
     * 승인요청시 카드사 포인트 차감하며 결제승인처리할지 flag. PG사 영업담당자와 계약 당시 사전 협의 필요
     */
    @JsonProperty("use_card_point")
    val useCardPoint: Boolean? = null,

    /**
     * 빌링키 발급 시 같이 저장할 가맹점 custom 데이터
     * 거래정보와 함께 저장할 추가 정보
     */
    @JsonProperty("custom_data")
    val customData: String? = null,

    /**
     * Notification URL(Webhook URL)
     * 결제성공 시 통지될 Notification URL(Webhook URL)
     */
    @JsonProperty("notice_url")
    val noticeUrl: String? = null,

    /**
     * 브라우저의 IP
     * 구매자 브라우져(PC)의 IP
     */
    @JsonProperty("browser_ip")
    val browserIp: String? = null,

    /**
     * 주문 상품구분
     * 결제 요청할 판매 상품에 대한 구분 값
     */
    @JsonProperty("product_type")
    val productType: String? = null,

    /**
     * 결제 상품의 개수
     * 결제 상품의 개수로 기본값은 1입니다.
     */
    @JsonProperty("product_count")
    val productCount: Int? = 1,

    /**
     * bypass 데이터
     * 정규화하기 어려운 JSON string 형식의 PG사별 specific 파라미터
     */
    @JsonProperty("bypass")
    val bypass: String? = null
)
