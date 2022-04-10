package com.springdemo.cartdemo.Item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepositroy extends JpaRepository<Item, Long>{
    Page<Item> findByNameContaining(String name, Pageable pageable);
    Page<Item> findByCategorieContaining(String categorie, Pageable pageable);
}
