package com.harsh.lessonswithai.Assistant.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.harsh.lessonswithai.Core.commonlist.model.ModelDto;
import com.harsh.lessonswithai.Core.commonlist.model.VoiceDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssistantDto {
    private Long id;
    private String assistantId;
    private String name;

    private VoiceDto voice;
    private String voiceName;

    private ModelDto model;
    private String modelName;

    private String createdAt;
    private String updatedAt;

    private String toolId;
    private String userId;
}
