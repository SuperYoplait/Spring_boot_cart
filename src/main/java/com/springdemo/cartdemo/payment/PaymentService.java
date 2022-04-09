package com.springdemo.cartdemo.payment;

import java.util.List;

import com.springdemo.cartdemo.account.Account;
import com.springdemo.cartdemo.account.CurrentUser;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService {
    public void paymentProcess(@CurrentUser Account account, Model model, List<String> checkedVal) {

        for (String idx : checkedVal) {
        
        }
    }
    
}
