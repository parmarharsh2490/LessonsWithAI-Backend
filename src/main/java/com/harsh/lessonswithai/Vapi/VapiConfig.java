package com.harsh.lessonswithai.Vapi;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Getter
@Setter
@Slf4j
@Service
public class VapiConfig {
    @Value("${spring.application.vapiApiKey}")
    private String vapiApiKey;

    private final String vapiURL = "https://api.vapi.ai/";

    public OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request original = chain.request();
                        Request newRequest = original.newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .addHeader("Authorization", "Bearer " + vapiApiKey)
                                .build();

                        return chain.proceed(newRequest);
                    }
                })
                .build();
    }
}
