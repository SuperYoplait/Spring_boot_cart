package com.springdemo.cartdemo.Payment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
/* import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart; */
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/* import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf; */
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
//import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.context.WebApplicationContext;


//import org.springframework.http.MediaType;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.springdemo.cartdemo.Item.Item;
import com.springdemo.cartdemo.account.Account;
import com.springdemo.cartdemo.account.AccountRepositroy;
import com.springdemo.cartdemo.account.AccountService;
import com.springdemo.cartdemo.cartitem.CartItem;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
//@ActiveProfiles("dev")
public class paymentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private AccountRepositroy accountRepository;

    @Autowired
    private AccountService accountService;

    @BeforeEach //Test ID Login
    private void beforeEach_login() throws Exception {
        System.out.println("BEFORE===========================================================\n\n");
        mockMvc.perform(post("/account/sign-up")
                .param("userid", "test123")
                .param("password", "1111")
                .param("name", "test name")
                .param("email", "jangbayooffcial@gmail.com")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());
        System.out.println("BEFORE===========================================================\n\n");
    }

    @AfterEach
    private void AfterEach(){
        System.out.println("AFTER============================================================\n\n");
    }

    @DisplayName("상품결제 - 정상")
    @Test
    @WithUserDetails(value = "test123", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void item_payment_pass() throws Exception {
        
        Item newitem = new Item();
        
        newitem.builder()
                .id(1L)
                .price(10000L)
                .build();
        Account newAccount =  accountRepository.findByUserid("test123");
        CartItem newCartItem = new CartItem();
        newCartItem.builder()
                    .id(newAccount.getCart().getId())
                    .cart(newAccount.getCart())
                    .cnt(1L)
                    .item(newitem)
                    .build();
        List<String> checkedVal = new ArrayList<String>();

        checkedVal.add("1");
        mockMvc.perform(post("/payment/pay")
                .param("checkedVal", checkedVal.get(0))
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("pass"));
    }
    @DisplayName("상품결제 - 실패")
    @Test
    @WithUserDetails(value = "test123", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void item_payment_fail() throws Exception {
        

        Account newAccount =  accountRepository.findByUserid("test123");
        
       
        mockMvc.perform(post("/payment/pay")
                .param("checkedVal", "")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("error"));
    }

    
}
