package com.harsh.lessonswithai.VoiceAI.Knowledgebase;

import com.harsh.lessonswithai.knowledgebase.model.KnowledgebaseDto;

public interface VoiceAIKnowledgebase {

    public KnowledgebaseDto create(KnowledgebaseDto dto);

    public KnowledgebaseDto update(KnowledgebaseDto dto);

    public KnowledgebaseDto get(String id);

    public boolean delete(String id);
}
