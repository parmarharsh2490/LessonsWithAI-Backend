package com.harsh.lessonswithai.Assistant.repository;

import com.harsh.lessonswithai.Assistant.domain.Assistant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssistantRepository extends JpaRepository<Assistant,String> {
    List<Assistant> findAllByUserIdOrderByCreatedAtDesc(String userId);

    Assistant findByAssistantId(String assistantId);
}
