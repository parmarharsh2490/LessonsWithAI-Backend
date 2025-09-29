package com.harsh.lessonswithai.Call.controller;

import com.harsh.lessonswithai.Call.Domain.Call;
import com.harsh.lessonswithai.Call.model.CallDto;
import com.harsh.lessonswithai.Core.ApiController;
import com.harsh.lessonswithai.Core.ApiMapper;
import com.harsh.lessonswithai.Core.ApiService;

public class CallController extends ApiController<Call, CallDto> {
    protected CallController(ApiService<Call> service, ApiMapper<Call, CallDto> mapper) {
        super(service, mapper);
    }
}
