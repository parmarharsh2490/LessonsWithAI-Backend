package com.harsh.lessonswithai.Vapi.assistant.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VapiAssistantDto {
    private String id;
    private String name;

    private Model model;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Model {
        private String model;
        private String provider;
        private ArrayList<String> toolIds;
    }

    private Voice voice;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Voice {
        private String voiceId;
        private String provider;
    }
}