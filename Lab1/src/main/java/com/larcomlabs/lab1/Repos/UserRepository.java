package com.larcomlabs.lab1.Repos;

import com.larcomlabs.lab1.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface UserRepository extends JpaRepository<User, String>
{
    User findByEmail(String email);
}
