package com.multicampus.gamesungcoding.a11ymarketserver.cart.service;


import com.multicampus.gamesungcoding.a11ymarketserver.cart.model.Cart;
import com.multicampus.gamesungcoding.a11ymarketserver.cart.model.CartDTO;
import com.multicampus.gamesungcoding.a11ymarketserver.cart.model.CartAddRequest;
import com.multicampus.gamesungcoding.a11ymarketserver.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public List<CartDTO> getCartItems(Long memberId) {
        return cartRepository.findByMemberId(memberId).stream()
                .map(CartDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public int getCartTotal(Long memberId) {
        return cartRepository.findByMemberId(memberId).stream()
                .mapToInt(c -> c.getPrice() * c.getQuantity())
                .sum();
    }

    @Override
    @Transactional
    public CartDTO addItem(CartAddRequest req) {
        // 같은 회원이 같은 상품을 담으면 수량만 증가
        Cart cart = cartRepository.findByMemberIdAndProductId(req.getMemberId(), req.getProductId())
                .map(existing -> {
                    existing.setQuantity(existing.getQuantity() + req.getQuantity());
                    // 가격 변경 정책은 팀 룰에 맞춰 조정 가능.
                    return existing;
                })
                .orElseGet(() -> Cart.builder()
                        .memberId(req.getMemberId())
                        .productId(req.getProductId())
                        .productName(req.getProductName())
                        .price(req.getPrice())
                        .quantity(req.getQuantity())
                        .build()
                );

        Cart saved = cartRepository.save(cart);
        return CartDTO.from(saved);
    }

    @Override
    @Transactional
    public CartDTO updateQuantity(Long cartItemId, int quantity) {
        if (quantity < 1) throw new IllegalArgumentException("quantity must be >= 1");

        Cart cart = cartRepository.findById(cartItemId)
                .orElseThrow(() -> new NoSuchElementException("Cart item not found: " + cartItemId));

        cart.setQuantity(quantity);
        return CartDTO.from(cart); // dirty checking으로 flush됨
    }

    @Override
    @Transactional
    public int deleteItems(List<Long> itemIds) {
        if (itemIds == null || itemIds.isEmpty()) return 0;
        cartRepository.deleteAllByIdInBatch(itemIds);
        return itemIds.size();
    }
}