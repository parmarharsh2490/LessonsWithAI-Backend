package com.harsh.lessonswithai.Call.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table
@Getter
@Setter
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "duration")
    private Long duration;

}
