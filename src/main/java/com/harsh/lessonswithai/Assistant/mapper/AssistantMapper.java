package com.harsh.lessonswithai.Assistant.mapper;

import com.harsh.lessonswithai.Assistant.domain.Assistant;
import com.harsh.lessonswithai.Assistant.model.AssistantDto;
import com.harsh.lessonswithai.Core.ApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssistantMapper extends ApiMapper<Assistant, AssistantDto> {

    @Override
    @Mapping(target = "voiceName",source = "voice.name")
    @Mapping(target = "modelName", source = "model.name")
    AssistantDto modelToDto(Assistant assistant);
}
