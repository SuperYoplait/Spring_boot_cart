package com.springdemo.cartdemo.cartitem;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    List<CartItem> findByCartId(Long id);
    Optional<CartItem> findByItemId(Long id);
}
