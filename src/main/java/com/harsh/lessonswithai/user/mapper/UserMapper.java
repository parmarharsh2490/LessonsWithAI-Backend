package com.harsh.lessonswithai.user.mapper;

import com.harsh.lessonswithai.Core.ApiMapper;
import com.harsh.lessonswithai.user.domain.User;
import com.harsh.lessonswithai.user.model.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends ApiMapper<User,UserDto> {}