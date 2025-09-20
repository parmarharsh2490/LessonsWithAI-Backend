package com.harsh.lessonswithai.user.service;


import com.harsh.lessonswithai.user.domain.User;
import com.harsh.lessonswithai.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public User getUser(String id){
        return this.repository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    public User register(User user){
        return this.repository.save(user);
    }

    public boolean validateDuplication(String name,String email){
        return this.repository.duplicateVerifyAtSave(name,email) != 0;
    }
}
