package com.example.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

// 엔티티 객체가 생성 또는 수정 되었을 때 자동으로 값을 할당하여 주는 어노테이션이다.
@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware(){
        // TODO: 스프링 시큐리티로 인증 기능을 붙이게 될 때, 값이 할당되도록 수정
        return () -> Optional.of("mihee");
    }
}
