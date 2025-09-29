package com.harsh.lessonswithai.Core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
public abstract class ApiController<Model,Dto>{
    private final ApiService<Model> service;
    private final ApiMapper<Model,Dto> mapper;

    protected ApiController(ApiService<Model> service, ApiMapper<Model, Dto> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericDataDto<Dto>> get(@PathVariable String id){
        var dataDto = new GenericDataDto<Dto>();
        try{
            Model data = service.get(id);
            dataDto.setResponseData(200,"Successfully Get Data", mapper.modelToDto(data));
        } catch (RuntimeException e) {
            dataDto.setError(404,"Data Not Found");
        } catch (Exception e) {
            log.info("Get By Id Error {}", e.getMessage());
            dataDto.setError(500,"Error Occurred");
        }
        return dataDto.sendResponse();
    }

    @PostMapping("/save")
    public ResponseEntity<GenericDataDto<Dto>> save(Dto data){
        var dto = new GenericDataDto<Dto>();
        try{
            var newData  = this.service.save(mapper.dtoToModel(data));
            dto.setResponseData(200,"Successfully Saved",mapper.modelToDto(newData));
        }catch (Exception e){
            log.info("Save Error : {}", e.getMessage());
            dto.setError(500,"Failed To Save");
        }
        return dto.sendResponse();
    }
}
