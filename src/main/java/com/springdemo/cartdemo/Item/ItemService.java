package com.springdemo.cartdemo.Item;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepositroy itemRepositroy;

    public void item_view_process(Model model, Long id) { // 구매자 상세보기
        Optional<Item> item = itemRepositroy.findById(id);
        Item view_item = item.get();
        model.addAttribute("item", view_item);
        model.addAttribute("itemId", id);
    }

    public void new_item_process(Model model, Long id) { // 판매자 상새보기
        if (id != null) {
            Optional<Item> item = itemRepositroy.findById(id);

            
            if (item.isPresent()) {
                ItemForm itemForm = ItemForm.builder()
                        .id(item.get().getId())
                        .name(item.get().getName())
                        .categorie(item.get().getCategorie())
                        .context(item.get().getContext())
                        .price(item.get().getPrice())
                        .count(item.get().getCount())
                        .imgName(item.get().getImgName())
                        .imgPath(item.get().getImgPath())
                        .build();
                model.addAttribute("itemForm", itemForm);
            }
        } else {
            model.addAttribute("itemForm", new ItemForm());
        }
    }

    public Item update_process(ItemForm itemForm, MultipartFile imgFile) throws Exception { // 제품 수정사항 변경
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
        Item newItem;
        if (itemForm.getId() == null) {
            newItem = Item.builder()
                    .id(null)
                    .name(itemForm.getName())
                    .context(itemForm.getContext())
                    .categorie(itemForm.getCategorie())
                    .price(itemForm.getPrice())
                    .count(itemForm.getCount())
                    .imgName(imgName)
                    .imgPath("/files/" + imgName)
                    .build();
        } else {
            newItem = Item.builder()
                    .id(itemForm.getId())
                    .name(itemForm.getName())
                    .context(itemForm.getContext())
                    .categorie(itemForm.getCategorie())
                    .price(itemForm.getPrice())
                    .count(itemForm.getCount())
                    .imgName(imgName)
                    .imgPath("/files/" + imgName)
                    .build();
        }
        System.out.println("\n\n" + newItem + "\n\n");
        newItem.setSold(itemForm.isSold()); //상품 판매 선택 
        itemRepositroy.save(newItem);

        return newItem;
    }

    public void item_cart_insert(){
        
    }

}
