package kr.paysync.sample.backend.transaction.service;

import kr.paysync.sample.backend.transaction.model.Transaction;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface TransactionService {

    Transaction get(String id);

    List<Transaction> getAll(@Nullable String bankAccountId, boolean hidden, boolean matched, @Nullable String dateAfter, @Nullable String dateBefore, long offset, int limit);
}