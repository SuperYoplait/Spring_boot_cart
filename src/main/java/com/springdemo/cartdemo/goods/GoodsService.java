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
                        .name(goods.get().getName())
                        .context(goods.get().getContext())
                        .price(goods.get().getPrice())
                        .image(goods.get().getImage())
                        .build();

                model.addAttribute("GoodsForm", goodsForm);
            }
        } else {
            model.addAttribute("GoodsForm", new GoodsForm());
        }
    }

}
