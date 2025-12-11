package com.multicampus.gamesungcoding.a11ymarketserver.common.advice.model;

public record RestErrorResponse(int status,
                                ErrorRespStatus error,
                                String message) {
}
