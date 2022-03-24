package com.springdemo.cartdemo.cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepositroy extends JpaRepository<Cart, Long>{
    
}
