package com.harsh.lessonswithai.Call.repository;

import com.harsh.lessonswithai.Call.Domain.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallRepository extends JpaRepository<Call,String> {
}
