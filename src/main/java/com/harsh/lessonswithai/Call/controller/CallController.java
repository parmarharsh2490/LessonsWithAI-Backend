package com.harsh.lessonswithai.Call.controller;

import com.harsh.lessonswithai.Assistant.model.AssistantDto;
import com.harsh.lessonswithai.Assistant.service.AssistantService;
import com.harsh.lessonswithai.Call.Domain.Call;
import com.harsh.lessonswithai.Call.Service.CallService;
import com.harsh.lessonswithai.Call.mapper.CallMapper;
import com.harsh.lessonswithai.Call.model.CallDto;
import com.harsh.lessonswithai.Call.model.CallReportRequestDto;
import com.harsh.lessonswithai.Core.ApiController;
import com.harsh.lessonswithai.Core.GenericDataDto;
import com.harsh.lessonswithai.VoiceAI.Call.VoiceAICall;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/v1/call")
public class CallController extends ApiController<Call, CallDto> {
    private final CallService service;
    private final AssistantService assistantService;
    private final VoiceAICall voiceAICall;
    protected CallController(CallService service, CallMapper mapper, AssistantService assistantService, VoiceAICall voiceAICall) {
        super(service, mapper);
        this.service = service;
        this.assistantService = assistantService;
        this.voiceAICall = voiceAICall;
    }

    public String getModuleName(){
        return "Call";
    }

    @PostMapping("/report")
    public ResponseEntity<GenericDataDto<CallDto>> callReport(@RequestBody CallReportRequestDto data) {
        var dto = new GenericDataDto<CallDto>();
        try{
            if(Objects.equals(data.getMessage().getType(), "end-of-call-report")) {
                AssistantDto assistantDto = assistantService.getByAssistantId(data.getAssistant().getId());
                CallDto callDto = new CallDto();
                callDto.setCallId(data.getMessage().getCall().getId());
                callDto.setDuration(data.getMessage().getDurationMs());
                callDto.setCost(data.getMessage().getCost());
                callDto.setStartedAt(data.getMessage().getStartedAt());
                callDto.setEndedAt(data.getMessage().getEndedAt());
                callDto.setStatus("ENDED");
                callDto.setUserId(assistantDto.getUserId());
                return super.save(callDto);
            }
        } catch (Exception e) {
            log.error("Failed To Save Call Report : {}",e.getMessage());
            dto.setError(400,"Failed To Save Call Report : {}" + e.getMessage());
        }
        return dto.sendResponse();
    }

    @Override
    public ResponseEntity<GenericDataDto<CallDto>> get(String id) {
        var dto = new GenericDataDto<CallDto>();
        try{
            var callDto = service.get(id);
            var aiCallDto = voiceAICall.get(callDto.getCallId());
            aiCallDto.setCost(callDto.getCost());
            aiCallDto.setId(callDto.getId());
            dto.setResponseData(200,"Successfully Received Call Details",aiCallDto);
        } catch (Exception e) {
            log.info("Call Get Error : {}",e.getMessage());
            dto.setError(400,"Failed to get call details");
        }
        return dto.sendResponse();
    }
}
