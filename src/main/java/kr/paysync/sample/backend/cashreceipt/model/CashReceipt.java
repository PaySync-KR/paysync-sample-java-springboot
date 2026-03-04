package kr.paysync.sample.backend.cashreceipt.model;

import kr.paysync.sample.backend.cashreceipt.enums.CashReceiptStatus;
import kr.paysync.sample.backend.cashreceipt.enums.CashReceiptType;
import org.jspecify.annotations.Nullable;

import java.util.Date;

public record CashReceipt(
        String id,
        CashReceiptStatus status,
        String issuerId,
        String corpNum,
        CashReceiptType type,
        String identifier,
        int amount,
        @Nullable String relatedInvoiceId,
        String ntsConfirmNum,
        @Nullable String ntsResultCode,
        Date issuedAt,
        @Nullable Date revokedAt
) {
}