package com.tunehub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunehub.entity.Users;

public interface UserRespository extends JpaRepository<Users,Integer> {
	
	public Users findByEmail(String email);
  
}
