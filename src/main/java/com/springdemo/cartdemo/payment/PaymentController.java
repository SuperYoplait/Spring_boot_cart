package com.springdemo.cartdemo.payment;

import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @PostMapping("/pay")
    public String Payment(){
        return "payment/Payment";
    }
}
