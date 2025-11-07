package com.multicampus.gamesungcoding.a11ymarketserver.cart.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartAddRequest {
    @NotNull
    private Long memberId;

    @NotNull
    private Long productId;

    // 실서비스에선 productName/price 는 Product 테이블에서 조회 권장.
    @NotNull
    private String productName;

    @NotNull @Min(1)
    private Integer price;

    @NotNull @Min(1)
    private Integer quantity;
}