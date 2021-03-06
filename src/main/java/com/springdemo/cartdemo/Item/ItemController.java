package com.springdemo.cartdemo.Item;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import com.springdemo.cartdemo.account.Account;
import com.springdemo.cartdemo.account.AccountRole;
import com.springdemo.cartdemo.account.CurrentUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import java.util.List;

import static com.springdemo.cartdemo.Item.ItemController.ROOT;
import static com.springdemo.cartdemo.Item.ItemController.ITEM;

@Controller
@RequestMapping(ROOT + ITEM)
@RequiredArgsConstructor
public class ItemController {
    public static final String ROOT = "/";
    public static final String ITEM = "item";

    private final ItemRepositroy itemRepositroy;
    private final ItemService itemService;

    @GetMapping("/list")
    public String item_List(Model model, @PageableDefault(size = 12) Pageable pageable,
            @RequestParam(required = false, defaultValue = "") String item,
            @RequestParam(required = false, defaultValue = "") String categorie) {
                

        Page<Item> ItemPagingList;
        if (!categorie.isEmpty()) {   
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
    @GetMapping("/detail")
    public String item_detail(Model model, @RequestParam Long id) {
        itemService.item_view_process(model, id);
        return "item/item-detail";
    }

    //상품 등록
    @GetMapping("/item-add") //권한에 막혀야됨 - 일반 구매자 접근 제한 - 판매자, 관리자만 접근 가능
    public String item_add(Model model, @RequestParam(required = false) Long id, @CurrentUser Account account) {
        if(account != null){
            List<AccountRole> roles = account.getRoles();
            for(AccountRole role : roles){
                if(role.getRole().equals("ROLE_SELLER") || role.getRole().equals("ROLE_ADMIN")){
                    itemService.new_item_process(model, id);
                    return "item/item-insert";
                }
            }
            return "redirect:/item/list"; //로그인은 되어 있지만 권한은 안가지고 있음.
        }
        else
            return "redirect:/login"; //로그인이 안되어 있는 경우.
    }

    @PostMapping("/item-add")
    public String item_add_post(ItemForm itemForm, MultipartFile imgFile, Model model) throws Exception {
        if (itemForm != null) {
            if (itemForm.getName().length() < 5 || itemForm.getName().length() > 200 // 상품명 미달 or 상품 설명 미달 or 상품가격, 갯수가 없을 때
                    || itemForm.getContext().length() < 5 || itemForm.getPrice() == null // th:object를 사용할 수 없으니 상품 Form의 등록 조건확인.
                    || itemForm.getCount() == null) {
                return "redirect:/item/item-add";
            }
        }
        Item newItem = itemService.update_process(itemForm, imgFile);
        return "redirect:/item/detail/" + newItem.getId();
    }

    //상품 장바구니에 추가
    @PostMapping("/init-cart")
    @ResponseBody
    public ResponseEntity<String> item_cart_insert(@RequestBody ItemInsertForm itemInsertForm, @CurrentUser Account account){
        itemService.insertProcess(itemInsertForm, account);
        return ResponseEntity.ok().build();
    }
}