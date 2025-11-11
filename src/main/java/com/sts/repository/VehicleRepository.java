package com.sts.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sts.entity.Vehicle;
import com.sts.entity.enums.SubscriptionType;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	
	
	 List<Vehicle> findByDealerId(Long dealerId);
	 
	 @Query("SELECT v FROM Vehicle v WHERE v.dealer.subscriptionType = com.sts.entity.enums.SubscriptionType.PREMIUM")
	    List<Vehicle> findAllByPremiumDealers();

	
	
	@Query("SELECT v FROM Vehicle v WHERE v.dealer.subscriptionType = :subscriptionType")
    List<Vehicle> findAllByDealerSubscriptionType(@Param("subscriptionType") SubscriptionType subscriptionType);
}

