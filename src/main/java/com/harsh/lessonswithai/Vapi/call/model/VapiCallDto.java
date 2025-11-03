package com.harsh.lessonswithai.Vapi.call.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class VapiCallDto {
    private String id;
    private String assistantId;
    private String type;
    private String startedAt;
    private String endedAt;
    private String transcript;
    private String recordingUrl;
    private String summary;
    private String createdAt;
    private String updatedAt;
    private String orgId;
    private double cost;
    private String webCallUrl;
    private String status;
    private String endedReason;
    private List<Message> messages;
    private String stereoRecordingUrl;
    private CostBreakdown costBreakdown;
    private AssistantOverrides assistantOverrides;
    private Map<String, Object> analysis;
    private Artifact artifact;
    private List<Cost> costs;
    private Monitor monitor;
    private Transport transport;

    @Getter
    @Setter
    public static class Message {
        private String role;
        private long time;
        private String message;
        private int secondsFromStart;
    }

    @Getter
    @Setter
    public static class CostBreakdown {
        private double transport;
        private double stt;
        private double llm;
        private double tts;
        private double vapi;
        private double chat;
        private double total;
        private int llmPromptTokens;
        private int llmCompletionTokens;
        private int ttsCharacters;
        private AnalysisCostBreakdown analysisCostBreakdown;
        private double knowledgeBaseCost;
        private double voicemailDetectionCost;
    }

    @Getter
    @Setter
    public static class AnalysisCostBreakdown {
        private double summary;
        private double structuredData;
        private double structuredOutput;
        private double successEvaluation;
        private int summaryPromptTokens;
        private int summaryCompletionTokens;
        private int structuredDataPromptTokens;
        private int structuredOutputPromptTokens;
        private int successEvaluationPromptTokens;
        private int structuredDataCompletionTokens;
        private int structuredOutputCompletionTokens;
        private int successEvaluationCompletionTokens;
    }

    @Getter
    @Setter
    public static class AssistantOverrides {
        private List<String> clientMessages;
        private Map<String, Object> variableValues;
    }

    @Getter
    @Setter
    public static class Artifact {
        private String recordingUrl;
        private String stereoRecordingUrl;
        private Recording recording;
        private List<Message> messages;
        private List<OpenAIMessage> messagesOpenAIFormatted;
        private String transcript;
        private String logUrl;
        private List<Object> nodes;
        private Map<String, String> variableValues;
        private Map<String, String> variables;
        private PerformanceMetrics performanceMetrics;
        private List<Object> transfers;
    }

    @Getter
    @Setter
    public static class Recording {
        private String stereoUrl;
        private Mono mono;
    }

    @Getter
    @Setter
    public static class Mono {
        private String combinedUrl;
        private String assistantUrl;
        private String customerUrl;
    }

    @Getter
    @Setter
    public static class OpenAIMessage {
        private String content;
        private String role;
    }

    @Getter
    @Setter
    public static class PerformanceMetrics {
        private List<Object> turnLatencies;
        private double modelLatencyAverage;
        private double voiceLatencyAverage;
        private double transcriberLatencyAverage;
        private double endpointingLatencyAverage;
        private double turnLatencyAverage;
    }

    @Getter
    @Setter
    public static class Cost {
        private double cost;
        private String type;
        private Double minutes;
        private Transcriber transcriber;
        private Model model;
        private Voice voice;
        private int promptTokens;
        private int completionTokens;
        private int characters;
        private String subType;
    }

    @Getter
    @Setter
    public static class Transcriber {
        private String model;
        private String provider;
    }

    @Getter
    @Setter
    public static class Model {
        private String model;
        private String provider;
    }

    @Getter
    @Setter
    public static class Voice {
        private String voiceId;
        private String provider;
    }

    @Getter
    @Setter
    public static class Monitor {
        private String listenUrl;
        private String controlUrl;
    }

    @Getter
    @Setter
    public static class Transport {
        private String callUrl;
        private String provider;
        private boolean assistantVideoEnabled;
        private boolean videoRecordingEnabled;
        private boolean roomDeleteOnUserLeaveEnabled;
    }
}
