package com.harsh.lessonswithai.Vapi.assistant.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harsh.lessonswithai.Assistant.model.AssistantDto;
import com.harsh.lessonswithai.Vapi.VapiConfig;
import com.harsh.lessonswithai.Vapi.assistant.mapper.VapiAssistantMapper;
import com.harsh.lessonswithai.Vapi.assistant.model.VapiAssistantDto;
import com.harsh.lessonswithai.VoiceAI.Assistant.VoiceAIAssistant;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class VapiAssistantService implements VoiceAIAssistant {
    private final VapiConfig config;
    private final VapiAssistantMapper mapper;

    public static final MediaType JSON = MediaType.get("application/json");

    private final ObjectMapper objectMapper;

    public VapiAssistantService(VapiConfig config, VapiAssistantMapper mapper, ObjectMapper objectMapper) {
        this.config = config;
        this.mapper = mapper;
        this.objectMapper = objectMapper;
    }

    public AssistantDto create(AssistantDto dto) {
        try{
            VapiAssistantDto vapiAssistantDto = mapper.assistantDtoToVapiAssisantDto(dto);
            log.info("Body  : {}", objectMapper.writeValueAsString(vapiAssistantDto));
            String url = config.getVapiURL() + "assistant";
            Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(objectMapper.writeValueAsString(vapiAssistantDto), JSON))
                    .build();
            String id = getJsonNode(request);
            dto.setAssistantId(id);
            return dto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public AssistantDto update(AssistantDto dto) {
        try{
            VapiAssistantDto vapiAssistantDto = mapper.assistantDtoToVapiAssisantDto(dto);
            log.info("Body2  : {}", objectMapper.writeValueAsString(vapiAssistantDto));

            String url = config.getVapiURL() + "assistant/" + dto.getAssistantId();
            Request request = new Request.Builder()
                    .url(url)
                    .patch(RequestBody.create(objectMapper.writeValueAsString(vapiAssistantDto), JSON))
                    .build();
            String id = getJsonNode(request);
            dto.setAssistantId(id);
            return dto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(String id) {
        try{
            String url = config.getVapiURL() + "assistant/" + id;
            Request request = new Request.Builder().url(url).delete().build();
            return getJsonNode(request).isEmpty();
        } catch (Exception e) {
            log.info("Failed to delete assistant in vapi.ai : {}", e.getMessage());
            return false;
        }
    }

    private String getJsonNode(Request request) throws IOException {
        Response result = config.getClient().newCall(request).execute();
        var responseBody = result.body().string();
        JsonNode node = objectMapper.readTree(responseBody);
        if(!result.isSuccessful()){
            throw new IOException("Failed In Assistant CRUD. Result is not successsfull" + result.message());
        }
        if (node.hasNonNull("id")) {
            return node.get("id").asText();
        }else{
            throw new IOException("Did not had Id Failed In Vapi Assistant CRUD.");
        }
    }

}