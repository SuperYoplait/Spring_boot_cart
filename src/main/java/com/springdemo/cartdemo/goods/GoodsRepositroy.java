package com.springdemo.cartdemo.goods;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepositroy extends JpaRepository<Goods, Long>{
    
}
