package com.finsavvy.api.finsavvy_api.v1.repositories;

import com.finsavvy.api.finsavvy_api.v1.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryCurrencyRepository extends JpaRepository<Budget.CountryCurrency, Long> {
    Optional<Budget.CountryCurrency> findByUuid(String uuid);
}
