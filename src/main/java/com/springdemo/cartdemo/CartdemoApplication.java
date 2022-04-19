package com.springdemo.cartdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CartdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartdemoApplication.class, args);
	}

// fragement 정리 - 스크립트는 body 직전, html 아래는 페이지에서 쓰는 특정 스크립트,
// 코드 줄이기
// select 과도하게 쓴거 서비스로 뺐는지
// application properties dev로 정리
// node 적용 cdn 걷어내기
// 자바메일센더 mymailcontext로 수정 properties에 따라 콘솔일지, 실제 메일을 쏠지
//  { dev - 개발서버(실제 메일 나가야함)
//    local - 콘솔  }
// 나눠서 테스트코드 작성해야지

}
