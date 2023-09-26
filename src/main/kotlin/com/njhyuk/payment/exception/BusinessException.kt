package com.njhyuk.payment.exception

import com.njhyuk.payment.config.handler.ErrorCode

open class BusinessException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)
