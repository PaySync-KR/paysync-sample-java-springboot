package kr.paysync.sample.backend.transaction.service;

import kr.paysync.sample.backend.paysync.model.ApiResponse;
import kr.paysync.sample.backend.paysync.model.PaginatedApiResponse;
import kr.paysync.sample.backend.transaction.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final RestClient client;

    @Override
    public Transaction get(String id) {
        ApiResponse<Transaction> response = client.get()
                .uri("/v1/transactions/{id}", id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if (response == null || response.data() == null || !response.code().equals("OK")) {
            throw new IllegalStateException("올바르지 않은 응답입니다.");
        }

        return response.data();
    }

    @Override
    public List<Transaction> getAll(@Nullable String bankAccountId, @Nullable Boolean hidden, @Nullable Boolean matched, @Nullable String dateAfter, @Nullable String dateBefore, long offset, int limit) {
        PaginatedApiResponse<List<Transaction>> response =
                client.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/v1/invoices")
                                .queryParamIfPresent("bankAccountId", Optional.ofNullable(bankAccountId))
                                .queryParamIfPresent("hidden", Optional.ofNullable(hidden))
                                .queryParamIfPresent("matched", Optional.ofNullable(matched))
                                .queryParamIfPresent("dateAfter", Optional.ofNullable(dateAfter))
                                .queryParamIfPresent("dateBefore", Optional.ofNullable(dateBefore))
                                .queryParam("offset", offset)
                                .queryParam("limit", limit)
                                .build()
                        )
                        .retrieve()
                        .body(new ParameterizedTypeReference<>() {});

        if (response == null || response.data() == null || !response.code().equals("OK")) {
            throw new IllegalStateException("올바르지 않은 응답입니다.");
        }

        return response.data();
    }
}