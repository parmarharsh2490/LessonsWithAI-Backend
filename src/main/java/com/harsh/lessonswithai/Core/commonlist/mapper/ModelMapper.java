package com.harsh.lessonswithai.Core.commonlist.mapper;

import com.harsh.lessonswithai.Core.ApiMapper;
import com.harsh.lessonswithai.Core.commonlist.domain.Model;
import com.harsh.lessonswithai.Core.commonlist.model.ModelDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelMapper extends ApiMapper<Model, ModelDto> {
}
