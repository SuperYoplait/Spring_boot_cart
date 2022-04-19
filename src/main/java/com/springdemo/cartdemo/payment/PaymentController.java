package com.springdemo.cartdemo.payment;

import java.util.List;

import com.springdemo.cartdemo.account.Account;
import com.springdemo.cartdemo.account.CurrentUser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentservice;

    @PostMapping("/pay")
    public String Payment(@CurrentUser Account account, Model model, @RequestParam List<String> checkedVal) {
        if(!checkedVal.isEmpty()) {
            paymentservice.paymentProcess(account, model, checkedVal);
            return "payment/Payment";
        }
        model.addAttribute("error", "부적절한 경로입니다. :(");
        return "account/emailcheck"; //재사용
        
    }
}
