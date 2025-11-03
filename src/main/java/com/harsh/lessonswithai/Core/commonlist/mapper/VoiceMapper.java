package com.harsh.lessonswithai.Core.commonlist.mapper;

import com.harsh.lessonswithai.Core.ApiMapper;
import com.harsh.lessonswithai.Core.commonlist.domain.Voice;
import com.harsh.lessonswithai.Core.commonlist.model.VoiceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoiceMapper extends ApiMapper<Voice, VoiceDto> {
}
