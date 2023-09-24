package com.njhyuk.payment.core.payment.exception

import com.njhyuk.payment.config.handler.ErrorCode
import com.njhyuk.payment.exception.BusinessException

class NotFoundException : BusinessException(ErrorCode.CARD_NOT_FOUND)
