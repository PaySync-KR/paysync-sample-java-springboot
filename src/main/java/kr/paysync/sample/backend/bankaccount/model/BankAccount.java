package kr.paysync.sample.backend.bankaccount.model;

import kr.paysync.sample.backend.bankaccount.enums.BankAccountProvider;
import kr.paysync.sample.backend.bankaccount.enums.BankAccountType;
import org.jspecify.annotations.Nullable;

import java.util.Date;

public record BankAccount(
        String id,
        Boolean active,
        String ownerId,
        String alias,
        BankAccountProvider provider,
        BankAccountType type,
        String number,
        Date createdAt,
        @Nullable Date verifiedAt
) {
}