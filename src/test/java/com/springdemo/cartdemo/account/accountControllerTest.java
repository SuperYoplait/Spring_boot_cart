package com.springdemo.cartdemo.account;

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

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class accountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private AccountRoleRepository accountRoleRepository;

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

    @DisplayName("회원가입 - 정상")
    @Test
    public void SignupForm_pass() throws Exception {
        mockMvc.perform(post("/account/sign-up")
                .param("userid", "test123")
                .param("password", "1111")
                .param("name", "test name")
                .param("email", "jangbayooffcial@gmail.com")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("회원가입 - 실패")
    @Test
    public void SignupForm_fail() throws Exception {
        mockMvc.perform(post("/account/sign-up")
                .param("userid", "test12")
                .param("password", "1")
                .param("name", "test name")
                .param("email", "jangbayooffcial@gmail.com")
                .with(csrf()))
                .andExpect(status().isOk());
    }

    @DisplayName("로그인 - 성공")
    @Test
    public void Login_pass() throws Exception {
        mockMvc.perform(post("/account/login")
                .param("username", "test")
                .param("password", "1111")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("로그인 - 실패")
    @Test
    public void Login_fail() throws Exception {
        mockMvc.perform(get("/account/login?error")
                .param("username", "test")
                .param("password", "1234")
                .with(csrf()))
                .andExpect(status().isOk());
    }

    @DisplayName("이메일 인증 - 성공")
    @Test
    public void Email_pass() throws Exception {
        
    }
    
    @DisplayName("이메일 인증 - 실패")
    @Test
    public void Email_fail() throws Exception {
        
    }

    @WithUserDetails(value = "test123", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @DisplayName("권한 수정")
    @Test
    public void privateModify() throws Exception{
        String userid = "test123";

        List<AccountRole> roles = accountRoleRepository.findAll();

        List<Long> rolesValue = new ArrayList<>();

        for (AccountRole role : roles){
            rolesValue.add(role.getId());
        }

        mockMvc.perform(post("/account/auth-update/" + userid)
                        .content(rolesValue.toString())
                        .contentType("application/json")
                        .param("userid", userid)
                        .with(csrf())
                        )
                        .andExpect(status().isOk());
    }
}
