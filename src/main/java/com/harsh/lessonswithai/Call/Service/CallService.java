package com.harsh.lessonswithai.Call.Service;

import com.harsh.lessonswithai.Call.Domain.Call;
import com.harsh.lessonswithai.Core.ApiRepository;
import com.harsh.lessonswithai.Core.ApiService;

public class CallService extends ApiService<Call> {
    public CallService(ApiRepository<Call> repository) {
        super(repository);
    }
}
