package com.harsh.lessonswithai.user.service;


import com.harsh.lessonswithai.Core.ApiService;
import com.harsh.lessonswithai.user.domain.User;
import com.harsh.lessonswithai.user.mapper.UserMapper;
import com.harsh.lessonswithai.user.model.UserDto;
import com.harsh.lessonswithai.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ApiService<User, UserDto> {
    private final UserRepository repository;

    public UserService(UserRepository userRepository, UserMapper mapper) {
        super(userRepository,mapper);
        this.repository = userRepository;
    }
}
