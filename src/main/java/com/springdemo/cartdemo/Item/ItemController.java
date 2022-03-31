package com.springdemo.cartdemo.Item;

import org.springframework.data.web.PageableDefault;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepositroy itemRepositroy;
    private final ItemService itemService;

    @GetMapping("/list")
    public String item_List(Model model, @PageableDefault(size = 12) Pageable pageable,
            @RequestParam(required = false, defaultValue = "") String item,
            @RequestParam(required = false, defaultValue = "") String categorie) {
                

        Page<Item> ItemPagingList;
        if (!categorie.isEmpty()) {   
            System.out.println("\n\n\n카테고리조회입니다.\n\n\n");         
            ItemPagingList = itemRepositroy.findByCategorieContaining(categorie, pageable);

        } else {
            ItemPagingList = itemRepositroy.findByNameContaining(item, pageable);            
        }
        int startPage = Math.max(1,
                    (ItemPagingList.getPageable().getPageNumber() / pageable.getPageSize()) * pageable.getPageSize()
                            + 1);
            int endPage = Math.min(ItemPagingList.getTotalPages(), startPage + pageable.getPageSize() - 1);

            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("list", ItemPagingList);

            model.addAttribute("searchItemName", item);
            return "item/item-list";

    }

    //상품상세보기
    @GetMapping("/detail/{itemId}")
    public String item_detail(Model model, @PathVariable("itemId") Long id) {
        itemService.item_view_process(model, id);
        return "item/item-detail";
    }

    //상품 등록
    @GetMapping("/item-add") //권한에 막혀야됨 - 일반 구매자 접근 제한 - 판매자, 관리자만 접근 가능
    public String item_add(Model model, @RequestParam(required = false) Long id) {
        itemService.new_item_process(model, id);
        return "item/item-insert";
    }

    @PostMapping("/item-add")
    public String item_add_post(ItemForm itemForm, MultipartFile imgFile, Model model) throws Exception {
        Item newItem = itemService.update_process(itemForm, imgFile);
        return "redirect:/item/detail/" + newItem.getId();
    }

    //상품 장바구니에 추가
    @GetMapping("/item-insert")
    public String item_cart_insert(Model model, @RequestParam Long id){

        return "redirect:/my-cart";
    }

    @GetMapping("/item-delete")
    public String Goods_Delete() {
        return "";
    }


}