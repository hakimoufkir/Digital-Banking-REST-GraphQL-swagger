package com.sid.bank_account_service.repositories;

import com.sid.bank_account_service.entities.BankAccount;
import com.sid.bank_account_service.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    List<BankAccount> findByCurrency (String currency);
    @RestResource(path = "/byType")
    List<BankAccount> findByType (@Param("t") AccountType type);
}
