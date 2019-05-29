package com.jpa;

import com.jpa.entity.user;
import com.jpa.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class demo {

    public static void main(String[] args) {
        SpringApplication.run(demo.class, args);
    }

    @Autowired
    UserRepository userRepository;

    @Test
    public void tsest(){
        user byId = userRepository.getById("1");
        System.out.println(byId);
    }
}
