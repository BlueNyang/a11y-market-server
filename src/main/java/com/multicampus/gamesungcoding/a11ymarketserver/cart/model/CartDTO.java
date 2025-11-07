package com.multicampus.gamesungcoding.a11ymarketserver.cart.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO {
    private Long cartItemId;
    private Long productId;
    private String productName;
    private Integer price;
    private Integer quantity;
    private Integer lineTotal; // price * quantity

    public static CartDTO from(Cart cart) {
        return CartDTO.builder()
                .cartItemId(cart.getId())
                .productId(cart.getProductId())
                .productName(cart.getProductName())
                .price(cart.getPrice())
                .quantity(cart.getQuantity())
                .lineTotal(cart.getPrice() * cart.getQuantity())
                .build();
    }
}