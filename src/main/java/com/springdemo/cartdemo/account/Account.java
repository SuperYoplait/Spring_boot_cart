package com.springdemo.cartdemo.account;

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
@Table(name = "ACCOUNT")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private Long number;

    @Column(unique = true)
    private String userid;
    
    @Column
    private String name;

    @Column
    private String Email;
    
    @Column
    private String Password;
    
    @Column
    private String token;
    
    @Column
    private LocalDateTime time;
    
    @Column(columnDefinition = "boolean default false")
    private Boolean token_bool;
}
