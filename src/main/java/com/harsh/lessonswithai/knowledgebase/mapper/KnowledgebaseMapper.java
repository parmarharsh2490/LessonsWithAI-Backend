package com.harsh.lessonswithai.knowledgebase.mapper;

import com.harsh.lessonswithai.Core.ApiMapper;
import com.harsh.lessonswithai.knowledgebase.domain.Knowledgebase;
import com.harsh.lessonswithai.knowledgebase.model.KnowledgebaseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface KnowledgebaseMapper extends ApiMapper<Knowledgebase, KnowledgebaseDto> {
}
