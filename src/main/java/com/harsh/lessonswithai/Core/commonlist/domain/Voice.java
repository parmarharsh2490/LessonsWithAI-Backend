package com.harsh.lessonswithai.Core.commonlist.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "voice")
@Getter
@Setter
public class Voice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "tone",nullable = false)
    private String tone;

    @Column(name = "provider",nullable = false)
    private String provider;

    @Column(name = "accent",nullable = false)
    private String accent;

    @Column(name = "gender",nullable = false)
    private String gender;

    @Column(name = "voice_id",nullable = false)
    private String voice_id;
}
