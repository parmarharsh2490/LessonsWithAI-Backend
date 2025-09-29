package com.harsh.lessonswithai.Assistant.repository;

import com.harsh.lessonswithai.Assistant.domain.Assistant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssistantRepository extends JpaRepository<Assistant,String> {
    List<Assistant> findByUserId(Long userId, Pageable pageable);
}
