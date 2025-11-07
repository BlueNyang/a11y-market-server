package com.multicampus.gamesungcoding.a11ymarketserver.cart.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                 // 장바구니 아이템 PK

    @Column(nullable = false)
    private Long memberId;           // 사용자/회원 ID

    @Column(nullable = false)
    private Long productId;          // 상품 ID

    @Column(nullable = false, length = 100)
    private String productName;      // 상품명

    @Column(nullable = false)
    private Integer price;           // 단가(원)

    @Column(nullable = false)
    private Integer quantity;        // 수량

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // 담은 시각

    @PrePersist
    public void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}