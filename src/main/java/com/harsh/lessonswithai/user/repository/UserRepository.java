package com.harsh.lessonswithai.user.repository;

import com.harsh.lessonswithai.Core.ApiRepository;
import com.harsh.lessonswithai.user.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ApiRepository<User> {}
