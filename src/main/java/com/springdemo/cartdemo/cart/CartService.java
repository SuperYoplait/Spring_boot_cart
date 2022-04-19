package com.springdemo.cartdemo.cart;

import java.util.ArrayList;
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

    public void cart_list(Model model, Account account) {
        List<CartItem> cartItems = cartItemRepository.findByCartId(account.getCart().getId());
        Optional<Cart> select_user = cartRepository.findById(account.getCart().getId());

        List<CartList> view_cartlist = new ArrayList<CartList>(); // view에 뿌려주기 위해 데이터 재 가공

        if (!cartItems.isEmpty()) {
            for (CartItem item : cartItems) {
                CartList temp_list_form = new CartList();
                temp_list_form.setId(item.getId());
                temp_list_form.setName(item.getItem().getName());
                temp_list_form.setImgName(item.getItem().getImgName());
                temp_list_form.setImgPath(item.getItem().getImgPath());
                temp_list_form.setCnt(item.getCnt());
                temp_list_form.setOption(item.getItem().getOption());
                temp_list_form.setPrice(item.getItem().getPrice());
               
                view_cartlist.add(temp_list_form);
            }
            model.addAttribute("cartlist", view_cartlist);
        }
        model.addAttribute("totalprice", select_user.get().getSum_price());
    }
}