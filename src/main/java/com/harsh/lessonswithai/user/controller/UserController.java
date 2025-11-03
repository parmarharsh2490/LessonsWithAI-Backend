package com.harsh.lessonswithai.user.controller;

import com.harsh.lessonswithai.Core.ApiController;
import com.harsh.lessonswithai.Core.GenericDataDto;
import com.harsh.lessonswithai.user.domain.User;
import com.harsh.lessonswithai.user.mapper.UserMapper;
import com.harsh.lessonswithai.user.model.UserDto;
import com.harsh.lessonswithai.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController extends ApiController<User,UserDto> {
    @Value("${spring.application.keycloak_realm_name}")
    private String keycloak_realm_name;

    private final Keycloak keycloak;

    public UserController(UserService service, UserMapper mapper, Keycloak keycloak) {
        super(service,mapper);
        this.keycloak = keycloak;
    }

    public String getModuleName(){
        return "User";
    }

    @Override
    public ResponseEntity<GenericDataDto<UserDto>> get(String id) {
        return this.methodNotAllowed();
    }

    @Override
    public ResponseEntity<GenericDataDto<UserDto>> update(@RequestBody UserDto data) {
        var dto = new GenericDataDto<UserDto>();
        try {
            var users = keycloak.realm(keycloak_realm_name).users();

            UserResource userResource = users.get(data.getId());

            UserRepresentation userRepresentation = userResource.toRepresentation();

            userRepresentation.setUsername(data.getEmail());
            userRepresentation.setEmail(data.getEmail());
            userRepresentation.setFirstName(data.getFirstName());
            userRepresentation.setLastName(data.getLastName());
            userRepresentation.setEnabled(true);
            userResource.update(userRepresentation);

            dto.setResponseData(200,"Successfully Updated User");
        } catch (Exception e) {
            log.info("Keycloak Error : {}", e.getMessage());
            dto.setError(500,"Failed to Update");
        }
        return dto.sendResponse();
    }

}
