package com.springdemo.cartdemo.goods;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepositroy goods_Repositroy;

    public void detailProcess(Model model, Long id) { //구매자 상세보기
        if (id != null) {
            Optional<Goods> goods = goods_Repositroy.findById(id);

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

    public void new_gooodsProcess(Model model, Long id){ //판매자 상새보기
        if (id != null) {
            Optional<Goods> goods = goods_Repositroy.findById(id);

            if (goods.isPresent()) {
                GoodsForm goodsForm = GoodsForm.builder()
                        .id(goods.get().getId())
                        .name(goods.get().getName())
                        .context(goods.get().getContext())
                        .price(goods.get().getPrice())
                        .count(goods.get().getCount())
                        //.sold(goods.get().getSold())
                        .image(goods.get().getImage())
                        .build();
                model.addAttribute("GoodsForm", goodsForm);
            }
        } else {
            model.addAttribute("GoodsForm", new GoodsForm());
        }
    }
    
    public Goods updateProcess(GoodsForm GoodsForm) { //제품 수정사항 변경
        Goods newGoods;

        if (GoodsForm.getId() == null) {
            newGoods = Goods.builder()
                    .id(null)
                    .name(GoodsForm.getName())
                    .context(GoodsForm.getContext())
                    .price(GoodsForm.getPrice())
                    .count(GoodsForm.getCount())
                    //.sold(GoodsForm.getSold())
                    .image(GoodsForm.getImage())
                    .build();

            newGoods.setSold(GoodsForm.isSold());
        } else {
            newGoods = Goods.builder()
                    .id(GoodsForm.getId())
                    .name(GoodsForm.getName())
                    .context(GoodsForm.getContext())
                    .price(GoodsForm.getPrice())
                    .count(GoodsForm.getCount())
                    //.sold(GoodsForm.getSold())
                    .image(GoodsForm.getImage())
                    .build();

            newGoods.setSold(GoodsForm.isSold());
        }

        goods_Repositroy.save(newGoods);

        /*
        System.out.println("\n");
        System.out.println(newGoods);
        System.out.println("\n");
        */
        return newGoods;
    }

}
