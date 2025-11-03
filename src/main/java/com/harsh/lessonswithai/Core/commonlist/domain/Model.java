package com.harsh.lessonswithai.Core.commonlist.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "model")
@Getter
@Setter
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "provider",nullable = false)
    private String provider;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "value",nullable = false)
    private String value;
}
