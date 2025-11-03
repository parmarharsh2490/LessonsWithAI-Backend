package com.harsh.lessonswithai.Assistant.domain;

import com.harsh.lessonswithai.Core.commonlist.domain.Model;
import com.harsh.lessonswithai.Core.commonlist.domain.Voice;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "assistant")
public class Assistant {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "assistant_id",nullable = false)
    private String assistantId;

    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "voice_id")
    private Voice voice;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private String updatedAt;
}
