package com.njhyuk.payment.core.card.domain

import org.springframework.data.jpa.repository.JpaRepository

interface CardRepository : JpaRepository<Card, Long> {
    fun findByIdAndUserId(id: Long, userId: String): Card?
}
