package com.multicampus.gamesungcoding.a11ymarketserver.cart.repository;

import com.multicampus.gamesungcoding.a11ymarketserver.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByMemberId(Long memberId);
}