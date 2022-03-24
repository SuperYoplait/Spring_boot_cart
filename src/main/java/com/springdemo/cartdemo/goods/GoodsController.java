package com.springdemo.cartdemo.goods;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @GetMapping("/list")
    public String Goods_List(){
        return "goods/GoodsList";
    }

    @GetMapping("/detail")
    public String Goods_Detail(){
        return "";
    }

    @GetMapping("/goods-add")
    public String Goods_Add(){
        return "";
    }

    @GetMapping("/goods-delete")
    public String Goods_Delete(){
        return "";
    }
}
