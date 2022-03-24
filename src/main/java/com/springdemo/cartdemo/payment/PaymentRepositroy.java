package com.springdemo.cartdemo.payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepositroy extends JpaRepository<Payment, Long>{
    
}
