package com.njhyuk.payment.exception

import com.njhyuk.payment.config.handler.ErrorCode

abstract class BusinessException(
    errorCode: ErrorCode
) : RuntimeException(errorCode.message)
