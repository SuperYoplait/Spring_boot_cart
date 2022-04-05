package com.springdemo.cartdemo.Item;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.io.File;
import java.io.FileInputStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    //private ItemRepositroy itemRepositroy;
    private WebApplicationContext webApplicationContext;

    @DisplayName("상품등록 - 정상")
    @Test
    void item_insert_true() throws Exception {
        String[] array = {"fruit" , "greens" , "milk" , "instant" , "beverage" , "seasoning" , "snacks" , "infant"};
        String fileName = "imgFile";
        //File file = new File("D:/gitproject/Spring_boot_cart/src/main/resources/static/img/images.png");
        //File file = new File("D:/spring_test/cartdemo/src/main/resources/static/img/images.png");
        File file = new File("/Users/macbookair/Documents/GitHub/Spring_boot_cart/src/main/resources/static/img/images.png");
        
        MockMultipartFile image = new MockMultipartFile(fileName, new FileInputStream(file));
        
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        for(int i = 0; i<20; i++){
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

        for(int i = 20; i<40; i++){
            mockMvc.perform(
                multipart("/item/item-add").file(image)
                .param("name","채소 test" + (i+1))
                .param("context","채소 상품 설명 : " + (i+1))
                .param("price","10000")
                .param("count","10")
                .param("categorie", array[1])
                .param("sold","true")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());
        }

        for(int i = 40; i<60; i++){
            mockMvc.perform(
                multipart("/item/item-add").file(image)
                .param("name","우유 test" + (i+1))
                .param("context","우유 상품 설명 : " + (i+1))
                .param("price","10000")
                .param("count","10")
                .param("categorie", array[2])
                .param("sold","true")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());
        }

        for(int i = 60; i<80; i++){
            mockMvc.perform(
                multipart("/item/item-add").file(image)
                .param("name","간편식 test" + (i+1))
                .param("context","간편식 상품 설명 : " + (i+1))
                .param("price","10000")
                .param("count","10")
                .param("categorie", array[3])
                .param("sold","true")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());
        }

        for(int i = 80; i<100; i++){
            mockMvc.perform(
                multipart("/item/item-add").file(image)
                .param("name","음료 test" + (i+1))
                .param("context","음료 상품 설명 : " + (i+1))
                .param("price","10000")
                .param("count","10")
                .param("categorie", array[4])
                .param("sold","true")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());
        }

        for(int i = 100; i<120; i++){
            mockMvc.perform(
                multipart("/item/item-add").file(image)
                .param("name","조미료 test" + (i+1))
                .param("context","조미료 상품 설명 : " + (i+1))
                .param("price","10000")
                .param("count","10")
                .param("categorie", array[5])
                .param("sold","true")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());
        }

        for(int i = 120; i<140; i++){
            mockMvc.perform(
                multipart("/item/item-add").file(image)
                .param("name","과자 test" + (i+1))
                .param("context","과자 상품 설명 : " + (i+1))
                .param("price","10000")
                .param("count","10")
                .param("categorie", array[6])
                .param("sold","true")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());
        }

        for(int i = 140; i<160; i++){
            mockMvc.perform(
                multipart("/item/item-add").file(image)
                .param("name","완구 test" + (i+1))
                .param("context","완구 상품 설명 : " + (i+1))
                .param("price","10000")
                .param("count","10")
                .param("categorie", array[7])
                .param("sold","true")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());
        }
        
    }

    /* @DisplayName("상품장바구니 저장 - 정상")
    @Test
    void item_insert_cart() throws Exception {
        mockMvc.perform(
            
        );
    }
     */
}
