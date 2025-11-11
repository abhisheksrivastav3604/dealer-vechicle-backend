package com.sts.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.sts.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	void deleteByDealerId(Long dealerId);

}