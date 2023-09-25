package com.njhyuk.payment.core.subscription.domain

import org.springframework.data.jpa.repository.JpaRepository

interface SubscriptionRepository : JpaRepository<Subscription, Long>
