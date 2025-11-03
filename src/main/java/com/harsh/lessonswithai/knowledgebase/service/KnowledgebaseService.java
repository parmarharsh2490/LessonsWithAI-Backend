package com.harsh.lessonswithai.knowledgebase.service;

import com.harsh.lessonswithai.Core.ApiService;
import com.harsh.lessonswithai.knowledgebase.domain.Knowledgebase;
import com.harsh.lessonswithai.knowledgebase.mapper.KnowledgebaseMapper;
import com.harsh.lessonswithai.knowledgebase.model.KnowledgebaseDto;
import com.harsh.lessonswithai.knowledgebase.repository.KnowledgebaseRepository;
import org.springframework.stereotype.Service;

@Service
public class KnowledgebaseService extends ApiService<Knowledgebase, KnowledgebaseDto> {
    private final KnowledgebaseRepository knowledgebaseRepository;

    public KnowledgebaseService(KnowledgebaseRepository repository, KnowledgebaseMapper mapper, KnowledgebaseRepository knowledgebaseRepository) {
        super(repository, mapper);
        this.knowledgebaseRepository = knowledgebaseRepository;
    }

    public KnowledgebaseDto getKnowledgebaseByAssistantId(String id){
        Knowledgebase knowledgebase = knowledgebaseRepository.getKnowledgebaseByAssistantId(id);
        return mapper.modelToDto(knowledgebase);
    }
}
