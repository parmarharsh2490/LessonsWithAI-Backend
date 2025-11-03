package com.harsh.lessonswithai.Core.commonlist.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ModelDto {
    private String id;

    private String provider;

    private String name;

    private String value;
}
