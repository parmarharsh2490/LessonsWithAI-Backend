package com.harsh.lessonswithai.Document.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DocumentDto {
    private String id;

    private String url;

    private String name;

    private String documentId;

    private String size;

    private String type;

    private String userId;

    private String createdAt;

    private String updatedAt;
}
