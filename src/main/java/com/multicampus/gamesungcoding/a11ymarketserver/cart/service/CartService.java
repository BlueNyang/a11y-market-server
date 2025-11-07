package com.multicampus.gamesungcoding.a11ymarketserver.cart.service;

import com.multicampus.gamesungcoding.a11ymarketserver.cart.model.CartDTO;
import com.multicampus.gamesungcoding.a11ymarketserver.cart.model.CartAddRequest;

import java.util.List;

public interface CartService {
    // 조회
    List<CartDTO> getCartItems(Long memberId);
    int getCartTotal(Long memberId);

    // 추가
    CartDTO addItem(CartAddRequest req);

    // 수량 수정
    CartDTO updateQuantity(Long cartItemId, int quantity);

    // 삭제
    int deleteItems(List<Long> itemIds);
}