package kr.paysync.sample.backend.webhook.service;

import com.standardwebhooks.Webhook;
import kr.paysync.sample.backend.paysync.config.PaySyncProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebhookServiceImpl implements WebhookService {

    private final PaySyncProperties properties;

    @Override
    public void handleWebhook(Map<String, List<String>> headers, String rawBody) {
        try {
            Webhook webhook = new Webhook(properties.webhookSigningSecret());
            webhook.verify(rawBody, headers);
        } catch (Exception e) {
            throw new RuntimeException("웹훅 검증 실패: " + e.getMessage(), e);
        }
    }
}