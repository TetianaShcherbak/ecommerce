package com.example.ecommerce.repository;

import com.example.ecommerce.model.AuthToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthTokenRepository extends MongoRepository<AuthToken, String> {
    Optional<AuthToken> findAuthTokenByToken(String token);
    Optional<AuthToken> findAuthTokenByUserId(String userId);
}
