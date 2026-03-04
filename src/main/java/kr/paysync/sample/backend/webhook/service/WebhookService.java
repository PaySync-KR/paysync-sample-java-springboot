package kr.paysync.sample.backend.webhook.service;

import org.springframework.http.HttpHeaders;

public interface WebhookService {

    void handleWebhook(HttpHeaders headers, String rawBody);
}