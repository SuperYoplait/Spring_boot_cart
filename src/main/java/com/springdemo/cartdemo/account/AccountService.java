package com.springdemo.cartdemo.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    
    private final AccountRepositroy accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRoleRepository accountRoleRepository;

    //회원 가입
    public void signUp(AccountSignUpForm AccountSignUpForm) {
        List<AccountRole> roles = new ArrayList<>();

        Optional<AccountRole> accountRole = accountRoleRepository.findById(1l);

        accountRole.ifPresent(role -> {

            AccountRole ROLE_USER = AccountRole.builder()
                    .id(role.getId())
                    .rolename(role.getRolename())
                    .role(role.getRole())
                    .build();

            roles.add(ROLE_USER);

            Account newAccount = Account.builder()
                    .userid(AccountSignUpForm.getUserid())
                    .Password(passwordEncoder.encode(AccountSignUpForm.getPassword()))
                    .name(AccountSignUpForm.getName())
                    .email(AccountSignUpForm.getEmail())
                    .roles(roles)
                    .build();

            //ewAccount.generateEmailCheckToken();
            //mailMessage(newAccount);
            
            //System.out.println("\n\n"+ newAccount + "\n\n");
            accountRepository.save(newAccount);
        });

    }

    //security DB connect
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String idOrEmail) throws UsernameNotFoundException {
        Account account = new Account();
        //System.out.println("\n\n" + idOrEmail + "\n\n");

        // userid 또는 email 로 login
        account = accountRepository.findByUserid(idOrEmail);
        //System.out.println("\n\n" + account.getUserid() + "\n\n");

        if (account == null) {
            account = accountRepository.findByEmail(idOrEmail);
        }
        if (account == null) {
            throw new UsernameNotFoundException(idOrEmail);
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        account.getRoles().forEach(e -> {
            authorities.add(new SimpleGrantedAuthority(e.getRole()));
        });

        return new UserAccount(account, authorities);
    }

    //로그인
    public void login(Account account) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(new UserAccount(account),
                account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(token);
    }
}
