package com.harsh.lessonswithai.Vapi;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Slf4j
@Service
public class VapiConfig {
    @Value("${spring.application.vapi_private_key}")
    private String vapiPrivateKey;

    private final String vapiURL = "https://api.vapi.ai/";

    public OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request newRequest = original.newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization", "Bearer " + vapiPrivateKey)
                            .build();

                    return chain.proceed(newRequest);
                })
                .build();
    }
}
