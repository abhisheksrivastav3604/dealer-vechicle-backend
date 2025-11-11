package com.sts.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sts.entity.Payment;
import com.sts.entity.enums.PaymentMethod;
import com.sts.service.PaymentService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService){ this.paymentService = paymentService; }

    @PostMapping("/initiate")
    public ResponseEntity<Payment> initiate(@RequestBody Map<String, String> body){
        Long dealerId = Long.parseLong(body.get("dealerId"));
        BigDecimal amount = new BigDecimal(body.get("amount"));
        PaymentMethod method = PaymentMethod.valueOf(body.get("method").toUpperCase());
        Payment p = paymentService.initiate(dealerId, amount, method);
        return ResponseEntity.ok(p);
    }
    
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }
}

