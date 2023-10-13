package com.example.ecommerce.model;


import com.example.ecommerce.enums.Gender;
import com.example.ecommerce.enums.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;



@Data
@Document
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private Gender gender;
    private Address address;
    private UserRole role;
    private LocalDateTime registrationDate;

    public User(String firstName,
                String lastName,
                String email,
                Gender gender,
                Address address,
                UserRole role,
                LocalDateTime registrationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.role = role;
        this.registrationDate = registrationDate;
    }
}
