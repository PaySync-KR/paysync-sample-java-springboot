package kr.paysync.sample.backend.cashreceipt.service;

import kr.paysync.sample.backend.cashreceipt.dto.IssueCashReceiptRequest;
import kr.paysync.sample.backend.cashreceipt.model.CashReceipt;

public interface CashReceiptService {

    CashReceipt issue(IssueCashReceiptRequest request);

    CashReceipt get(String id);

    void revoke(String id);
}