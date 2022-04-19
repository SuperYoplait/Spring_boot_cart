package com.springdemo.cartdemo.Item;

import static com.springdemo.cartdemo.Item.ItemController.ROOT;
import static com.springdemo.cartdemo.Item.ItemController.ITEM;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.File;
import java.io.FileInputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

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

    //아이템 등록 실패
    @DisplayName("상품등록 - 실패")
    @Test
    void item_insert_fail() throws Exception {
        String fileName = "imgFile";
        
        //file path
        //File file = new File("D:/gitproject/Spring_boot_cart/src/main/resources/static/img/images.png"); //home desk top - dongbin
        //File file = new File("D:/spring_test/cartdemo/src/main/resources/static/img/images.png"); // laptop - junho
        File file = new File("/Users/macbookair/Documents/GitHub/Spring_boot_cart/src/main/resources/static/img/images.png"); // macbook air - dongbin
        
        MockMultipartFile image = new MockMultipartFile(fileName, new FileInputStream(file));
        
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(
                multipart("/item/item-add").file(image)
                        .param("name", "test")
                        .param("context", "과일 상품 설명 : " )
                        .param("price", "10000")
                        .param("count", "10")
                        .param("categorie", "fruit")
                        .param("sold", "true")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/item/item-add"));
    }

    @DisplayName("상품등록 - 정상")
    @Test
    void item_insert_true() throws Exception {
        //String[] array = {"fruit" , "greens" , "milk" , "instant" , "beverage" , "seasoning" , "snacks" , "infant"};
        String fileName = "imgFile";
        
        //file path
        //File file = new File("D:/gitproject/Spring_boot_cart/src/main/resources/static/img/images.png"); //home desk top - dongbin
        //File file = new File("D:/spring_test/cartdemo/src/main/resources/static/img/images.png"); // laptop - junho
        File file = new File("/Users/macbookair/Documents/GitHub/Spring_boot_cart/src/main/resources/static/img/images.png"); // macbook air - dongbin
        
        MockMultipartFile image = new MockMultipartFile(fileName, new FileInputStream(file));
        
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(
                multipart("/item/item-add").file(image)
                        .param("name", "과일 test")
                        .param("context", "과일 상품 설명 : " )
                        .param("price", "10000")
                        .param("count", "10")
                        .param("categorie", "fruit")
                        .param("sold", "true")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

    

    @DisplayName("상품장바구니 담기 - 정상")
    @Test
    @WithUserDetails(value = "test123", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void item_insert_cart_pass() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        
        ItemInsertForm itemInsertForm = ItemInsertForm.builder()
                .itemId(1L)
                .cnt(1L)
                .build();

        mockMvc.perform(post(ROOT + ITEM + "/init-cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemInsertForm))
                .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(authenticated().withUsername("test123"));
    }


    @DisplayName("상품 보기 - 정상")
    @Test
    public void Item_detail_pass() throws Exception {
        mockMvc.perform(get("/item/detail")
                .param("id", "1")
                .with(csrf()))
                .andExpect(status().isOk());
    }
}
