package com.sts.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.sts.entity.enums.PaymentMethod;
import com.sts.entity.enums.PaymentStatus;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dealer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Dealer dealer;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private Instant createdAt;

    // ✅ Default constructor (required by JPA)
    public Payment() {
    }

    // ✅ All-args constructor
    public Payment(Long id, Dealer dealer, BigDecimal amount, PaymentMethod method,
                   PaymentStatus status, Instant createdAt) {
        this.id = id;
        this.dealer = dealer;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.createdAt = createdAt;
    }

    // ✅ Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    // ✅ Optional: Builder-like helper (mimics Lombok @Builder)
    public static PaymentBuilder builder() {
        return new PaymentBuilder();
    }

    public static class PaymentBuilder {
        private Long id;
        private Dealer dealer;
        private BigDecimal amount;
        private PaymentMethod method;
        private PaymentStatus status;
        private Instant createdAt;

        public PaymentBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PaymentBuilder dealer(Dealer dealer) {
            this.dealer = dealer;
            return this;
        }

        public PaymentBuilder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public PaymentBuilder method(PaymentMethod method) {
            this.method = method;
            return this;
        }

        public PaymentBuilder status(PaymentStatus status) {
            this.status = status;
            return this;
        }

        public PaymentBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Payment build() {
            return new Payment(id, dealer, amount, method, status, createdAt);
        }
    }
}
