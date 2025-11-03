package com.harsh.lessonswithai.Core.commonlist.service;

import com.harsh.lessonswithai.Core.commonlist.mapper.CommonListMapper;
import com.harsh.lessonswithai.Core.commonlist.mapper.ModelMapper;
import com.harsh.lessonswithai.Core.commonlist.mapper.VoiceMapper;
import com.harsh.lessonswithai.Core.commonlist.model.CommonListDto;
import com.harsh.lessonswithai.Core.commonlist.model.ModelDto;
import com.harsh.lessonswithai.Core.commonlist.model.VoiceDto;
import com.harsh.lessonswithai.Core.commonlist.repository.CommonListRepository;
import com.harsh.lessonswithai.Core.commonlist.repository.ModelRepository;
import com.harsh.lessonswithai.Core.commonlist.repository.VoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonListService {
    private final CommonListRepository repository;
    private final CommonListMapper mapper;
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    private final VoiceRepository voiceRepository;
    private final VoiceMapper voiceMapper;

    public CommonListService(CommonListRepository repository, CommonListMapper mapper, ModelRepository modelRepository, ModelMapper modelMapper, VoiceRepository voiceRepository, VoiceMapper voiceMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
        this.voiceRepository = voiceRepository;
        this.voiceMapper = voiceMapper;
    }

    public List<CommonListDto> getAll(String type){
        return repository.findByType(type).stream().map(mapper::modelToDto).toList();
    }

    public List<ModelDto> getAllModel(){
        return modelRepository.findAll().stream().map(modelMapper::modelToDto).toList();
    }

    public List<VoiceDto> getAllVoice(){
        return voiceRepository.findAll().stream().map(voiceMapper::modelToDto).toList();
    }
}
