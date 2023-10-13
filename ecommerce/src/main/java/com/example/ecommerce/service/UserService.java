package com.example.ecommerce.service;

import com.example.ecommerce.enums.UserRole;
import com.example.ecommerce.exceptions.UserNotFoundException;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;


    public User getUser(String userId) throws UserNotFoundException {
        return repository.findById(userId).get();
    }
    public UserRole getUserRole(String userId) throws UserNotFoundException {
        return repository.findById(userId).get().getRole();
    }
}
