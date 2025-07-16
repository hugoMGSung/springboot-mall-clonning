package com.hugo83.webmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hugo83.webmall.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    
}
