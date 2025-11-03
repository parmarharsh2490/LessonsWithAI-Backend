package com.harsh.lessonswithai.knowledgebase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harsh.lessonswithai.Core.ApiController;
import com.harsh.lessonswithai.Core.GenericDataDto;
import com.harsh.lessonswithai.VoiceAI.Knowledgebase.VoiceAIKnowledgebase;
import com.harsh.lessonswithai.knowledgebase.domain.Knowledgebase;
import com.harsh.lessonswithai.knowledgebase.mapper.KnowledgebaseMapper;
import com.harsh.lessonswithai.knowledgebase.model.KnowledgebaseDto;
import com.harsh.lessonswithai.knowledgebase.service.KnowledgebaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/knowledgebase")
public class KnowledgebaseController extends ApiController<Knowledgebase, KnowledgebaseDto> {
    private final VoiceAIKnowledgebase voiceAIKnowledgebase;
    private final KnowledgebaseService service;
    private final ObjectMapper objectMapper;

    protected KnowledgebaseController(KnowledgebaseService service, KnowledgebaseMapper mapper, VoiceAIKnowledgebase voiceAIKnowledgebase, ObjectMapper objectMapper) {
        super(service, mapper);
        this.service = service;
        this.voiceAIKnowledgebase = voiceAIKnowledgebase;
        this.objectMapper = objectMapper;
    }

    public String getModuleName(){
        return "Knowledgebase";
    }


    @Override
    public ResponseEntity<GenericDataDto<KnowledgebaseDto>> update(@RequestBody KnowledgebaseDto data) {
        var dto = new GenericDataDto<KnowledgebaseDto>();
        try{
            var response = voiceAIKnowledgebase.update(data);
            return super.update(response);
        } catch (Exception e) {
            log.info("Failed to update Knowledgebase : {}" , e.getMessage());
            dto.setError(400,"Failed to update Knowledgebase");
        }
        return dto.sendResponse();
    }

    @Override
    public ResponseEntity<GenericDataDto<KnowledgebaseDto>> save(KnowledgebaseDto data) {
        var dto = new GenericDataDto<KnowledgebaseDto>();
        try{
            var aiResponse = voiceAIKnowledgebase.create(data);
            return super.save(aiResponse);
        } catch (Exception e) {
            log.info("Failed to save Knowledgebase : {}" , e.getMessage());
            dto.setError(400,"Failed to save Knowledgebase");
        }
        return dto.sendResponse();
    }


    @DeleteMapping("/{id}/{knowledgebase_id}")
    public ResponseEntity<GenericDataDto<KnowledgebaseDto>> delete(@PathVariable String id,@PathVariable String knowledgebase_id) {
        var dto = new GenericDataDto<KnowledgebaseDto>();
        try{
            if(voiceAIKnowledgebase.delete(knowledgebase_id)){
                return super.delete(id);
            }else{
                throw new Exception("Failed to delete knowledebase");
            }
        } catch (Exception e) {
            log.info("Failed to delete knowledebase : {}", e.getMessage());
            dto.setError(400,"Failed to delete knowledebase");
        }
        return dto.sendResponse();
    }

    @Override
    public ResponseEntity<GenericDataDto<KnowledgebaseDto>> get(String id) {
        var dto = new GenericDataDto<KnowledgebaseDto>();
        try{
            var knowledgebase = service.getKnowledgebaseByAssistantId(id);
            var data = voiceAIKnowledgebase.get(knowledgebase.getToolId());
            data.setId(knowledgebase.getId());
            data.setAssistantId(knowledgebase.getAssistantId());
            dto.setResponseData(200, "Successfully Received Knowledgebase", data);
        } catch (Exception e) {
            log.info("Failed to get knowledgebase : {}", e.getMessage());
            dto.setError(404,"knowledgebase Not Found");
        }
        return dto.sendResponse();
    }

    @Override
    public ResponseEntity<GenericDataDto<KnowledgebaseDto>> delete(String id) {
        return super.methodNotAllowed();
    }
}
