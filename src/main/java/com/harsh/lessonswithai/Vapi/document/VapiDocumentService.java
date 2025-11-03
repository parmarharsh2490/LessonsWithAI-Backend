package com.harsh.lessonswithai.Vapi.document;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harsh.lessonswithai.Document.model.DocumentDto;
import com.harsh.lessonswithai.Vapi.VapiConfig;
import com.harsh.lessonswithai.VoiceAI.Document.VoiceAIDocument;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class VapiDocumentService implements VoiceAIDocument {
    private final VapiConfig config;

    @Autowired
    private ObjectMapper objectMapper;

    public VapiDocumentService(VapiConfig config) {
        this.config = config;
    }

    public DocumentDto upload(MultipartFile file) {
        try{
            HttpUrl url = HttpUrl.parse(config.getVapiURL() + "file");
            MediaType mediaType = MediaType.parse(file.getContentType()); // generic
            RequestBody fileBody = RequestBody.create(file.getBytes(), mediaType);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM) // sets the multipart/form-data boundary
                    .addFormDataPart(
                            "file",                 // form field name
                            file.getOriginalFilename(),         // file name
                            fileBody
                    )
                    .build();
            Request request = new Request.Builder().url(url).post(requestBody).build();
            Response response =  this.config.getClient().newCall(request).execute();
            JsonNode node = objectMapper.readTree(response.body().string());
            if(!response.isSuccessful()){
                throw new IOException("Failed to upload Document.");
            }
            return DocumentDto.builder()
                    .documentId(node.get("id").asText())
                    .name(node.get("name").asText())
                    .url(node.get("url").asText())
                    .type(node.get("mimetype").asText())
                    .size(node.get("bytes").asText())
                    .build();
        } catch (Exception e) {
            log.info("Upload Image to Vapi Error : {}", e.getMessage());
            return null;
        }
    }

    public boolean delete(String id){
        try{
            HttpUrl url = HttpUrl.parse(config.getVapiURL() + "file/" + id);
            Request request = new Request.Builder().url(url).delete().build();
            Response response = config.getClient().newCall(request).execute();
            return response.isSuccessful();
        } catch (Exception e) {
            log.info("Failed To Delete File : {}", e.getMessage());
            return false;
        }
    }
}
