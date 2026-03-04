package kr.paysync.sample.backend.paysync.model;

import org.jspecify.annotations.Nullable;

public record ApiResponse<T>(
        String code,
        @Nullable T data
) {
}