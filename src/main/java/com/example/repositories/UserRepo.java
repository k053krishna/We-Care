package com.example.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByEmailAndPassword(String email, String password);

}
