package com.example.Blog04;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    // You can add specific query methods if needed
	
	
	
	
	 List<Post> findByEmail(String email);
	
}
