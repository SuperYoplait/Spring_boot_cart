package com.springdemo.cartdemo.cart;

import com.springdemo.cartdemo.account.Account;
import com.springdemo.cartdemo.account.CurrentUser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/my-cart")
    public String my_cart(Model model, @CurrentUser Account account) {
        if (account != null) {
            cartService.cart_list(model, account);
        } else {
            model.addAttribute("error", "에러발생 (로그인관련)");
        }
        return "cart/my-cart";
    }
}
