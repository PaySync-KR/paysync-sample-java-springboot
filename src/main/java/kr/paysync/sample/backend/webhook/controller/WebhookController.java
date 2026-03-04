package kr.paysync.sample.backend.webhook.controller;

import kr.paysync.sample.backend.webhook.service.WebhookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhooks")
@RequiredArgsConstructor
public class WebhookController {

    private final WebhookService service;

    @PostMapping
    public void handleWebhook(
            @RequestHeader HttpHeaders headers,
            @RequestBody String rawBody
    ) {
        service.handleWebhook(headers, rawBody);
    }
}