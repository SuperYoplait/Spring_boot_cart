package com.springdemo.cartdemo.cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {
    @GetMapping("/my-cart")
    public String My_cart(){
        return "cart/CartList";
    }


}
