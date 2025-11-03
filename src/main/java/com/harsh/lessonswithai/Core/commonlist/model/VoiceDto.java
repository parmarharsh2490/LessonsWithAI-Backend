package com.harsh.lessonswithai.Core.commonlist.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VoiceDto {
    private String id;
    private String name;
    private String tone;
    private String accent;
    private String gender;
    private String voice_id;
    private String provider;
}
