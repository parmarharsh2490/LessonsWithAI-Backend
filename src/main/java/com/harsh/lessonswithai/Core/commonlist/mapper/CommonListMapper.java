package com.harsh.lessonswithai.Core.commonlist.mapper;

import com.harsh.lessonswithai.Core.ApiMapper;
import com.harsh.lessonswithai.Core.commonlist.domain.CommonList;
import com.harsh.lessonswithai.Core.commonlist.model.CommonListDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommonListMapper extends ApiMapper<CommonList,CommonListDto> {
}
