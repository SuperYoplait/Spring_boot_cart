package com.springdemo.cartdemo.goods;

import javax.validation.Valid;

import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodsController {

    private final GoodsRepositroy goodsRepositroy;
    private final GoodsService goodsService;

    @GetMapping("/list")
    public String Goods_List(Model model, @PageableDefault(size = 10) Pageable pageable, @RequestParam(required = false, defaultValue = "") String goods) {
        Page<Goods> GoodsPagingList = goodsRepositroy.findByNameContaining(goods, pageable);
        
        System.out.println("\n\n");
        System.out.println(GoodsPagingList);
        System.out.println("\n\n");
        
        int startPage = Math.max(1, (GoodsPagingList.getPageable().getPageNumber() /
            pageable.getPageSize()) * pageable.getPageSize() + 1);
        int endPage = Math.min(GoodsPagingList.getTotalPages(), startPage +
            pageable.getPageSize() - 1);
        
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("list", GoodsPagingList);
        
        model.addAttribute("searchGoodsName", goods);
        return "goods/GoodsList";
    }

    @GetMapping("/detail")
    public String Goods_Detail(Model model, @RequestParam(required = false) Long id) {
        goodsService.detailProcess(model, id);
        return "";
    }

    @GetMapping("/goods-add")
    public String Goods_Add(Model model, @RequestParam(required = false) Long id) {
        goodsService.new_gooodsProcess(model, id);
        return "goods/GoodsAdd";
    }

    @PostMapping("/goods-add")
    public String Goods_Add_Post(@Valid GoodsForm goodsForm, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "goods/GoodsAdd";
        }
        Goods newGoods = goodsService.updateProcess(goodsForm);

        return "redirect:/goods/detail?id=" + newGoods.getId();
    }

    @GetMapping("/goods-delete")
    public String Goods_Delete() {
        return "";
    }
}
