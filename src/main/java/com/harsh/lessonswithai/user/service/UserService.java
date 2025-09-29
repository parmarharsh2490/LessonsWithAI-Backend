package com.harsh.lessonswithai.user.service;


import com.harsh.lessonswithai.Core.ApiService;
import com.harsh.lessonswithai.user.domain.User;
import com.harsh.lessonswithai.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ApiService<User> {
    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.repository = userRepository;
    }
}
