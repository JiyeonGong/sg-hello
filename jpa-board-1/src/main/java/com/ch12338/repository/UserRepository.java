package com.ch12338.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ch12338.model.BoardUser;

public interface UserRepository  extends JpaRepository<BoardUser, Long>  {
	Optional<BoardUser> findByUsername(String username);
}
