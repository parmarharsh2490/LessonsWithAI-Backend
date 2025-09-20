package com.harsh.lessonswithai.user.repository;

import com.harsh.lessonswithai.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    @Query(value = "select count(*) from user where email = :email or name = :name", nativeQuery = true)
    Integer duplicateVerifyAtSave(@Param("name") String name, @Param("email") String email);
}
