package com.harsh.lessonswithai.Assistant.controller;

import com.harsh.lessonswithai.Assistant.domain.Assistant;
import com.harsh.lessonswithai.Assistant.mapper.AssistantMapper;
import com.harsh.lessonswithai.Assistant.model.AssistantDto;
import com.harsh.lessonswithai.Assistant.service.AssistantService;
import com.harsh.lessonswithai.Core.ApiController;
import com.harsh.lessonswithai.Core.GenericDataDto;
import com.harsh.lessonswithai.Utils.Auth.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/api/v1/assistant")
public class AssistantController {
    private final AssistantService service;
    private final AssistantMapper mapper;
    private final AuthService authService;

    public AssistantController(AssistantService service, AssistantMapper mapper, AuthService authService) {
        this.service = service;
        this.mapper = mapper;
        this.authService = authService;
    }

    @GetMapping("/all")
    public ResponseEntity<GenericDataDto<AssistantDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int rowPerPage
    ) {
        var dataDto = new GenericDataDto<AssistantDto>();
        try {
            ArrayList<AssistantDto> assistants = service.getAll(Long.valueOf(authService.getUser_id()), page, rowPerPage);
            dataDto.setResponseData(200,"Successfully Get Assistants",assistants);
        } catch (Exception e) {
            log.info("Assistant Get All Error : {}", e.getMessage());
            dataDto.setError(500,"Failed To Get Assistants");
        }
        return dataDto.sendResponse();
    }


    @GetMapping("/{id}")
    public ResponseEntity<GenericDataDto<AssistantDto>> get(@PathVariable String id){
        var dataDto = new GenericDataDto<AssistantDto>();
        try{
            var assistant = service.get(id);
            dataDto.setResponseData(200,"Successfully Get Assistant",assistant);
        } catch (RuntimeException e) {
            dataDto.setError(404,"Assistant Not Found");
        } catch (Exception e) {
            log.info("Assistant Get Error " + e.getMessage());
            dataDto.setError(500,"Error Occurred");
        }
        return dataDto.sendResponse();
    }

    @PostMapping("")
    @PutMapping("")
    public ResponseEntity<GenericDataDto<AssistantDto>> save(@RequestBody AssistantDto assistantDto){
        var dataDto = new GenericDataDto<AssistantDto>();
        try {
            var assistant = service.save(mapper.dtoToModel(assistantDto));
            dataDto.setResponseData(201,"Successfully Created Assistant",mapper.modelToDto(assistant));
        } catch (Exception e) {
            log.info("Failed to Create Assistant " + e.getMessage());
            dataDto.setError(500,"Failed to Create Assistant");
        }
        return dataDto.sendResponse();
    }

    public ResponseEntity<GenericDataDto<AssistantDto>> delete(String id) {
        var dataDto = new GenericDataDto<AssistantDto>();
        try{
            var is_assistant_deleted = service.delete(id);
            if(is_assistant_deleted){
                dataDto.setResponseData(200,"Successfully Delete Assistant");
            }else{
                dataDto.setError(404,"Assistant Not Found");
            }
        } catch (Exception e) {
            log.info("Assistant Delete Error {}", e.getMessage());
            dataDto.setError(500,"Error Occurred");
        }
        return dataDto.sendResponse();
    }
}
