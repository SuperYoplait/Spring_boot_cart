package com.springdemo.cartdemo.cart;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.springdemo.cartdemo.account.AccountService;
import com.springdemo.cartdemo.account.AccountSignUpForm;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class cartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountService accountService;

    @BeforeEach //Test ID Login
    private void beforeEach_signup() throws Exception {
        System.out.println("BEFORE===========================================================\n\n");
        AccountSignUpForm signUpForm = new AccountSignUpForm();
        signUpForm.setUserid("test123");
        signUpForm.setPassword("1111");
        signUpForm.setName("test123");
        signUpForm.setEmail("test@test.com");
        accountService.signUp(signUpForm);
        System.out.println("BEFORE===========================================================\n\n");
    }

    @AfterEach
    private void AfterEach(){
        System.out.println("AFTER============================================================\n\n");
    }

    @DisplayName("장바구니")
    @Test
    @WithUserDetails(value = "test123", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void Email_pass() throws Exception {
        mockMvc.perform(get("/cart/my-cart")
                .with(csrf()))
                .andExpect(status().isOk());
    }
    
}
