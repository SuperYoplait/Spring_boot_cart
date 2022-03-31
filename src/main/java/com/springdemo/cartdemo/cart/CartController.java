package com.springdemo.cartdemo.cart;

import java.util.List;
import java.util.Optional;

import com.springdemo.cartdemo.cartitem.CartItem;
import com.springdemo.cartdemo.cartitem.CartItemRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartItemRepository cartItemRepository;
    private final CartRepositroy cartRepository;

    private final Long id = 3080L;
    @GetMapping("/my-cart")
    public String my_cart(Model model){ // id 불러오기
        List<CartItem> select_cartId = cartItemRepository.findByCartId(id);
        Optional<Cart> select_user = cartRepository.findById(id);
        
        if(select_cartId.isEmpty()){
            System.out.println("비었음");
        }else{
            for(CartItem item : select_cartId){
                System.out.println("아이템 목록 : " + item.getItem().getName() + "\t아이템 가격" + item.getItem().getPrice());
            }
            System.out.println("\n전체 상품 가격 : " + select_user.get().getSum_price());
        }
        return "my-cart";
    }
}
