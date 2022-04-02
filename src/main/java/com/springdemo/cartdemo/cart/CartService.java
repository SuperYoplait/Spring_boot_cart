package com.springdemo.cartdemo.cart;

import java.util.List;
import java.util.Optional;

import com.springdemo.cartdemo.account.Account;
import com.springdemo.cartdemo.cartitem.CartItem;
import com.springdemo.cartdemo.cartitem.CartItemRepository;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartItemRepository cartItemRepository;
    private final CartRepositroy cartRepository;

    public void cart_list(Model model, Account account){
        List<CartItem> cartItems = cartItemRepository.findByCartId(account.getCart().getId());
        Optional<Cart> select_user = cartRepository.findById(account.getCart().getId());
        
<<<<<<< HEAD
        if(cartItems.isEmpty()){
=======
        if(!cartItems.isEmpty()){
>>>>>>> master
            System.out.println("\n\n장바구니 비었음");
        }else{
            model.addAttribute("cartlist", cartItems);
            
            for(CartItem item : cartItems){
                System.out.println("아이템 목록 : " + item.getItem().getName() + "\t아이템 가격" + item.getItem().getPrice());
            }
<<<<<<< HEAD
            System.out.println("\n전체 상품 가격 : " + select_user.get().getSum_price());
=======
>>>>>>> master
        }

        System.out.println("\n\n 상품 가격" + select_user.get().getSum_price());
        model.addAttribute("totalprice", select_user.get().getSum_price());

    }
}
