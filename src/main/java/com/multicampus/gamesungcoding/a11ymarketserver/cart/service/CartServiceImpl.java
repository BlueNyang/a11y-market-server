package com.multicampus.gamesungcoding.a11ymarketserver.cart.service;

import com.multicampus.gamesungcoding.a11ymarketserver.cart.model.Cart;
import com.multicampus.gamesungcoding.a11ymarketserver.cart.model.CartDTO;
import com.multicampus.gamesungcoding.a11ymarketserver.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public List<CartDTO> getCartItems(Long memberId) {
        List<Cart> items = cartRepository.findByMemberId(memberId);
        return items.stream()
                .map(CartDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public int getCartTotal(Long memberId) {
        return cartRepository.findByMemberId(memberId).stream()
                .mapToInt(c -> c.getPrice() * c.getQuantity())
                .sum();
    }
}