package com.sid.bank_account_service;

import com.sid.bank_account_service.entities.BankAccount;
import com.sid.bank_account_service.entities.Customer;
import com.sid.bank_account_service.enums.AccountType;
import com.sid.bank_account_service.repositories.BankAccountRepository;
import com.sid.bank_account_service.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}
@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository) {
		return args -> {
			Stream.of("Mohammed", "Yassine", "Hanae", "Imane").forEach(c -> {
				Customer customer = Customer.builder()
						.name(c)
						.build();
				customerRepository.save(customer);
			});

			customerRepository.findAll().forEach(c -> {
				for(int i = 0; i< 10; i++){
					BankAccount bankAccount = BankAccount.builder()
							.id(UUID.randomUUID().toString())
							.type(Math.random()> 0.5? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
							.balance(Math.random()* 90000)
							.createAt(new Date())
							.currency("MAD")
							.customer(c)
							.build();
					bankAccountRepository.save(bankAccount);
				}
			});
			for(int i = 0; i< 10; i++){
				BankAccount bankAccount = BankAccount.builder()
						.id(UUID.randomUUID().toString())
						.type(Math.random()> 0.5? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
						.balance(Math.random()* 90000)
						.createAt(new Date())
						.currency("MAD")
						.build();
				bankAccountRepository.save(bankAccount);
			}
		};
}
}
