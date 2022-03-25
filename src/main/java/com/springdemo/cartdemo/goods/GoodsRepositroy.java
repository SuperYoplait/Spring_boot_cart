package com.springdemo.cartdemo.goods;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepositroy extends JpaRepository<Goods, Long>{
    Page<Goods> findByNameContaining(String name , Pageable pageable);
    //Page<Goods> findByIdContainingOrNameContainingOrderByIdDesc(String name, String name2, Pageable pageable);
}
