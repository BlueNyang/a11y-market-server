package com.multicampus.gamesungcoding.a11ymarketserver.cart.controller;


import com.multicampus.gamesungcoding.a11ymarketserver.cart.model.CartDTO;
import com.multicampus.gamesungcoding.a11ymarketserver.cart.model.CartAddRequest;
import com.multicampus.gamesungcoding.a11ymarketserver.cart.model.CartQtyUpdateRequest;
import com.multicampus.gamesungcoding.a11ymarketserver.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
public class CartController {

    private final CartService cartService;

    // GET /api/v1/cart 목록 조회 기능
    @GetMapping("/v1/cart") //
    public ResponseEntity<Map<String, Object>> getCart(@RequestParam("memberId") Long memberId) {
        List<CartDTO> items = cartService.getCartItems(memberId);
        int total = cartService.getCartTotal(memberId);

        Map<String, Object> body = new HashMap<>();
        body.put("items", items);
        body.put("total", total);
        return ResponseEntity.ok(body);
    }

    // POST /api/v1/cart/items 상품 검색 기능
    @PostMapping("/v1/cart/items")
    public ResponseEntity<CartDTO> addItem(@Valid @RequestBody CartAddRequest req) {
        CartDTO created = cartService.addItem(req);
        return ResponseEntity.created(URI.create("/api/v1/cart/items/" + created.getCartItemId()))
                .body(created);
    }

    // PATCH /api/v1/cart/items/{cartItemId} 수량 조정 기능
    @PatchMapping("/v1/cart/items/{cartItemId}")
    public ResponseEntity<CartDTO> updateQuantity(@PathVariable Long cartItemId,
                                                  @Valid @RequestBody CartQtyUpdateRequest body) {
        CartDTO updated = cartService.updateQuantity(cartItemId, body.getQuantity());
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/v1/cart/items?itemIds=1,2,3 삭제 기능
    @DeleteMapping("/v1/cart/items")
    public ResponseEntity<Map<String, Object>> deleteItems(@RequestParam("itemIds") List<Long> itemIds) {
        int deleted = cartService.deleteItems(itemIds);
        Map<String, Object> res = Map.of("deleted", deleted, "itemIds", itemIds);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}