package com.springdemo.cartdemo.goods;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("goods")
public class GoodsController {
    @GetMapping("/list")
    public String Goods_List(){
        return "goods/GoodsList";
    }
}
