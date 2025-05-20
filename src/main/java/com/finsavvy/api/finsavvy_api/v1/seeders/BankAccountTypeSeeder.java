package com.finsavvy.api.finsavvy_api.v1.seeders;

import com.finsavvy.api.finsavvy_api.v1.entities.account.BankAccountType;
import com.finsavvy.api.finsavvy_api.v1.repositories.BankAccountTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Order(1)
@Slf4j
@RequiredArgsConstructor
public class BankAccountTypeSeeder implements CommandLineRunner {

    private final BankAccountTypeRepository bankAccountTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        seedBankAccountTypes();
    }

    private void seedBankAccountTypes() {
        if (bankAccountTypeRepository.count() == 0) {
            log.info("Seeding BankAccountType data using Builder pattern...");

            Set<BankAccountType> accountTypes = Set.of(
                    BankAccountType.builder()
                            .bankAccountTypeName("Checking Account")
                            .bankAccountTypeDescription("")
                            .build(),
                    BankAccountType.builder()
                            .bankAccountTypeName("Savings Account")
                            .bankAccountTypeDescription("")
                            .build(),
                    BankAccountType.builder()
                            .bankAccountTypeName("Joint Account")
                            .bankAccountTypeDescription("")
                            .build(),
                    BankAccountType.builder()
                            .bankAccountTypeName("Student Account")
                            .bankAccountTypeDescription("")
                            .build(),
                    BankAccountType.builder()
                            .bankAccountTypeName("Payroll Account")
                            .bankAccountTypeDescription("")
                            .build(),
                    BankAccountType.builder()
                            .bankAccountTypeName("Current Account")
                            .bankAccountTypeDescription("")
                            .build()
            );
            bankAccountTypeRepository.saveAll(accountTypes);
            log.info("{} BankAccountType records seeded.", accountTypes.size());
        } else {
            log.info("BankAccountType data already exists. Skipping seed.");
        }
    }
}
