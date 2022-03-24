package com.springdemo.cartdemo.goods;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepositroy extends JpaRepository<Goods, Long>{
    Page<Goods> findByIdContainingOrNameContainingOrderByIdDesc(String searchString, String searchString2, Pageable pageable);
}
