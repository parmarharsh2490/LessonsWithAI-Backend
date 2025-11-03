package com.harsh.lessonswithai.Core.commonlist.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonListDto {
    private Long id;

    private String text;

    private String value;

    private String type;

    private String provider;
}
