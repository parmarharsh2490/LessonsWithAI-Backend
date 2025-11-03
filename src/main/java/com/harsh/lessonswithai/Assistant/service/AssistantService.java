package com.harsh.lessonswithai.Assistant.service;

import com.harsh.lessonswithai.Assistant.domain.Assistant;
import com.harsh.lessonswithai.Assistant.mapper.AssistantMapper;
import com.harsh.lessonswithai.Assistant.model.AssistantDto;
import com.harsh.lessonswithai.Assistant.repository.AssistantRepository;
import com.harsh.lessonswithai.Core.ApiService;
import com.harsh.lessonswithai.Utils.Auth.AuthService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistantService extends ApiService<Assistant, AssistantDto> {
    private final AssistantRepository repository;
    private final AuthService authService;
    public AssistantService(AssistantRepository repository, AssistantMapper mapper, AuthService authService) {
        super(repository,mapper);
        this.repository = repository;
        this.authService = authService;
    }

    @Override
    public List<AssistantDto> getAll() {
        return this.repository.findAllByUserIdOrderByCreatedAtDesc(authService.getUser_id()).stream().map(mapper::modelToDto).toList();
    }

    public AssistantDto getByAssistantId(String assistantId){
        Assistant assistant = this.repository.findByAssistantId(assistantId);
        return mapper.modelToDto(assistant);
    }
}
