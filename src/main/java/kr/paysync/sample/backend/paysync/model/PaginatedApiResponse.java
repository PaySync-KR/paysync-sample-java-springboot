package kr.paysync.sample.backend.paysync.model;

import org.jspecify.annotations.Nullable;

public record PaginatedApiResponse<T>(
        String code,
        @Nullable T data,
        int totalItems
) {
}