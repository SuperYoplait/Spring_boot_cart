package com.springdemo.cartdemo.goods;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class GoodsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private GoodsRepositroy goodsRepository;

    @DisplayName("상품등록 - 정상")
    @Test
    void board_insert_true() throws Exception {
        for(int i = 0; i< 300; i++){
            mockMvc.perform(post("/goods/goods-add")
                .param("name", "test name" + i)
                .param("context", "test context" + i)
                .param("price", "1100")
                .param("count", "100")
                .param("sold", "true")
                .param("img", "null")
                )
                .andExpect(status().is3xxRedirection());
        }
        
    }
}
