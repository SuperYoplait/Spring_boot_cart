package com.springdemo.cartdemo.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfoForm {
    private Long id;

    private String userid;

    private String password;

    private String name;

    private String email;

    //private String auth;
}
