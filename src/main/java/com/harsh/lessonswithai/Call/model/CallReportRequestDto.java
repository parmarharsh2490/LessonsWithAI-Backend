package com.harsh.lessonswithai.Call.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CallReportRequestDto {

    private Message message;

    private Assistant assistant;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Assistant {
        private String id;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {
        private String type;
        private String startedAt;
        private String endedAt;
        private String endedReason;
        private double cost;
        private int durationMs;

        private Call call;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Call {
            private String id;
        }
    }
}
