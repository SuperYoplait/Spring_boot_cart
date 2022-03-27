package com.springdemo.cartdemo.account;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PostPersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "ACCOUNT")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String userid;
    
    @Column
    private String name;

    @Column
    private String email;
    
    @Column
    private String password;
    
    @Column
    private String token;
    
    @Column
    private LocalDateTime time;
    
    @Column //(columnDefinition = "boolean default false")
    private Boolean token_bool;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name="authority",
        joinColumns =  @JoinColumn(name="accountId"),
        inverseJoinColumns = @JoinColumn(name="roleId")
    )
    List<AccountRole> roles = new ArrayList<>();


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
