package com.sts.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sts.entity.Dealer;
import com.sts.entity.Payment;
import com.sts.entity.enums.PaymentMethod;
import com.sts.entity.enums.PaymentStatus;
import com.sts.repository.DealerRepository;
import com.sts.repository.PaymentRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final DealerRepository dealerRepository;

    public PaymentService(PaymentRepository paymentRepository, DealerRepository dealerRepository) {
        this.paymentRepository = paymentRepository;
        this.dealerRepository = dealerRepository;
    }

    public Payment initiate(Long dealerId, BigDecimal amount, PaymentMethod method) {
        Dealer d = dealerRepository.findById(dealerId).orElseThrow();
        Payment p = Payment.builder()
                .dealer(d)
                .amount(amount)
                .method(method)
                .status(PaymentStatus.PENDING)
                .createdAt(Instant.now())
                .build();
        p = paymentRepository.save(p);

        // simulate callback after 5 seconds
        simulateCallback(p.getId());
        return p;
    }
    
 
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }

    @Async
    void simulateCallback(Long paymentId) {
        try {
            Thread.sleep(5000L);
            paymentRepository.findById(paymentId).ifPresent(p -> {
                p.setStatus(PaymentStatus.SUCCESS);
                paymentRepository.save(p);
            });
        } catch (InterruptedException ignored) {}
    }
}

