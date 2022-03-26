package com.springdemo.cartdemo.account;

import java.util.ArrayList;
import java.util.Optional;
import java.util.*;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor

public class AccountService implements UserDetailsService {
    
    private final AccountRepositroy accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRoleRepository accountRoleRepository;

    
    public void signUp(AccountSignUpForm AccountSignUpForm) {

        List<AccountRole> roles = new ArrayList<>();

        Optional<AccountRole> accountRole = accountRoleRepository.findById(1l);

        //accountRole.ifPresent(role -> {

            // AccountRole ROLE_USER = AccountRole.builder()
            //         .id(role.getId())
            //         .rolename(role.getRolename())
            //         .role(role.getRole())
            //         .build();

            // roles.add(ROLE_USER);

            Account newAccount = Account.builder()
                    .userid(AccountSignUpForm.getNickname())
                    .Password(passwordEncoder.encode(AccountSignUpForm.getPassword()))
                    .Email(AccountSignUpForm.getEmail())
                    //.roles(roles)
                    .build();

            //ewAccount.generateEmailCheckToken();
            //mailMessage(newAccount);

            accountRepository.save(newAccount);
        // });

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }


    
}
