package com.njhyuk.payment.core.payment.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import javax.persistence.LockModeType

interface PaymentRepository : JpaRepository<Payment, Long> {
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Payment p WHERE p.id = :id")
    fun findByIdWithLock(id: Long): Payment?
}
