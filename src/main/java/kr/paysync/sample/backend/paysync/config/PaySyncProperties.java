package kr.paysync.sample.backend.paysync.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("paysync")
public record PaySyncProperties(
        String baseUrl,
        String apiKey,
        String webhookSigningSecret
) {
}