package com.njhyuk.payment.core.payment.domain

import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<Transaction, Long>
