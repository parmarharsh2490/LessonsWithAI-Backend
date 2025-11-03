package com.harsh.lessonswithai.Document.mapper;

import com.harsh.lessonswithai.Core.ApiMapper;
import com.harsh.lessonswithai.Document.domain.Document;
import com.harsh.lessonswithai.Document.model.DocumentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper extends ApiMapper<Document, DocumentDto> {

}
