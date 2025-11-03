package com.harsh.lessonswithai.knowledgebase.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class KnowledgebaseDto {
    private Long id;
    private String assistantId;
    private String toolId;
    private String description;
    private ArrayList<String> fileIds;
}