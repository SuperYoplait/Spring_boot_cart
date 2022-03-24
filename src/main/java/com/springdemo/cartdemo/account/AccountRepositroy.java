package com.springdemo.cartdemo.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepositroy extends JpaRepository<Account, Long>{
    Account findByUserid(String id);
}
