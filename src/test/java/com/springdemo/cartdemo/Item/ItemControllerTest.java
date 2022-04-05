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
        File file = new File("/Users/macbookair/Documents/GitHub/Spring_boot_cart/src/main/resources/static/img/images.png");
        
        MockMultipartFile image = new MockMultipartFile(fileName, new FileInputStream(file));
        
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        for(int i = 120; i<140; i++){
            mockMvc.perform(
                multipart("/item/item-add").file(image)
                .param("name","완구 test" + (i+1))
                .param("context","완구 상품 설명 : " + (i+1))
                .param("price","10000")
                .param("count","10")
                .param("categorie", array[7])
                .param("sole","true")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());
        }
        
    }
    
}
