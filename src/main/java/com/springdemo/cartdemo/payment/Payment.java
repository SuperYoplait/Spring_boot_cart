package com.springdemo.cartdemo.payment;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

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
    private UUID idx;

    private Long payment_id;

    private Long cart_id;

    private Long goods_id;

    private String goods_name;

    private String goods_option;

    private Long price;

    private Long cnt;

    private LocalDateTime time;

    private boolean state;

    private String addres;

    private String log;
}
