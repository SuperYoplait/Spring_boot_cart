package com.springdemo.cartdemo.payment;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.springdemo.cartdemo.account.Account;
import com.springdemo.cartdemo.account.CurrentUser;
import com.springdemo.cartdemo.cartitem.CartItem;
import com.springdemo.cartdemo.cartitem.CartItemRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService {

    private final CartItemRepository cartItemRepository;
    private final PaymentRepositroy paymentRepositroy;

    public void paymentProcess(@CurrentUser Account account, Model model, List<String> checkedVal) {

        List<Payment> newPayments = new ArrayList<Payment>();
        List<CartItem> delCartItem = new ArrayList<CartItem>();
        int checkedPay = 0;
        int totalprice = 0;
        String firstItem= null;
        CartItem newcartitem;
        for (String idx : checkedVal) {
            SimpleDateFormat idFormat1 = new SimpleDateFormat("yyyyMMddHHmmss");

            String idFormat = idFormat1.format(System.currentTimeMillis()) + Integer.toString(((int) Math.random() * 100));
            Payment newPaymentForm = new Payment();
            newcartitem = cartItemRepository.findById(Long.parseLong(idx)).get();

            
            newPaymentForm.setIdx(UUID.randomUUID());
            newPaymentForm.setPayment_id(Long.parseLong(idFormat));
            newPaymentForm.setItem_id(newcartitem.getItem().getId());
            newPaymentForm.setCart_id(newcartitem.getCart().getId());
            newPaymentForm.setItem_name(newcartitem.getItem().getName());
            newPaymentForm.setItem_option(newcartitem.getItem().getOption());
            newPaymentForm.setPrice(newcartitem.getItem().getPrice() * newcartitem.getCnt());
            newPaymentForm.setCnt(newcartitem.getCnt());
            newPaymentForm.setTime(LocalDateTime.now());
            newPaymentForm.setAccount_id(account.getUserid());
            newPaymentForm.setState(true);

            newPayments.add(newPaymentForm);
            delCartItem.add(newcartitem);

            totalprice += newPaymentForm.getPrice();
            if (firstItem == null) {
                firstItem = newPaymentForm.getItem_name();
            }
            checkedPay++;
        }


        paymentRepositroy.saveAll(newPayments);
        cartItemRepository.deleteAll(delCartItem);

        if(checkedPay < 1 ) {
            model.addAttribute("code", newPayments.get(0).getPayment_id());
            model.addAttribute("payitem", firstItem);
            model.addAttribute("price", totalprice);
            model.addAttribute("itemcnt", null);
        } else {
            model.addAttribute("code", newPayments.get(0).getPayment_id());
            model.addAttribute("payitem", firstItem);            
            model.addAttribute("price", totalprice);
            model.addAttribute("itemcnt", checkedPay - 1);
        }
    }

}
