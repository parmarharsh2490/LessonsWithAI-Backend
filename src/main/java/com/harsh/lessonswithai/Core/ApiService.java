package com.harsh.lessonswithai.Core;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class ApiService<Model,Mapper> {
    private final JpaRepository<Model,String> repository;
    protected final ApiMapper<Model,Mapper>  mapper;

    public ApiService(JpaRepository<Model,String> repository, ApiMapper<Model, Mapper> mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public Mapper get(String id){
        return mapper.modelToDto(this.repository.findById(id).orElseThrow(() -> new RuntimeException("Data Not Found")));
    }

    public Model save(Model model){
        return this.repository.save(model);
    }

    public Model update(Model model){
        return this.repository.save(model);
    }

    public void delete(String id){
        this.repository.deleteById(id);
    }

    public List<Mapper> getAll(){
        return this.repository.findAll().stream().map(mapper::modelToDto).toList();
    }
}