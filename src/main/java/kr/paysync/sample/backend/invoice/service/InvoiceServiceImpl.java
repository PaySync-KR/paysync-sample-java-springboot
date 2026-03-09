package kr.paysync.sample.backend.invoice.service;

import kr.paysync.sample.backend.invoice.dto.IssueInvoiceRequest;
import kr.paysync.sample.backend.invoice.model.Invoice;
import kr.paysync.sample.backend.paysync.model.ApiResponse;
import kr.paysync.sample.backend.paysync.model.PaginatedApiResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final RestClient client;

    @Override
    public Invoice issue(IssueInvoiceRequest request) {
        ApiResponse<Invoice> response = client.post()
                .uri("/v1/invoices")
                .body(request)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if (response == null || response.data() == null || !response.code().equals("CREATED")) {
            throw new IllegalStateException("올바르지 않은 응답입니다.");
        }

        return response.data();
    }

    @Override
    public void delete(String id) {
        ApiResponse<Void> response = client.delete()
                .uri("/v1/invoices/{id}", id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if (response == null || !response.code().equals("DELETED")) {
            throw new IllegalStateException("올바르지 않은 응답입니다.");
        }
    }

    @Override
    public Invoice get(String id) {
        ApiResponse<Invoice> response = client.get()
                .uri("/v1/invoices/{id}", id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if (response == null || response.data() == null || !response.code().equals("OK")) {
            throw new IllegalStateException("올바르지 않은 응답입니다.");
        }

        return response.data();
    }

    @Override
    public List<Invoice> getAll(@Nullable Boolean paid, @Nullable String dateAfter, @Nullable String dateBefore, long offset, int limit) {
        PaginatedApiResponse<List<Invoice>> response =
                client.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/v1/invoices")
                                .queryParamIfPresent("paid", Optional.ofNullable(paid))
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

    @Override
    public Invoice markAsPaid(String id) {
        ApiResponse<Invoice> response = client.post()
                .uri("/v1/invoices/{id}/mark-as-paid", id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if (response == null || response.data() == null || !response.code().equals("OK")) {
            throw new IllegalStateException("올바르지 않은 응답입니다.");
        }

        return response.data();
    }
}