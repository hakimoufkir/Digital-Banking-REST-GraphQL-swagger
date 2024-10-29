package com.sid.bank_account_service.web;

import com.sid.bank_account_service.dto.BankAccountRequestDTO;
import com.sid.bank_account_service.dto.BankAccountResponseDTO;
import com.sid.bank_account_service.entities.BankAccount;
import com.sid.bank_account_service.mappers.AccountMapper;
import com.sid.bank_account_service.repositories.BankAccountRepository;
import com.sid.bank_account_service.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;


    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id) {
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Bank account with id %s not found", id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.addAccount(requestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount save(@RequestBody BankAccount bankAccount,@PathVariable String id) {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Bank account with id %s not found", id)));

        if(bankAccount.getBalance() != null)account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCreateAt() != null)account.setCreateAt(bankAccount.getCreateAt());
        if(bankAccount.getType() != null)account.setType(bankAccount.getType());
        if(bankAccount.getCurrency() != null)account.setCurrency(bankAccount.getCurrency());

        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }

}
