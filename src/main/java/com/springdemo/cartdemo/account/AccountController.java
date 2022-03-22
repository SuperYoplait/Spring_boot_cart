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
        return "account/Login";
    }

    //회원 정보 수정
    @GetMapping("/info")
    public String Info_Update_GET(){
        return "account/Info";
    }
    @PostMapping("/info")
    public String Info_Update_Post(){
        return "";
    }

    //회원 권한 수정
    @GetMapping("/auth-update")
    public String Auth_Update_GET(){
        return "account/AuthUpdate";
    }
    @PostMapping("/auth-update")
    public String Auth_Update_POST(){
        return "";
    }

}
