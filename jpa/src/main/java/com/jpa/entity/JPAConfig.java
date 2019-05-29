package com.jpa.entity;

import com.jpa.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JPAConfig {

    /*@Bean
    public UserRepository UserRepository(){
        return new UserRepository() {
            @Override
            public user getById(String id) {
                return null;
            }
        };
    }*/
}
