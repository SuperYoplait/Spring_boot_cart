package com.springdemo.cartdemo.account;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ACCOUNT")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private Long number;

    @Column(unique = true)
    private String userid;
    
    @Column
    private String name;

    @Column
    private String Email;
    
    @Column
    private String Password;
    
    @Column
    private String token;
    
    @Column
    private LocalDateTime time;
    
    @Column(columnDefinition = "boolean default false")
    private Boolean token_bool;


    @PostPersist
    public void creationEmailTokenValue() { // 인증 값 생성
        this.token = UUID.randomUUID().toString();
    }

    public void setEmailTokentrue() {
        this.token_bool = true;
    }
    public void setEmailTokenfalse() {
        this.token_bool = false;
    }

    public void setEmailTokenSendtime() { 
        this.time = LocalDateTime.now();
    }

    public boolean canSendConfirmEmail(){
        //return this.emailTokenSendAt.isBefore(LocalDateTime.now().minusHours(1));
        return true;//테스트용 트루임 주석처리하세요
    }
}
