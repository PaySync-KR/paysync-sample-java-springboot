package kr.paysync.sample.backend.invoice.model;

import kr.paysync.sample.backend.cashreceipt.enums.CashReceiptType;
import org.jspecify.annotations.Nullable;

import java.util.Date;
import java.util.Map;

public record Invoice(
        String id,
        String issuerId,
        Customer customer,
        @Nullable CashReceipt cashReceipt,
        int amount,
        boolean paid,
        @Nullable Map<String, String> metadata,
        Date issuedAt,
        @Nullable Date expiresAt,
        @Nullable Date deletedAt
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