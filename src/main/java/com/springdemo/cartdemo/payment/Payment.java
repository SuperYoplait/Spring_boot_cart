package com.springdemo.cartdemo.payment;

import java.time.LocalDateTime;

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
public class Payment {
    @Id
    @GeneratedValue
    private Long id;

    private Long cart_id;

    private Long account_id;

    private Long goods_id;

    private Long price;

    private LocalDateTime time;

    private boolean state;

    private String way;

    private String card;
}
