package kr.paysync.sample.backend.cashreceipt.dto;

import kr.paysync.sample.backend.cashreceipt.enums.CashReceiptType;

public record IssueCashReceiptRequest(
        CashReceiptType type,
        String identifier,
        int amount
) {
}