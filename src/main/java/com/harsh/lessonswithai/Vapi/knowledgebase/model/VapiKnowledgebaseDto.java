package com.harsh.lessonswithai.Vapi.knowledgebase.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class VapiKnowledgebaseDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Function {
        private String name;
    }

    private Function function;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class KnowledgeBase {
        private String provider;
        private String name;
        private String description;
        private ArrayList<String> fileIds;
    }

    private ArrayList<KnowledgeBase> knowledgeBases;
}