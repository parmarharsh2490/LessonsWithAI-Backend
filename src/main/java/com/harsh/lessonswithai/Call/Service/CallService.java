package com.harsh.lessonswithai.Call.Service;

import com.harsh.lessonswithai.Call.Domain.Call;
import com.harsh.lessonswithai.Call.mapper.CallMapper;
import com.harsh.lessonswithai.Call.model.CallDto;
import com.harsh.lessonswithai.Call.repository.CallRepository;
import com.harsh.lessonswithai.Core.ApiService;
import org.springframework.stereotype.Service;

@Service
public class CallService extends ApiService<Call, CallDto> {
    public CallService(CallRepository repository, CallMapper mapper) {
        super(repository,mapper);
    }
}
