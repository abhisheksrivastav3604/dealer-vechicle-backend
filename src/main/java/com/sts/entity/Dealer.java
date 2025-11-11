package com.sts.entity;

import com.sts.entity.enums.SubscriptionType;
import jakarta.persistence.*;

@Entity
@Table(name = "dealer")
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    // ✅ Store hashed password (never plain text)
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    // ✅ Default constructor
    public Dealer() {
    }

    // ✅ All-args constructor
    public Dealer(Long id, String name, String email, String password, SubscriptionType subscriptionType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.subscriptionType = subscriptionType;
    }

    // ✅ Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    // Never expose or log raw passwords!
    public void setPassword(String password) {
        this.password = password;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    // ✅ Builder pattern
    public static DealerBuilder builder() {
        return new DealerBuilder();
    }

    public static class DealerBuilder {
        private Long id;
        private String name;
        private String email;
        private String password;
        private SubscriptionType subscriptionType;

        public DealerBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DealerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DealerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public DealerBuilder password(String password) {
            this.password = password;
            return this;
        }

        public DealerBuilder subscriptionType(SubscriptionType subscriptionType) {
            this.subscriptionType = subscriptionType;
            return this;
        }

        public Dealer build() {
            return new Dealer(id, name, email, password, subscriptionType);
        }
    }
}
