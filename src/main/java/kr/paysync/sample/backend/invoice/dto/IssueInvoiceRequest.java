package kr.paysync.sample.backend.invoice.dto;

import kr.paysync.sample.backend.cashreceipt.enums.CashReceiptType;
import org.jspecify.annotations.Nullable;

import java.util.Map;

public record IssueInvoiceRequest(
        Customer customer,
        int amount,
        @Nullable CashReceipt cashReceipt,
        @Nullable String expireAfter,
        @Nullable Map<String, String> metadata
) {

    public record Customer(
            String name,
            @Nullable String email,
            @Nullable String phoneNumber
    ) {
    }

    public record CashReceipt(
            CashReceiptType type,
            String identifier
    ) {
    }
}