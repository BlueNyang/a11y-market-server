package com.multicampus.gamesungcoding.a11ymarketserver.cart.service;

import com.multicampus.gamesungcoding.a11ymarketserver.cart.model.CartDTO;

import java.util.List;

public interface CartService {
    List<CartDTO> getCartItems(Long memberId);

    int getCartTotal(Long memberId); // 총 금액: 화면 합계용
}