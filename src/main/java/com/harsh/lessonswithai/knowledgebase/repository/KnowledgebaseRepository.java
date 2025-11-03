package com.harsh.lessonswithai.knowledgebase.repository;

import com.harsh.lessonswithai.knowledgebase.domain.Knowledgebase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgebaseRepository extends JpaRepository<Knowledgebase,String> {
    Knowledgebase getKnowledgebaseByAssistantId(String assistantId);
}
