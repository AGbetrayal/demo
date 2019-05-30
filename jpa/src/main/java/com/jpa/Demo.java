package com.jpa;

import com.jpa.entity.User;
import com.jpa.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.jpa.**")
public class Demo {

    public static void main(String[] args) {
        SpringApplication.run(Demo.class, args);
    }

}
