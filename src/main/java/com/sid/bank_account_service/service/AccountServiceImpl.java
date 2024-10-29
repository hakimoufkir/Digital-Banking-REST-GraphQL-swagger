package com.sid.bank_account_service.service;

import com.sid.bank_account_service.dto.BankAccountRequestDTO;
import com.sid.bank_account_service.dto.BankAccountResponseDTO;
import com.sid.bank_account_service.entities.BankAccount;
import com.sid.bank_account_service.mappers.AccountMapper;
import com.sid.bank_account_service.repositories.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO){
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO =
                BankAccountResponseDTO.builder()
                        .id(savedBankAccount.getId())
                        .type(savedBankAccount.getType())
                        .createAt(savedBankAccount.getCreateAt())
                        .balance(savedBankAccount.getBalance())
                        .build();
        return bankAccountResponseDTO;
    }
}
