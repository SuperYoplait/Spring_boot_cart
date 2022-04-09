package com.springdemo.cartdemo.Item;

import static com.springdemo.cartdemo.Item.ItemController.ROOT;
import static com.springdemo.cartdemo.Item.ItemController.ITEM;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

    @DisplayName("상품등록 - 정상")
    @Test
    void item_insert_true() throws Exception {
        String[] array = {"fruit" , "greens" , "milk" , "instant" , "beverage" , "seasoning" , "snacks" , "infant"};
        String fileName = "imgFile";
        File file = new File("D:/gitproject/Spring_boot_cart/src/main/resources/static/img/images.png");
        //File file = new File("D:/spring_test/cartdemo/src/main/resources/static/img/images.png");
        //File file = new File("/Users/macbookair/Documents/GitHub/Spring_boot_cart/src/main/resources/static/img/images.png");
        
        MockMultipartFile image = new MockMultipartFile(fileName, new FileInputStream(file));
        
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        for(int i = 0; i<10; i++){
            mockMvc.perform(
                multipart("/item/item-add").file(image)
                .param("name","과일 test" + (i+1))
                .param("context","과일 상품 설명 : " + (i+1))
                .param("price","10000")
                .param("count","10")
                .param("categorie", array[0])
                .param("sold","true")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());
        }
    }

    @DisplayName("상품장바구니 담기 - 정상")
    @Test
    @WithUserDetails(value = "test123", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void item_insert_cart_pass() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("\n\nTEST============================================================\n\n");
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
}
