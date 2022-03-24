package com.springdemo.cartdemo.payment;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PAYMENT")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long cart_id;

    @Column
    private Long account_id;

    @Column
    private Long goods_id;

    @Column
    private Long price;

    @Column
    private LocalDateTime time;

    @Column
    private boolean state;

    @Column
    private String way;

    @Column
    private String card;
}
