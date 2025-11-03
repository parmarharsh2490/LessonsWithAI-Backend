package com.harsh.lessonswithai.Core.commonlist.controller;

import com.harsh.lessonswithai.Core.commonlist.service.CommonListService;
import com.harsh.lessonswithai.Core.GenericDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/commonlist")
public class CommonListController {
    private final CommonListService service;

    public CommonListController(CommonListService service) {
        this.service = service;
    }

    @GetMapping("/{type}")
    public ResponseEntity<GenericDataDto> getAll(@PathVariable String type){
        var dto = new GenericDataDto();
        try {
            if(type.equals("model")){
                var dataList = service.getAllModel();
                dto.setDataList(dataList);
            }else if(type.equals("voice")){
                var dataList = service.getAllVoice();
                dto.setDataList(dataList);
            }
            dto.setResponseData(200,"Successfully Received");
        } catch (Exception e) {
            log.info("Get Error In CommonList : {}", e.getMessage());
            dto.setError(400,"Failed To Get in CommonList");
        }
        return dto.sendResponse();
    }
}
