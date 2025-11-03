package com.harsh.lessonswithai.user.repository;

import com.harsh.lessonswithai.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {}
