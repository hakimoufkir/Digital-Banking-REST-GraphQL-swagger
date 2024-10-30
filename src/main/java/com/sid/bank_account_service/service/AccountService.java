package com.sid.bank_account_service.service;

import com.sid.bank_account_service.dto.BankAccountRequestDTO;
import com.sid.bank_account_service.dto.BankAccountResponseDTO;

public interface AccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);

    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
}
