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

    //??????????????????
    @GetMapping("/detail")
    public String item_detail(Model model, @RequestParam Long id) {
        itemService.item_view_process(model, id);
        return "item/item-detail";
    }

    //?????? ??????
    @GetMapping("/item-add") //????????? ???????????? - ?????? ????????? ?????? ?????? - ?????????, ???????????? ?????? ??????
    public String item_add(Model model, @RequestParam(required = false) Long id, @CurrentUser Account account) {
        if(account != null){
            List<AccountRole> roles = account.getRoles();
            for(AccountRole role : roles){
                if(role.getRole().equals("ROLE_SELLER") || role.getRole().equals("ROLE_ADMIN")){
                    itemService.new_item_process(model, id);
                    return "item/item-insert";
                }
            }
            return "redirect:/item/list"; //???????????? ?????? ????????? ????????? ???????????? ??????.
        }
        else
            return "redirect:/login"; //???????????? ????????? ?????? ??????.
    }

    @PostMapping("/item-add")
    public String item_add_post(ItemForm itemForm, MultipartFile imgFile, Model model) throws Exception {
        if (itemForm != null) {
            if (itemForm.getName().length() < 5 || itemForm.getName().length() > 200 // ????????? ?????? or ?????? ?????? ?????? or ????????????, ????????? ?????? ???
                    || itemForm.getContext().length() < 5 || itemForm.getPrice() == null // th:object??? ????????? ??? ????????? ?????? Form??? ?????? ????????????.
                    || itemForm.getCount() == null) {
                return "redirect:/item/item-add";
            }
        }
        Item newItem = itemService.update_process(itemForm, imgFile);
        return "redirect:/item/detail/" + newItem.getId();
    }

    //?????? ??????????????? ??????
    @PostMapping("/init-cart")
    @ResponseBody
    public ResponseEntity<String> item_cart_insert(@RequestBody ItemInsertForm itemInsertForm, @CurrentUser Account account){
        itemService.insertProcess(itemInsertForm, account);
        return ResponseEntity.ok().build();
    }
}