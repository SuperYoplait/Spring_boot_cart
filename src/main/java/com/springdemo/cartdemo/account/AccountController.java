package com.springdemo.cartdemo.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    //회원가입
    @GetMapping("/sign-up")
    public String SignUp_GET(){
        return "account/SignUp";
    }
    @PostMapping("/sign-up")
    public String SignUp_POST(){
        return "";
    }

    //로그인
    @GetMapping("/login")
    public String Login(){
        return "";
    }
}
