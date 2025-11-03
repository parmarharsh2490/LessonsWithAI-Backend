package com.harsh.lessonswithai.VoiceAI.Document;

import com.harsh.lessonswithai.Document.model.DocumentDto;
import org.springframework.web.multipart.MultipartFile;

public interface VoiceAIDocument {

    public DocumentDto upload(MultipartFile file);

    public boolean delete(String id);
}
