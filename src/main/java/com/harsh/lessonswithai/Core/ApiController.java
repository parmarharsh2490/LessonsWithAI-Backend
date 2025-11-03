package com.harsh.lessonswithai.Core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
public abstract class ApiController<Model,Dto>{
    protected final ApiService<Model,Dto> service;
    protected final ApiMapper<Model,Dto> mapper;

    protected ApiController(ApiService<Model,Dto> service, ApiMapper<Model, Dto> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    public abstract String getModuleName();

    @GetMapping("/{id}")
    public ResponseEntity<GenericDataDto<Dto>> get(@PathVariable String id){
        var dataDto = new GenericDataDto<Dto>();
        try{
            var data = service.get(id);
            dataDto.setResponseData(200,"Successfully Get Data", data);
        } catch (RuntimeException e) {
            dataDto.setError(404,"Data Not Found");
        } catch (Exception e) {
            log.info("Get By Id Error {}", e.getMessage());
            dataDto.setError(500,"Error Occurred");
        }
        return dataDto.sendResponse();
    }

    @PostMapping("/save")
    public ResponseEntity<GenericDataDto<Dto>> save(@RequestBody Dto data){
        var dto = new GenericDataDto<Dto>();
        try{
            var modelData = mapper.dtoToModel(data);
            var modelResponseData  = this.service.save(modelData);
            dto.setResponseData(201,"Successfully Saved " + this.getModuleName(),mapper.modelToDto(modelResponseData));
        }catch (Exception e){
            log.info("Save Error : {}", e.getMessage());
            dto.setError(400,"Failed To Save " + this.getModuleName());
        }
        return dto.sendResponse();
    }

    @PutMapping("/update")
    public ResponseEntity<GenericDataDto<Dto>> update(@RequestBody Dto data){
        var dto = new GenericDataDto<Dto>();
        try{
            var newData  = this.service.update(mapper.dtoToModel(data));
            dto.setResponseData(200,"Successfully Updated " + this.getModuleName(),mapper.modelToDto(newData));
        }catch (Exception e){
            log.info("Update Error : {}", e.getMessage());
            dto.setError(500,"Failed To Update " + this.getModuleName());
        }
        return dto.sendResponse();
    }

    @GetMapping("")
    public ResponseEntity<GenericDataDto<Dto>> getAll(){
        var dto = new GenericDataDto<Dto>();
        try{
            var data = service.getAll();
            dto.setDataList(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dto.sendResponse();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericDataDto<Dto>> delete(@PathVariable String id){
        var dto = new GenericDataDto<Dto>();
        try{
            service.delete(id);
            dto.setResponseData(204,"Successfully Delete " + this.getModuleName());
        } catch (Exception e) {
            log.info("ApiController Delete Error : {}", e.getMessage());
            dto.setError(400,"Failed To Delete " + this.getModuleName());
        }
        return dto.sendResponse();
    }


    public ResponseEntity<GenericDataDto<Dto>> methodNotAllowed() {
        var dto = new GenericDataDto<Dto>();
        dto.setError(400,"Method not allowed");
        return dto.sendResponse();
    }
}
