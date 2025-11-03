package com.harsh.lessonswithai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class LessonswithaiApplication {
    public static void main(String[] args) {
        SpringApplication.run(LessonswithaiApplication.class, args);
    }

    @GetMapping("/")
    public String homePage(){
        return "Hello World";
    }
}
