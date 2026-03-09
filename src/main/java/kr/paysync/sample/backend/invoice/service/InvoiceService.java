package kr.paysync.sample.backend.invoice.service;

import kr.paysync.sample.backend.invoice.dto.IssueInvoiceRequest;
import kr.paysync.sample.backend.invoice.model.Invoice;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface InvoiceService {

    Invoice issue(IssueInvoiceRequest request);

    void delete(String id);

    Invoice get(String id);

    List<Invoice> getAll(@Nullable Boolean paid, @Nullable String rawDateAfter, @Nullable String rawDateBefore, long offset, int limit);

    Invoice markAsPaid(String id);
}