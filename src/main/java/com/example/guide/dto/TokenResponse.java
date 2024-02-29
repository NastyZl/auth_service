package com.example.guide.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Setter
@Getter
public class TokenResponse {
    String token;

    public TokenResponse(String token) {
        this.token = token;
    }

    public TokenResponse() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenResponse that = (TokenResponse) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}
