package com.springdemo.cartdemo.Item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String name; //상품명

    private String categorie; //카테고리

    private String context; //상품 설명

    private String imgName; //상품 이미지

    private String imgPath; //이미지 경로

    private Long price; //상품 가격

    private boolean sold; //판매 가능 여부

    private Long count; //재고

    private String Option; //상품 옵션
}
