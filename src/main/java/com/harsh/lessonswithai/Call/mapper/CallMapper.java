package com.harsh.lessonswithai.Call.mapper;

import com.harsh.lessonswithai.Call.Domain.Call;
import com.harsh.lessonswithai.Call.model.CallDto;
import com.harsh.lessonswithai.Core.ApiMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CallMapper implements ApiMapper<Call, CallDto> {
}
