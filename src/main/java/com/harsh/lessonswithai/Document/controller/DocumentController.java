package com.harsh.lessonswithai.Document.controller;

import com.harsh.lessonswithai.Core.ApiController;
import com.harsh.lessonswithai.Core.GenericDataDto;
import com.harsh.lessonswithai.Document.domain.Document;
import com.harsh.lessonswithai.Document.mapper.DocumentMapper;
import com.harsh.lessonswithai.Document.model.DocumentDto;
import com.harsh.lessonswithai.Document.service.DocumentService;
import com.harsh.lessonswithai.Utils.Auth.AuthService;
import com.harsh.lessonswithai.VoiceAI.Document.VoiceAIDocument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/v1/document")
public class DocumentController extends ApiController<Document, DocumentDto> {
    private final VoiceAIDocument voiceAIDocument;
    private final AuthService authService;
    protected DocumentController(DocumentService service, DocumentMapper mapper, VoiceAIDocument voiceAIDocument, AuthService authService) {
        super(service, mapper);
        this.voiceAIDocument = voiceAIDocument;
        this.authService = authService;
    }

    public String getModuleName(){
        return "Document";
    }

    @PostMapping(value = "/save",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GenericDataDto<DocumentDto>> save(@RequestParam("file") MultipartFile file) {
        var dto = new GenericDataDto<DocumentDto>();
        try{
            DocumentDto documentDto = voiceAIDocument.upload(file);
            documentDto.setUserId(authService.getUser_id());
            return super.save(documentDto);
        } catch (Exception e) {
            log.info("Failed To Upload File : {}", e.getMessage());
            dto.setError(400,"Failed To Upload File");
        }
        return dto.sendResponse();
    }

    @DeleteMapping("/{id}/{document_id}")
    public ResponseEntity<GenericDataDto<DocumentDto>> delete(@PathVariable String id,@PathVariable String document_id) {
        var dto = new GenericDataDto<DocumentDto>();
        try{
            if(voiceAIDocument.delete(document_id)){
                return super.delete(id);
            }else{
                throw new Exception();
            }
        } catch (Exception e) {
            log.info("Delete File Error : {}", e.getMessage());
            dto.setError(404,"File Not Found");
        }
        return dto.sendResponse();
    }

    @Override
    public ResponseEntity<GenericDataDto<DocumentDto>> delete(String id) {
        return super.methodNotAllowed();
    }
}
