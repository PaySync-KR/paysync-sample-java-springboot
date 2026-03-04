package kr.paysync.sample.backend.transaction.model;

import kr.paysync.sample.backend.bankaccount.model.BankAccount;
import org.jspecify.annotations.Nullable;

import java.util.Date;

public record Transaction(
        String id,
        BankAccount bankAccount,
        @Nullable String matchedInvoiceId,
        int amount,
        String description,
        boolean hidden,
        Date receivedAt,
        @Nullable Date matchedAt,
        Date createdAt
) {
}