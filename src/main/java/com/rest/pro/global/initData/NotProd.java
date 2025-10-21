package com.rest.pro.global.initData;

import com.rest.pro.domain.article.service.ArticleService;
import com.rest.pro.domain.member.entity.Member;
import com.rest.pro.domain.member.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile({"dev", "test"})
public class NotProd {
    @Bean
    CommandLineRunner initData(ArticleService articleService, MemberService memberService, PasswordEncoder passwordEncoder) {

        String password = passwordEncoder.encode("1234");
        return args -> {

            //회원 추가
            Member admin = memberService.join("admin", password, "admin@test.com");
            Member user1 = memberService.join("admin", password, "admin1@test.com");
            Member user2 = memberService.join("admin", password, "admin2@test.com");

            articleService.create(admin,"제목1", "내용1");
            articleService.create(user1,"제목2", "내용2");
            articleService.create(user1,"제목3", "내용3");
            articleService.create(user2,"제목4", "내용4");
            articleService.create(user2,"제목5", "내용5");


        };
    }
}
