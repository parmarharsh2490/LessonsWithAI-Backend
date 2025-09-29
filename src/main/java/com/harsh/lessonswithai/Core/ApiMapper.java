package com.harsh.lessonswithai.Core;

public interface ApiMapper<Model,Dto>  {
    Dto modelToDto(Model model);

    Model dtoToModel(Dto dto);
}
