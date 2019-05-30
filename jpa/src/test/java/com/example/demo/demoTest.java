package com.example.demo;

import com.jpa.Demo;
import com.jpa.entity.User;
import com.jpa.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Demo.class})
public class demoTest {
    @Autowired(required=true)
    UserRepository userRepository;

    @Test
    public void tsest(){
        User byId = userRepository.getById("1");
        System.out.println(byId);
    }
}
