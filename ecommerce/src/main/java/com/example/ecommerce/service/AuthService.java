package com.example.ecommerce.service;

import com.example.ecommerce.model.AuthToken;
import com.example.ecommerce.repository.AuthTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private static final TemporalAmount tokenDurationInSeconds = Duration.ofSeconds(60*15); // 15 min

    private final AuthTokenRepository repository;

    public String getUserId(String token) throws IllegalAccessException {
        Optional<AuthToken> authToken = repository.findAuthTokenByToken(token);
        if (isTokenValid(authToken)){
            return authToken.get().getUserId();
        } else {
            throw new IllegalAccessException();
        }
    }

    private boolean isTokenValid(Optional<AuthToken> token){
        return isTokenRegistered(token) && !isTokenExpired(token.get());

    }

    private boolean isTokenRegistered(Optional<AuthToken> token){
        return token.isPresent();
    }
    private boolean isTokenExpired(AuthToken token){
        return token.getCreationDate().plus(tokenDurationInSeconds).isBefore(LocalDateTime.now());
    }

}
