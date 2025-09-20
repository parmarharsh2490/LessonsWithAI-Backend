package com.harsh.lessonswithai.Assistant.repository;

import com.harsh.lessonswithai.user.domain.User;
import jakarta.persistence.*;

@Table(name = "assistant")
public class AssistantDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "model",nullable = false)
    private String model;

    @ManyToOne()
    @JoinColumn(name = "id")
    private User user;
}
