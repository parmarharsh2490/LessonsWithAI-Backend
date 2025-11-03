package com.harsh.lessonswithai.Core.commonlist.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "commonlist")
@Getter
@Setter
public class CommonList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text",nullable = false)
    private String text;

    @Column(name = "value",nullable = false)
    private String value;

    @Column(name = "type",nullable = false)
    private String type;

    @Column(name = "provider",nullable = false)
    private String provider;
}
