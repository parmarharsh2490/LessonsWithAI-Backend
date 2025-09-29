package com.harsh.lessonswithai.Utils.Auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class AuthService {
    private String user_id;
    private String email;
}
