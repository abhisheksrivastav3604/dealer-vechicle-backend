package com.sts.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import com.sts.entity.enums.VehicleStatus;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dealer_id")
    private Dealer dealer;

    // ✅ Default constructor (required by JPA)
    public Vehicle() {
    }

    // ✅ All-args constructor
    public Vehicle(Long id, String model, BigDecimal price, VehicleStatus status, Dealer dealer) {
        this.id = id;
        this.model = model;
        this.price = price;
        this.status = status;
        this.dealer = dealer;
    }

    // ✅ Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    // ✅ Optional builder-style class (Lombok @Builder equivalent)
    public static VehicleBuilder builder() {
        return new VehicleBuilder();
    }

    public static class VehicleBuilder {
        private Long id;
        private String model;
        private BigDecimal price;
        private VehicleStatus status;
        private Dealer dealer;

        public VehicleBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public VehicleBuilder model(String model) {
            this.model = model;
            return this;
        }

        public VehicleBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public VehicleBuilder status(VehicleStatus status) {
            this.status = status;
            return this;
        }

        public VehicleBuilder dealer(Dealer dealer) {
            this.dealer = dealer;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(id, model, price, status, dealer);
        }
    }
}
