package com.web.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.todo.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
