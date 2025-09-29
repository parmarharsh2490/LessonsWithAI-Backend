package com.harsh.lessonswithai.Assistant.service;

import com.harsh.lessonswithai.Assistant.domain.Assistant;
import com.harsh.lessonswithai.Assistant.mapper.AssistantMapper;
import com.harsh.lessonswithai.Assistant.model.AssistantDto;
import com.harsh.lessonswithai.Assistant.repository.AssistantRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class AssistantService {
    private final AssistantRepository repository;
    private final AssistantMapper mapper;

    public AssistantService(AssistantRepository repository, AssistantMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AssistantDto get(String id){
        return this.repository.findById(id).map(mapper::modelToDto).orElseThrow(() -> new RuntimeException("Assistant Not Found"));
    }

    public Assistant save(Assistant assistant){
        return this.repository.save(assistant);
    }

    public ArrayList<AssistantDto> getAll(Long user_id, int page, int rowPerPage){
        Pageable pageable = PageRequest.of(page, rowPerPage, Sort.by("id").descending());
        return this.repository.findByUserId(user_id,pageable).stream().map(mapper::modelToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean delete(String id){
        if(repository.existsById(id)){
            this.repository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
