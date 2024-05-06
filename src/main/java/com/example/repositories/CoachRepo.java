package com.example.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Coach;

@Repository
public interface CoachRepo extends JpaRepository<Coach, Integer> {

	Optional<Coach> findByNameAndPassword(String email, String password);

	
}
