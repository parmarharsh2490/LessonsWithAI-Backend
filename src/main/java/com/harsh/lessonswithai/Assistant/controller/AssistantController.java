package com.harsh.lessonswithai.Assistant.controller;

import com.harsh.lessonswithai.Assistant.domain.Assistant;
import com.harsh.lessonswithai.Assistant.mapper.AssistantMapper;
import com.harsh.lessonswithai.Assistant.model.AssistantDto;
import com.harsh.lessonswithai.Assistant.service.AssistantService;
import com.harsh.lessonswithai.Core.ApiController;
import com.harsh.lessonswithai.Core.GenericDataDto;
import com.harsh.lessonswithai.VoiceAI.Assistant.VoiceAIAssistant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/assistant")
public class AssistantController extends ApiController<Assistant,AssistantDto>{
    private final VoiceAIAssistant voiceAIAssistant;

    public AssistantController(AssistantService service, AssistantMapper mapper, VoiceAIAssistant voiceAIAssistant) {
        super(service,mapper);
        this.voiceAIAssistant = voiceAIAssistant;
    }

    public String getModuleName(){
        return "Assistant";
    }

    @Override
    public ResponseEntity<GenericDataDto<AssistantDto>> save(@RequestBody AssistantDto data) {
        var dto = new GenericDataDto<AssistantDto>();
        try {
            var response = voiceAIAssistant.create(data);
            return super.save(response);
        } catch (Exception e) {
            log.info("Assistant Save Error : {}", e.getMessage());
            dto.setError(400,"Failed To Create Assistant");
        }
        return dto.sendResponse();
    }

    @Override
    public ResponseEntity<GenericDataDto<AssistantDto>> update(@RequestBody AssistantDto data) {
        var dto = new GenericDataDto<AssistantDto>();
        try {
            var response = voiceAIAssistant.update(data);
            return super.update(response);
        } catch (Exception e) {
            log.info("Assistant Update Error : {}", e.getMessage());
            dto.setError(400,"Failed To Update Assistant");
        }
        return dto.sendResponse();
    }


    @DeleteMapping("/{id}/{assistant_id}")
    public ResponseEntity<GenericDataDto<AssistantDto>> delete(@PathVariable String id,@PathVariable String assistant_id) {
        var dto = new GenericDataDto<AssistantDto>();
        try {
            voiceAIAssistant.delete(assistant_id);
            return super.delete(id);
        } catch (Exception e) {
            dto.setError(400,"Failed To Delete Assistant");
        }
        return dto.sendResponse();
    }

    @Override
    public ResponseEntity<GenericDataDto<AssistantDto>> delete(String id) {
        return super.methodNotAllowed();
    }
}
