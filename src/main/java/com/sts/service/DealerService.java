package com.sts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sts.entity.Dealer;
import com.sts.entity.Payment;
import com.sts.repository.DealerRepository;
import com.sts.repository.PaymentRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DealerService {

    private final PaymentRepository paymentRepository;
	@Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    DealerService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Dealer registerDealer(Dealer dealer) {
        dealer.setPassword(passwordEncoder.encode(dealer.getPassword()));
        return dealerRepository.save(dealer);
    }

//    public Dealer login(String email, String password) {
//        Optional<Dealer> dealer = dealerRepository.findByEmail(email);
//        if (dealer.isPresent() && passwordEncoder.matches(password, dealer.get().getPassword())) {
//            return dealer.get();
//        } else {
//            throw new RuntimeException("Invalid email or password");
//        }
//    }


    public Dealer create(Dealer d) { return dealerRepository.save(d); }
    
    public Dealer update(Long id, Dealer changed) {
        Dealer existing = dealerRepository.findById(id).orElseThrow();
        existing.setName(changed.getName());
        existing.setEmail(changed.getEmail());
        existing.setSubscriptionType(changed.getSubscriptionType());
        return dealerRepository.save(existing);
    }
    public Dealer get(Long id) { return dealerRepository.findById(id).orElseThrow(); }
    public List<Dealer> list() { return dealerRepository.findAll(); }
    
    @Transactional
    public void deleteDealer(Long dealerId) {
        paymentRepository.deleteByDealerId(dealerId);
        dealerRepository.deleteById(dealerId);
    }

//    public void delete(Long id) { dealerRepository.deleteById(id); }
}

