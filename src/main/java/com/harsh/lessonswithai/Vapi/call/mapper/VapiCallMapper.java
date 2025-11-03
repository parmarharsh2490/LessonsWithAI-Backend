package com.harsh.lessonswithai.Vapi.call.mapper;

import com.harsh.lessonswithai.Call.model.CallDto;
import com.harsh.lessonswithai.Vapi.call.model.VapiCallDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class VapiCallMapper {

    public CallDto vapiCallDtoToCallDto(VapiCallDto vapiCallDto){
        CallDto dto = new CallDto();
        if(vapiCallDto.getId() != null && !vapiCallDto.getId().isEmpty()){
            dto.setCallId(vapiCallDto.getId());
        }

        if(vapiCallDto.getType() != null && !vapiCallDto.getType().isEmpty()){
            dto.setType(vapiCallDto.getType());
        }

        if(vapiCallDto.getAssistantId() != null && !vapiCallDto.getAssistantId().isEmpty()){
            dto.setAssistantId(vapiCallDto.getAssistantId());
        }

        if(vapiCallDto.getStartedAt() != null && !vapiCallDto.getStartedAt().isEmpty()){
            dto.setStartedAt(vapiCallDto.getStartedAt());
        }

        if(vapiCallDto.getEndedAt() != null && !vapiCallDto.getEndedAt().isEmpty()){
            dto.setEndedAt(vapiCallDto.getEndedAt());
        }

        if(vapiCallDto.getSummary() != null && !vapiCallDto.getSummary().isEmpty()){
            dto.setSummary(vapiCallDto.getSummary());
        }

        if(vapiCallDto.getRecordingUrl() != null && !vapiCallDto.getRecordingUrl().isEmpty()){
            dto.setRecordingUrl(vapiCallDto.getRecordingUrl());
        }

        if (vapiCallDto.getMessages() != null) {
            dto.setMessages(
                    vapiCallDto.getMessages().stream()
                            .map(m -> new CallDto.Message( m.getRole(), m.getTime(),m.getMessage(), m.getSecondsFromStart()))
                            .toList()
            );
        }

        if(vapiCallDto.getTranscript() != null && !vapiCallDto.getTranscript().isEmpty()){
            dto.setTranscript(vapiCallDto.getTranscript());
        }

        return dto;
    }
}
