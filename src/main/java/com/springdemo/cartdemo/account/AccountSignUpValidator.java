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
        // TODO Auto-generated method stub
        return clazz.isAssignableFrom(AccountSignUpForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // TODO Auto-generated method stub

        AccountSignUpForm signupform = (AccountSignUpForm) target;

        if (accountRepository.existsByEmail(signupform.getEmail())) {
            errors.rejectValue("email", "invalid.email", new Object[] { signupform.getEmail() }, "이미 사용중인 이메일 입니다.");

        }

        if (accountRepository.existsByNickname(signupform.getNickname())) {
            errors.rejectValue("nickname", "invalid.nickname", new Object[] { signupform.getNickname() }, "이미 사용중인 아이디 입니다.");

        }
        
    }

}

