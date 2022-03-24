package com.springdemo.cartdemo.goods;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodsController {

    private final GoodsService goodsService;

    @GetMapping("/list")
    public String Goods_List(){
        
        return "goods/GoodsList";
    }

    @GetMapping("/detail")
    public String Goods_Detail(Model model, @RequestParam(required = false) Long id ){
        goodsService.detailProcess(model, id);
        return "";
        //
    }

    @GetMapping("/goods-add")
    public String Goods_Add(){
        return "goods/GoodsAdd";
    }

    @GetMapping("/goods-delete")
    public String Goods_Delete(){
        return "";
    }
}
