package com.springdemo.cartdemo.cart;

import java.util.List;
import java.util.Optional;

import com.springdemo.cartdemo.account.Account;
import com.springdemo.cartdemo.account.AccountRepositroy;
import com.springdemo.cartdemo.account.CurrentUser;
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

    @GetMapping("/my-cart")
    public String my_cart(Model model, @CurrentUser Account account){ // id 불러오기
        //Optional<Account> user_data = accountRepositroy.findById(account.getId());


        List<CartItem> cartItems = cartItemRepository.findByCartId(account.getId());
        Optional<Cart> select_user = cartRepository.findById(account.getId());

        
        if(cartItems.isEmpty()){
            System.out.println("\n\n장바구니 비었음");
            //model.addAttribute("cartlist", "장바구니가 비어있습니다.");
        }else{
            model.addAttribute("cartlist", cartItems);
            
            for(CartItem item : cartItems){
                System.out.println("아이템 목록 : " + item.getItem().getName() + "\t아이템 가격" + item.getItem().getPrice());
            }
            System.out.println("\n전체 상품 가격 : " + select_user.get().getSum_price());
        }
        return "cart/my-cart";
    }
}
