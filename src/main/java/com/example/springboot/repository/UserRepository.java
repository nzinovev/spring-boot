package com.example.springboot.repository;

import com.example.springboot.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findUserByEmail(String email);
}
