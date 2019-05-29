package com.jpa.repository;

import com.jpa.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import sun.misc.Contended;

@Contended
public interface UserRepository extends JpaRepository<user, Integer> {
    user getById(String id);
}
