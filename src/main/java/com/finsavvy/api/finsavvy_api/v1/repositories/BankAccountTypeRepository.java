package com.finsavvy.api.finsavvy_api.v1.repositories;

import com.finsavvy.api.finsavvy_api.v1.entities.account.BankAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountTypeRepository extends JpaRepository<BankAccountType, Long> {
    Optional<BankAccountType> findByUuid(String uuid);

}
