package com.harsh.lessonswithai.Vapi.knowledgebase.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harsh.lessonswithai.Vapi.VapiConfig;
import com.harsh.lessonswithai.Vapi.knowledgebase.mapper.VapiKnowledgebaseMapper;
import com.harsh.lessonswithai.Vapi.knowledgebase.model.VapiKnowledgebaseDto;
import com.harsh.lessonswithai.VoiceAI.Knowledgebase.VoiceAIKnowledgebase;
import com.harsh.lessonswithai.knowledgebase.model.KnowledgebaseDto;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VapiKnowledgebaseService implements VoiceAIKnowledgebase {
    private final VapiKnowledgebaseMapper mapper;

    private final VapiConfig config;
    public static final MediaType JSON = MediaType.get("application/json");

    private final ObjectMapper objectMapper;

    public VapiKnowledgebaseService(VapiKnowledgebaseMapper mapper, VapiConfig config, ObjectMapper objectMapper) {
        this.mapper = mapper;
        this.config = config;
        this.objectMapper = objectMapper;
    }

    public KnowledgebaseDto create(KnowledgebaseDto knowledgebaseDto) {
     try {
         HttpUrl url = HttpUrl.parse(config.getVapiURL() + "tool");
         RequestBody body = RequestBody.create(objectMapper.writeValueAsString(mapper.knowledgebaseDtoToVapiKnowledgebaseDto(knowledgebaseDto)), JSON);
         Request request = new Request.Builder().post(body).url(url).build();
         JsonNode jsonNode = getJSONNode(request);
         knowledgebaseDto.setToolId(jsonNode.get("id").asText());
         return knowledgebaseDto;
     } catch (Exception e) {
         throw new RuntimeException(e);
     }
    }

    public KnowledgebaseDto update(KnowledgebaseDto knowledgebaseDto) {
        try {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            HttpUrl url = HttpUrl.parse(config.getVapiURL() + "tool/" + knowledgebaseDto.getToolId());
            VapiKnowledgebaseDto dto = mapper.knowledgebaseDtoToVapiKnowledgebaseDto(knowledgebaseDto);
            log.info("DTO : {}", objectMapper.writeValueAsString(dto));
            var data = mapper.knowledgebaseDtoToVapiKnowledgebaseDto(knowledgebaseDto);
            data.setType(null);
            RequestBody body = RequestBody.create(objectMapper.writeValueAsString(data), JSON);
            Request request = new Request.Builder().patch(body).url(url).build();
            getJSONNode(request);
            return knowledgebaseDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public KnowledgebaseDto get(String id) {
        try {
            HttpUrl url = HttpUrl.parse(config.getVapiURL() + "tool/" + id);
            Request request = new Request.Builder().get().url(url).build();
            JsonNode node = getJSONNode(request);
            VapiKnowledgebaseDto vapiKnowledgebaseDto = objectMapper.convertValue(node, VapiKnowledgebaseDto.class);
            return mapper.vapiKnowledgebaseDtoToKnowledgebaseDto(vapiKnowledgebaseDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode getJSONNode(Request request){
        try{
            Response  result = config.getClient().newCall(request).execute();
            String responseBody = result.body().string();

            if(!result.isSuccessful()){
                throw new Exception("Failed in crud Knowledgebase");
            }

            return objectMapper.readTree(responseBody);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(String id) {
        try{
            HttpUrl url = HttpUrl.parse(config.getVapiURL() + "tool/" + id);
            Request request = new Request.Builder().delete().url(url).build();
            return config.getClient().newCall(request).execute().isSuccessful();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
