package com.springdemo.cartdemo.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.mail.SimpleMailMessage;
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
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    
    private final AccountRepositroy accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRoleRepository accountRoleRepository;
    private final TempMailSender tempMailSender;  //가짜빈 

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
                    .password(passwordEncoder.encode(AccountSignUpForm.getPassword()))
                    .name(AccountSignUpForm.getName())
                    .email(AccountSignUpForm.getEmail())
                    .roles(roles)
                    
                    .build();
            mailSend(newAccount.getEmail(), newAccount.getName(), newAccount.getToken(), newAccount.getId());            
          
            newAccount.setTokeninit();
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

    public void mailSend(String email, String username, String token, Long useridval) { //보낼 이메일이름과 토큰 받고 유저 고유번호 받아야함.
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("장봐요 인증메일입니다.");
        simpleMailMessage.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
                .append(username)
                .append("님! 장봐요에 가입해주셔서 감사합니다!")
				.append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
				.append("<a href='http://localhost:8080/account/signupcheck?token=")
				.append(token)
				.append("&userid=")
				.append(useridval)
				.append("' target='_blenk'>이메일 인증 확인</a>")
				.toString());
    
        tempMailSender.send(simpleMailMessage);
    }

    public void sendSignUpConfirmEmail(Account account) {
        mailSend("ggb04212@naver.com", account.getName(), //보낸사람이메일
        account.getToken(), account.getId()); //메일 보내고
        account.setEmailTokenSendtime(); // 보낸시간 찍고
    }

    public void signupProcess(Model model, String token, Long userid) { //이메일 인증 프로세스
        if (userid != null) {
            Optional<Account> Accountop = accountRepository.findById(userid);
            if (Accountop.get().getToken().equals(token)) {
                Account newAccount = Accountop.get();
                
                newAccount = Account.builder()
                        .id(newAccount.getId())
                        .userid(newAccount.getUserid())
                        .password(newAccount.getPassword())
                        .name(newAccount.getName())
                        .email(newAccount.getEmail())
                        .token(newAccount.getToken())
                        .token_bool(true)                        
                        .build();
                accountRepository.save(newAccount);
                //권한추가해야함..     
                model.addAttribute("name", newAccount.getName());
                model.addAttribute("id", newAccount.getId());
                System.out.println("인증완료");
            }else model.addAttribute("error", "worng token!");

        }
    }
}
