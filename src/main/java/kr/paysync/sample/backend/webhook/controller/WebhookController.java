package kr.paysync.sample.backend.webhook.controller;

import kr.paysync.sample.backend.webhook.service.WebhookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
            @RequestHeader Map<String, String> headers,
            @RequestBody String rawBody
    ) {
        service.handleWebhook(
                headers.entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                e -> List.of(e.getValue())
                        )),
                rawBody
        );
        return ResponseEntity.ok().build();
    }
}