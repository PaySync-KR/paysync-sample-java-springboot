package kr.paysync.sample.backend.webhook.service;

import com.standardwebhooks.Webhook;
import kr.paysync.sample.backend.paysync.config.PaySyncProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebhookServiceImpl implements WebhookService {

    private final PaySyncProperties properties;

    @Override
    public void handleWebhook(HttpHeaders headers, String rawBody) {
        try {
            Webhook webhook = new Webhook(properties.webhookSigningSecret());
            webhook.verify(
                    rawBody,
                    java.net.http.HttpHeaders.of(
                            new HashMap<>() {{
                                headers.forEach((k, v) -> put(k, new ArrayList<>(v)));
                            }},
                            (k, v) -> true
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException("웹훅 검증 실패: " + e.getMessage(), e);
        }
    }
}