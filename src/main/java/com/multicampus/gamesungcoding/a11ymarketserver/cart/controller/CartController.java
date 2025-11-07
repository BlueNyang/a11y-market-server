package com.multicampus.gamesungcoding.a11ymarketserver.cart.controller;


import com.multicampus.gamesungcoding.a11ymarketserver.cart.model.CartDTO;
import com.multicampus.gamesungcoding.a11ymarketserver.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    /**
     * 장바구니 목록 조회
     * GET /api/v1/cart?memberId={회원ID}
     * 응답:
     * {
     * "items": [CartDTO...],
     * "total": 12345
     * }
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getCart(@RequestParam("memberId") Long memberId) {
        List<CartDTO> items = cartService.getCartItems(memberId);
        int total = cartService.getCartTotal(memberId);

        Map<String, Object> body = new HashMap<>();
        body.put("items", items);
        body.put("total", total);

        return ResponseEntity.ok(body); // 빈 목록도 200 + [] 로 반환
    }
}