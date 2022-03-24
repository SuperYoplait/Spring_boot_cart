package com.springdemo.cartdemo.goods;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepositroy goodsrepoRepositroy;


    public void detailProcess(Model model, Long id) {
        if (id != null) {
            Optional<Goods> goods = goodsrepoRepositroy.findById(id);

            if (goods.isPresent()) {
                GoodsForm goodsForm = GoodsForm.builder()
                        .id(goods.get().getId())
                        .name(goods.get().getname())
                        .context(goods.get().getcontext())
                        .price(goods.get().getprice())
                        .image(goods.get().getimage())
                        .build();

                model.addAttribute("GoodsForm", goodsForm);
            }
        } else {
            model.addAttribute("GoodsForm", new GoodsForm());
        }
    }

}
