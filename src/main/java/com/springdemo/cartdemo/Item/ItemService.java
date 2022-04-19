package com.springdemo.cartdemo.Item;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

import com.springdemo.cartdemo.account.Account;
import com.springdemo.cartdemo.cart.Cart;
import com.springdemo.cartdemo.cart.CartRepositroy;
import com.springdemo.cartdemo.cartitem.CartItem;
import com.springdemo.cartdemo.cartitem.CartItemRepository;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepositroy itemRepositroy;
    private final CartItemRepository cartitemRepositroy;
    private final CartRepositroy cartRepositroy;

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
        String savedFileName = uuid + "_" + oriImgName; // 저장될 파일명
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
        newItem.setSold(itemForm.isSold()); // 상품 판매 선택
        itemRepositroy.save(newItem);

        return newItem;
    }

    public void insertProcess(ItemInsertForm itemInsertForm, Account account) {
        System.out.println("\n\n" + account.getId());
        System.out.println("\n\n" + account.getCart().getId());
        
        Optional<Cart> addCart = cartRepositroy.findById(account.getCart().getId());
        System.out.println("\n\n\n\n CartItemID" + account.getCart().getId());

        Optional<Item> serchItem = itemRepositroy.findById(itemInsertForm.getItemId());
        System.out.println("\n\n\n\n serchItem" + serchItem);

        Optional<CartItem> cartItem = cartitemRepositroy.findByItemId(itemInsertForm.getItemId());

        Long temp_sum_price = 0L;

        CartItem newCartItem;
        CartItem bool_item;
        
        if (addCart.isPresent() && serchItem.isPresent()) {
            // cart_id, item_id 가 이미 존재 할 경우 cnt만 그만큼 증가 해야됨.
            if (!cartItem.isEmpty()) {
                bool_item = cartItem.get();
                newCartItem = CartItem.builder()
                        .id(bool_item.getId())
                        .cnt(itemInsertForm.getCnt() + bool_item.getCnt())
                        .item(serchItem.get())
                        .cart(account.getCart())
                        .build();
            } else { // item_id가 없으면 기존에 item을 넣어놓은것이 없으니 새로 추가.
                newCartItem = CartItem.builder()
                        .cnt(itemInsertForm.getCnt())
                        .item(serchItem.get())
                        .cart(account.getCart())
                        .build();
            }
            cartitemRepositroy.save(newCartItem);
            //수정 해야함.
            if (addCart.get().getSum_price() == 0) { // 상품 추가할 때 cart에 합계 미리 계산 해 놓음
                temp_sum_price += (newCartItem.getCnt() * newCartItem.getItem().getPrice());
            } else {
                temp_sum_price = addCart.get().getSum_price();
                temp_sum_price = (newCartItem.getCnt() * newCartItem.getItem().getPrice());
            }
            addCart.get().setSum_price(temp_sum_price);
            cartRepositroy.save(addCart.get());
        }
    }
}