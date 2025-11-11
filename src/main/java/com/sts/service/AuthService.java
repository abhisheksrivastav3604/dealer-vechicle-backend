package com.sts.service;


import com.sts.entity.Dealer;
import com.sts.entity.enums.SubscriptionType;
import com.sts.repository.DealerRepository;
import com.sts.config.JwtUtil;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	
	@Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
	
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(DealerRepository dealerRepository, JwtUtil jwtUtil) {
        this.dealerRepository = dealerRepository;
        this.jwtUtil = jwtUtil;
    }

    public Dealer register(String name, String email, String password, SubscriptionType subscriptionType) {
        Dealer dealer = new Dealer();
        dealer.setName(name);
        dealer.setEmail(email);
        dealer.setPassword(encoder.encode(password));
        dealer.setSubscriptionType(subscriptionType);
        return dealerRepository.save(dealer);
    }

    public Dealer login(String email, String password) {
    	Optional<Dealer> dealer = dealerRepository.findByEmail(email);
        if (dealer.isPresent() && passwordEncoder.matches(password, dealer.get().getPassword())) {
            return dealer.get();
        } else {
            throw new RuntimeException("Invalid email or password");
        }

        
    }
    
}

