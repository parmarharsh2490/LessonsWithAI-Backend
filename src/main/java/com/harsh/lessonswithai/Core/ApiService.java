package com.harsh.lessonswithai.Core;

import org.springframework.stereotype.Service;

public abstract class ApiService<Model> {
    private final ApiRepository<Model> repository;

    public ApiService(ApiRepository<Model> repository){
        this.repository = repository;
    }

    public Model get(String id){
        return this.repository.findById(id).orElseThrow(() -> new RuntimeException("Data Not Found"));
    }

    public Model save(Model model){
        return this.repository.save(model);
    }

    public Model update(Model model){
        return this.repository.save(model);
    }

    public Model delete(String id){
        Model data = this.repository.getReferenceById(id);
//        data.setIsDeleted(true);
        return this.repository.save(data);
    }
}