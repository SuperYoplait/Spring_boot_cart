package com.springdemo.cartdemo.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "ROLE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role{

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String role_name;

    @Column(unique = true)
    private String authority;
}
