package com.springdemo.cartdemo.cartitem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.springdemo.cartdemo.Item.Item;
import com.springdemo.cartdemo.cart.Cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long cnt;

    @OneToOne // 상품에 대한 1:1 맵핑
    private Item item;

    @ManyToOne
    private Cart cart;
}