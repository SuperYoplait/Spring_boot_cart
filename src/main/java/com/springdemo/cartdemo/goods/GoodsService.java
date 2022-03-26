package com.springdemo.cartdemo.goods;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepositroy goods_Repositroy;

    public void goods_View_Process(Model model, Long id) { // 구매자 상세보기
        Optional<Goods> goods = goods_Repositroy.findById(id);
        Goods view_goods = goods.get();
        model.addAttribute("item", view_goods);
        //return view_goods;
    }

    public void new_gooodsProcess(Model model, Long id) { // 판매자 상새보기
        if (id != null) {
            Optional<Goods> goods = goods_Repositroy.findById(id);

            if (goods.isPresent()) {
                GoodsForm goodsForm = GoodsForm.builder()
                        .id(goods.get().getId())
                        .name(goods.get().getName())
                        .categorie(goods.get().getCategorie())
                        .context(goods.get().getContext())
                        .price(goods.get().getPrice())
                        .count(goods.get().getCount())
                        .imgName(goods.get().getImgName())
                        .imgPath(goods.get().getImgPath())
                        .build();
                model.addAttribute("GoodsForm", goodsForm);
            }
        } else {
            model.addAttribute("GoodsForm", new GoodsForm());
        }
    }

    public Goods updateProcess(GoodsForm GoodsForm, MultipartFile imgFile) throws Exception { // 제품 수정사항 변경
        String oriImgName = imgFile.getOriginalFilename();
        String imgName = "";
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files/";

        // UUID 를 이용하여 파일명 새로 생성
        // UUID - 서로 다른 객체들을 구별하기 위한 클래스
        UUID uuid = UUID.randomUUID();
        String savedFileName = uuid + "_" + oriImgName; //저장될 파일명
        imgName = savedFileName;
        File saveFile = new File(projectPath, imgName);
        imgFile.transferTo(saveFile);
        Goods newGoods;
        if (GoodsForm.getId() == null) {
            newGoods = Goods.builder()
                    .id(null)
                    .name(GoodsForm.getName())
                    .context(GoodsForm.getContext())
                    .categorie(GoodsForm.getCategorie())
                    .price(GoodsForm.getPrice())
                    .count(GoodsForm.getCount())
                    .imgName(imgName)
                    .imgPath("/files/" + imgName)
                    .build();
        } else {
            newGoods = Goods.builder()
                    .id(GoodsForm.getId())
                    .name(GoodsForm.getName())
                    .context(GoodsForm.getContext())
                    .categorie(GoodsForm.getCategorie())
                    .price(GoodsForm.getPrice())
                    .count(GoodsForm.getCount())
                    .imgName(imgName)
                    .imgPath("/files/" + imgName)
                    .build();
        }
        System.out.println("\n\n" + newGoods + "\n\n");
        newGoods.setSold(GoodsForm.isSold()); //상품 판매 선택 
        goods_Repositroy.save(newGoods);

        return newGoods;
    }

}
