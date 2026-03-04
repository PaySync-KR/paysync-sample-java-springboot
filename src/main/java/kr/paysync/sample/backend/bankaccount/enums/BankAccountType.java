package kr.paysync.sample.backend.bankaccount.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BankAccountType {

    PERSONAL,  // 개인
    CORPORATE  // 기업
}