package com.springdemo.cartdemo.goods;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
//import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import lombok.extern.slf4j.Slf4j;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.FileInputStream;
import java.net.http.HttpHeaders;
import java.nio.charset.StandardCharsets;

//import javax.transaction.Transactional;
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class GoodsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GoodsRepositroy goodsRepository;

    @DisplayName("상품등록 - 정상")
    @Test
    void goods_insert_true() throws Exception {
        MockMultipartFile file = new MockMultipartFile("imgFile", "test.png", "image/png",
                new FileInputStream("/img/images.png"));
/*
                mockMvc.perform(
                    .param("name", "test name")
                    .param("context", "test context")
                    .param("price", "10000")
                    .param("count", "10")
                    .param("sold", "true")
                    .file(file).part(new MockPart("id", "foo".getBytes(StandardCharsets.UTF_8))))
                .andExpect(status().is3xxRedirection());
                */
    }
    
}
