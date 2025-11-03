package com.harsh.lessonswithai.VoiceAI.Call;

import com.harsh.lessonswithai.Call.model.CallDto;

public interface VoiceAICall {
    void create();

    CallDto get(String id);
}
