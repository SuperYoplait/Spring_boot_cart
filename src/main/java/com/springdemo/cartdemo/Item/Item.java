package com.springdemo.cartdemo.Item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ITEM")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name; //상품명

    @Column
    private String categorie; //카테고리

    @Column
    private String context; //상품 설명

    @Column
    private String imgName; //상품 이미지

    @Column
    private String imgPath; //이미지 경로

    @Column
    private Long price; //상품 가격

    @Column
    private boolean sold; //판매 가능 여부

    @Column
    private Long count; //재고

    @Column
    private String Option; //상품 옵션

}
