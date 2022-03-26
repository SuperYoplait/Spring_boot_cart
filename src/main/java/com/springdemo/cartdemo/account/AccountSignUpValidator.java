package com.springdemo.cartdemo.account;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountSignUpValidator implements Validator {
    private final AccountRepositroy accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(AccountSignUpForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountSignUpForm signupform = (AccountSignUpForm) target;

        if (accountRepository.existsByEmail(signupform.getEmail())) {
            errors.rejectValue("email", "invalid.email", new Object[] { signupform.getEmail() }, "이미 사용중인 이메일 입니다.");
        }

        if (accountRepository.existsByUserid(signupform.getUserid())) {
            errors.rejectValue("nickname", "invalid.nickname", new Object[] { signupform.getUserid() }, "이미 사용중인 아이디 입니다.");
        }
    }
}

