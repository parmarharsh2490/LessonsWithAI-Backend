package com.harsh.lessonswithai.user.controller;

import com.harsh.lessonswithai.Utils.GenericDataDto;
import com.harsh.lessonswithai.user.domain.User;
import com.harsh.lessonswithai.user.mapper.UserMapper;
import com.harsh.lessonswithai.user.model.UserDto;
import com.harsh.lessonswithai.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;
    public UserController(UserService userService, UserMapper mapper) {
        this.service = userService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericDataDto<UserDto>> getUser(@PathVariable("id") String id){
        var dataDto = new GenericDataDto<UserDto>();
        try{
            var user = service.getUser(id);
            dataDto.setResponseData(200,"Successfully Get User",mapper.modelToDto(user));
        } catch (RuntimeException e) {
            dataDto.setError(404,"User Not Found");
        } catch (Exception e) {
            log.info("User Get Error " + e.getMessage());
            dataDto.setError(500,"Error Occurred");
        }
        return dataDto.sendResponse();
    }

    @PostMapping("")
    public ResponseEntity<GenericDataDto<UserDto>> register(@RequestBody UserDto userDto){
        var dataDto = new GenericDataDto<UserDto>();
        try {
            boolean isDuplicate = service.validateDuplication(userDto.getName(),userDto.getEmail());
            if(isDuplicate){
                dataDto.setError(400,"User Already Registered via name or email");
            }else{
                User user = service.register(mapper.dtoToModel(userDto));
                dataDto.setResponseData(201,"Successfully Registered User",mapper.modelToDto(user));
            }
        } catch (Exception e) {
            log.info("User Registration Error " + e.getMessage());
            dataDto.setError(500,"Error Occurred");
        }
        return dataDto.sendResponse();
    }
}
