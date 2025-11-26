package com.multicampus.gamesungcoding.a11ymarketserver.admin.seller.model;

import com.multicampus.gamesungcoding.a11ymarketserver.feature.seller.model.SellerGrades;

public record AdminSellerUpdateRequest(
        String sellerName,
        String businessNumber,
        SellerGrades sellerGrade,
        String sellerIntro,
        Boolean a11yGuarantee
) {
}
