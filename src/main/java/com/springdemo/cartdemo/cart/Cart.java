package com.springdemo.cartdemo.cart;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CART")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long account_id; //회원 번호

    @Column
    private Long goods_id; //상품 번호

    @Column
    private Long count; //상품 수량
}
