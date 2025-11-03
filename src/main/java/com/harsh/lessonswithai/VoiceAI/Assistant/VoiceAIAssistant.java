package com.harsh.lessonswithai.VoiceAI.Assistant;

import com.harsh.lessonswithai.Assistant.model.AssistantDto;

public interface VoiceAIAssistant {

    AssistantDto create(AssistantDto assistantDto);

    AssistantDto update(AssistantDto assistantDto);

    boolean delete(String id);

}
