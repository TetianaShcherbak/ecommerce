package com.example.ecommerce.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Document
@NoArgsConstructor
public class AuthToken {
    @Id
    private String id;
    private String token;
    private LocalDateTime creationDate;
    @Indexed(unique = true)
    private String userId;

    public AuthToken(String userId) {
        this.token = UUID.randomUUID().toString();
        this.creationDate = LocalDateTime.now();
        this.userId = userId;
    }

}
