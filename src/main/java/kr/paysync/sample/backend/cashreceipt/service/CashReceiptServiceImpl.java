package kr.paysync.sample.backend.cashreceipt.service;

import kr.paysync.sample.backend.cashreceipt.dto.IssueCashReceiptRequest;
import kr.paysync.sample.backend.cashreceipt.model.CashReceipt;
import kr.paysync.sample.backend.paysync.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class CashReceiptServiceImpl implements CashReceiptService {

    private final RestClient client;

    @Override
    public CashReceipt issue(IssueCashReceiptRequest request) {
        ApiResponse<CashReceipt> response = client.post()
                .uri("/v1/cash-receipts")
                .body(request)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if (response == null || response.data() == null || !response.code().equals("CREATED")) {
            throw new IllegalStateException("올바르지 않은 응답입니다.");
        }

        return response.data();
    }

    @Override
    public CashReceipt get(String id) {
        ApiResponse<CashReceipt> response = client.get()
                .uri("/v1/cash-receipts/{id}", id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if (response == null || response.data() == null || !response.code().equals("OK")) {
            throw new IllegalStateException("올바르지 않은 응답입니다.");
        }

        return response.data();
    }

    @Override
    public void revoke(String id) {
        ApiResponse<Void> response = client.post()
                .uri("/v1/cash-receipts/{id}/revoke", id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if (response == null || !response.code().equals("OK")) {
            throw new IllegalStateException("현금영수증 취소 응답이 올바르지 않습니다.");
        }
    }
}