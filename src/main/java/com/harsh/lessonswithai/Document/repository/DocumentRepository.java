package com.harsh.lessonswithai.Document.repository;

import com.harsh.lessonswithai.Document.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, String> {
    List<Document> findAllByUserIdOrderByCreatedAtDesc(String userId);

}
