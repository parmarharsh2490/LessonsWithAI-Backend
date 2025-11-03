package com.harsh.lessonswithai.Call.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "`call`")
public class Call {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "call_id",nullable = false)
    private String callId;

    @Column(name = "user_id",nullable = false)
    private String userId;

    @Column(name = "duration")
    private int duration;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "cost", nullable = false)
    private double cost;

    @Column(name = "started_at", nullable = false)
    private String startedAt;

    @Column(name = "ended_at", nullable = false)
    private String endedAt;

    @Column(name = "created_at",insertable = false,updatable = false)
    private String createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private String updatedAt;
}
