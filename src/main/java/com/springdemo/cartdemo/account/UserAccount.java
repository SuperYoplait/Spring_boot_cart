package com.springdemo.cartdemo.account;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
<<<<<<< HEAD
public class UserAccount extends User {
    private Account account;

    public UserAccount(Account account) {
        super(account.getUserid(), account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.account = account;
    }

    public UserAccount(Account account, List<SimpleGrantedAuthority> authorities) {
=======
public class UserAccount extends User{
    private Account account;

    public UserAccount(Account account){
        super(account.getUserid(), account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.account=account;
    }

    public UserAccount(Account account, List<SimpleGrantedAuthority> authorities){
>>>>>>> master
        super(account.getUserid(), account.getPassword(), authorities);
        this.account = account;
    }
}
