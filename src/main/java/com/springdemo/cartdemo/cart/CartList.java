package com.springdemo.cartdemo.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartList {
    private Long id; //상품 pk

    private String name; //상품 이름

    private String imgName; //상품 이미지

    private String imgPath; //상품 이미지 경로

    private Long cnt; // 상품 갯수

    private String option; // 상품 옵션

    private Long price; //상품 가격
}
