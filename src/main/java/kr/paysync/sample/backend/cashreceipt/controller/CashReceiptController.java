package kr.paysync.sample.backend.cashreceipt.controller;

import kr.paysync.sample.backend.cashreceipt.dto.IssueCashReceiptRequest;
import kr.paysync.sample.backend.cashreceipt.model.CashReceipt;
import kr.paysync.sample.backend.cashreceipt.service.CashReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cash-receipts")
@RequiredArgsConstructor
public class CashReceiptController {

    private final CashReceiptService service;

    @PostMapping
    public ResponseEntity<CashReceipt> issue(@RequestBody IssueCashReceiptRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.issue(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CashReceipt> get(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping("/{id}/revoke")
    public ResponseEntity<Void> revoke(@PathVariable String id) {
        service.revoke(id);
        return ResponseEntity.ok().build();
    }
}