package com.harsh.lessonswithai.Core.commonlist.repository;

import com.harsh.lessonswithai.Core.commonlist.domain.Voice;
import com.harsh.lessonswithai.Core.commonlist.model.VoiceDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoiceRepository extends JpaRepository<Voice, VoiceDto> {
}
