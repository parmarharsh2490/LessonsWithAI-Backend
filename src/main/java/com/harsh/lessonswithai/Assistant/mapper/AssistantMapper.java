package com.harsh.lessonswithai.Assistant.mapper;

import com.harsh.lessonswithai.Assistant.domain.Assistant;
import com.harsh.lessonswithai.Assistant.model.AssistantDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssistantMapper {

    AssistantDto modelToDto(Assistant assistant);

    Assistant dtoToModel(AssistantDto assistantDto);
}
