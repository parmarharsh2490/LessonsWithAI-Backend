package com.harsh.lessonswithai.Document.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "document")
public class Document {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size",nullable = false)
    private String size;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "url",nullable = false)
    private String url;

    @Column(name = "document_id",nullable = false)
    private String documentId;

    @Column(name = "type",nullable = false)
    private String type;

    @Column(name = "user_id",nullable = false)
    private String userId;

    @Column(name = "created_at",nullable = false, insertable = false, updatable = false)
    private String createdAt;

    @Column(name = "updated_at",nullable = false, insertable = false, updatable = false)
    private String updatedAt;
}
