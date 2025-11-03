package com.harsh.lessonswithai.Vapi.assistant.mapper;

import com.harsh.lessonswithai.Assistant.model.AssistantDto;
import com.harsh.lessonswithai.Vapi.assistant.model.VapiAssistantDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public class VapiAssistantMapper {

    public VapiAssistantDto assistantDtoToVapiAssisantDto(AssistantDto dto) {
        var vapiAssistantDto = new VapiAssistantDto();
        vapiAssistantDto.setName(dto.getName());
        var toolIds = new ArrayList<String>();
        if(dto.getToolId() != null && !dto.getToolId().isEmpty()){
            toolIds.add(dto.getToolId());
        }
        vapiAssistantDto.setModel(new VapiAssistantDto.Model(dto.getModel().getValue(),dto.getModel().getProvider(),toolIds));
        vapiAssistantDto.setVoice(new VapiAssistantDto.Voice(dto.getVoice().getVoice_id(),dto.getVoice().getProvider()));
        return vapiAssistantDto;
    }
}
