package com.njhyuk.payment.core.subscription.exception

import com.njhyuk.payment.config.handler.ErrorCode
import com.njhyuk.payment.exception.BusinessException

class SubscriptionNotFoundException : BusinessException(ErrorCode.SUBSCRIPTION_NOT_FOUND)
