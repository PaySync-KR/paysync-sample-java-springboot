package kr.paysync.sample.backend.webhook.service;

import java.util.List;
import java.util.Map;

public interface WebhookService {

    void handleWebhook(Map<String, List<String>> headers, String rawBody);
}