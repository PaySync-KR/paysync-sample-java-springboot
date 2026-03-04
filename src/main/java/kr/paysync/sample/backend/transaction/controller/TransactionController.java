package kr.paysync.sample.backend.transaction.controller;

import kr.paysync.sample.backend.transaction.model.Transaction;
import kr.paysync.sample.backend.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> get(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAll(
            @RequestParam(required = false) String bankAccountId,
            @RequestParam(required = false) boolean hidden,
            @RequestParam(required = false) boolean matched,
            @RequestParam(required = false) String dateAfter,
            @RequestParam(required = false) String dateBefore,
            @RequestParam(defaultValue = "0") long offset,
            @RequestParam(defaultValue = "20") int limit
    ) {
        return ResponseEntity.ok(service.getAll(bankAccountId, hidden, matched, dateAfter, dateBefore, offset, limit));
    }
}