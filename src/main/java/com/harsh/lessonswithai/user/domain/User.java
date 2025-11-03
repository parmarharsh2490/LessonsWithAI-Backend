package com.harsh.lessonswithai.user.domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id",nullable = false)
    private String id;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "email",nullable = false)
    private String email;

//    @OneToMany(mappedBy = "user")
//    ArrayList<AssistantDto> assistantLists = new ArrayList<>();
}

