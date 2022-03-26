package com.springdemo.cartdemo.goods;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//import javax.transaction.Transactional;
@SpringBootTest
@AutoConfigureMockMvc
public class GoodsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    //private GoodsRepositroy goodsRepository;
    private WebApplicationContext webApplicationContext;

    @DisplayName("상품등록 - 정상")
    @Test
    void goods_insert_true() throws Exception {
        MockMultipartFile image = new MockMultipartFile("imgFile", "imgFile.jpeg", "image/jpeg", "<<png data>>".getBytes());

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(
            multipart("/upload").file(image)
            .param("name","test name")
            .param("context","test context")
            .param("price","1000")
            .param("count","10")
            .param("sole","true")
            )
            .andExpect(status().isOk());
    }
    
}
