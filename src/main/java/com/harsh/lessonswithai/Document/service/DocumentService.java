package com.harsh.lessonswithai.Document.service;

import com.harsh.lessonswithai.Core.ApiService;
import com.harsh.lessonswithai.Document.domain.Document;
import com.harsh.lessonswithai.Document.mapper.DocumentMapper;
import com.harsh.lessonswithai.Document.model.DocumentDto;
import com.harsh.lessonswithai.Document.repository.DocumentRepository;
import com.harsh.lessonswithai.Utils.Auth.AuthService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService extends ApiService<Document, DocumentDto> {
    private final DocumentRepository repository;
    private final AuthService authService;
    public DocumentService(DocumentRepository repository, DocumentMapper mapper, AuthService authService) {
        super(repository, mapper);
        this.repository = repository;
        this.authService = authService;
    }

    @Override
    public List<DocumentDto> getAll() {
        return this.repository.findAllByUserIdOrderByCreatedAtDesc(authService.getUser_id()).stream().map(mapper::modelToDto).toList();
    }
}
