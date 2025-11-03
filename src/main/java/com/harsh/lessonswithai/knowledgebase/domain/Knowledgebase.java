package com.harsh.lessonswithai.knowledgebase.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "knowledgebase")
@Getter
@Setter
public class Knowledgebase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tool_id",nullable = false)
    private String toolId;

    @Column(name = "assistant_id",nullable = false)
    private String assistantId;
}
