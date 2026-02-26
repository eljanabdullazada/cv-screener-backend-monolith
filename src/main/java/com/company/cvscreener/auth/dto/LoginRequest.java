package com.company.cvscreener.auth.dto;

public record LoginRequest(
        String username,
        String password
) {}
