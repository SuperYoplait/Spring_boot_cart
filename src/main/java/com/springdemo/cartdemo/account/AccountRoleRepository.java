package com.springdemo.cartdemo.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AccountRoleRepository extends JpaRepository<AccountRole,Long> {
    
    // SELECT role.rolename FROM account user
    // inner join authority auth on user.id = auth.account_id
    // inner join account_role role on role.id = auth.role_id
    // where user.nickname = 'jang';
    
    //체크한 권한
    List<AccountRole> findByIdIn(List<Long> roleIds);

}
