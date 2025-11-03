package com.harsh.lessonswithai.Vapi.knowledgebase.mapper;

import com.harsh.lessonswithai.Vapi.knowledgebase.model.VapiKnowledgebaseDto;
import com.harsh.lessonswithai.knowledgebase.model.KnowledgebaseDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public class VapiKnowledgebaseMapper {

    public VapiKnowledgebaseDto knowledgebaseDtoToVapiKnowledgebaseDto(KnowledgebaseDto knowledgebaseDto){
        var dto = new VapiKnowledgebaseDto();
        dto.setType("query");
        dto.setFunction(new VapiKnowledgebaseDto.Function("Test"));
        var arrayList = new ArrayList<VapiKnowledgebaseDto.KnowledgeBase>();
        arrayList.add(new VapiKnowledgebaseDto.KnowledgeBase("google", "Test", knowledgebaseDto.getDescription(), knowledgebaseDto.getFileIds()));
        dto.setKnowledgeBases(arrayList);
        return dto;
    }

    public KnowledgebaseDto vapiKnowledgebaseDtoToKnowledgebaseDto(VapiKnowledgebaseDto vapiKnowledgebaseDto){
        var dto = new KnowledgebaseDto();
        dto.setFileIds(vapiKnowledgebaseDto.getKnowledgeBases().getFirst().getFileIds());
        dto.setDescription(vapiKnowledgebaseDto.getKnowledgeBases().getFirst().getDescription());
        dto.setToolId(vapiKnowledgebaseDto.getId());
        return dto;
    }
}
