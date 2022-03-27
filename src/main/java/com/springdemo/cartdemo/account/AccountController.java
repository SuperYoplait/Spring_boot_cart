package com.springdemo.cartdemo.account;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Error;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountRepositroy accountRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final AccountSignUpValidator AccountSignUpValidator;

    @InitBinder("AccountSignUpForm")
    public void InitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(AccountSignUpValidator);
    }

    // 회원가입
    @GetMapping("/sign-up")
    public String SignUp_GET(Model model) {
        model.addAttribute("signUpForm", new AccountSignUpForm());
        return "account/SignUp";
    }

    @PostMapping("/sign-up")
    public String SignUp_POST(@Valid AccountSignUpForm signUpForm, Errors errors) {
        System.out.println("\n\n"+ signUpForm + "\n\n");
        if (errors.hasErrors()) {
            return "login/sign-up";
        }
        accountService.signUp(signUpForm);
        return "redirect:/";
    }

    // 로그인
    @GetMapping("/login")
    public String Login() {
        return "account/Login";
    }

    // 회원 정보 수정
    @GetMapping("/info")
    public String Info_Update_GET() {
        return "account/Info";
    }

    @PostMapping("/info")
    public String Info_Update_Post() {
        return "";
    }

    // 회원 권한 수정
    @GetMapping("/auth-update")
    public String Auth_Update_GET() {
        return "account/AuthUpdate";
    }

    @PostMapping("/auth-update")
    public String Auth_Update_POST() {
        return "";
    }

}
