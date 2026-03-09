package kr.paysync.sample.backend.invoice.controller;

import kr.paysync.sample.backend.invoice.dto.IssueInvoiceRequest;
import kr.paysync.sample.backend.invoice.model.Invoice;
import kr.paysync.sample.backend.invoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService service;

    @PostMapping
    public ResponseEntity<Invoice> issue(@RequestBody IssueInvoiceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.issue(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> get(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAll(
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) String dateAfter,
            @RequestParam(required = false) String dateBefore,
            @RequestParam(defaultValue = "0") long offset,
            @RequestParam(defaultValue = "20") int limit
    ) {
        return ResponseEntity.ok(service.getAll(paid, dateAfter, dateBefore, offset, limit));
    }

    @PostMapping("/{id}/mark-as-paid")
    public ResponseEntity<Invoice> markAsPaid(@PathVariable String id) {
        return ResponseEntity.ok(service.markAsPaid(id));
    }
}