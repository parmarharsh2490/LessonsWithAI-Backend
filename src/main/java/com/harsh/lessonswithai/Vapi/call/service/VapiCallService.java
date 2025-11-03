package com.harsh.lessonswithai.Vapi.call.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harsh.lessonswithai.Call.model.CallDto;
import com.harsh.lessonswithai.Vapi.VapiConfig;
import com.harsh.lessonswithai.Vapi.call.mapper.VapiCallMapper;
import com.harsh.lessonswithai.Vapi.call.model.VapiCallDto;
import com.harsh.lessonswithai.VoiceAI.Call.VoiceAICall;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VapiCallService implements VoiceAICall {
    private final VapiConfig config;
    private final VapiCallMapper mapper;

    @Autowired
    ObjectMapper objectMapper;

    public VapiCallService(VapiConfig config, VapiCallMapper mapper) {
        this.config = config;
        this.mapper = mapper;
    }

    @Override
    public void create() {
    }

    @Override
    public CallDto get(String id) {
        try{
            HttpUrl url = HttpUrl.parse(config.getVapiURL() + "call/" + id);
            Request request = new Request.Builder().get().url(url).build();
            Response response = this.config.getClient().newCall(request).execute();
            String responseBody = response.body().string();
            if(!response.isSuccessful()){
                throw new RuntimeException("Failed to get call details");
            }
            VapiCallDto vapiCallDto = objectMapper.readValue(responseBody, VapiCallDto.class);
            return mapper.vapiCallDtoToCallDto(vapiCallDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
