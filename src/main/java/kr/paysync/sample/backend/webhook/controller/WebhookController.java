package kr.paysync.sample.backend.webhook.controller;

import kr.paysync.sample.backend.webhook.service.WebhookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/webhooks")
@RequiredArgsConstructor
public class WebhookController {

    private final WebhookService service;

    @PostMapping
    public ResponseEntity<Void> handleWebhook(
            @RequestHeader HttpHeaders headers,
            @RequestBody String rawBody
    ) {
        service.handleWebhook(
                headers.headerSet()
                        .stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue
                        )),
                rawBody
        );

        return ResponseEntity.ok().build();
    }
}