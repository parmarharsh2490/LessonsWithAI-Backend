package com.harsh.lessonswithai.Call.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CallDto {

    private Long id;

    private String userId;

    private String type;

    private List<Message> messages;

    private String assistantId;

    private String summary;

    private String recordingUrl;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Message {
        private String role;
        private long time;
        private String message;
        private int secondsFromStart;
    }

    private int duration;

    private String callId;

    private String transcript;

    private String status;

    private double cost;

    private String startedAt;

    private String endedAt;

    private String createdAt;

    private String updatedAt;
}
