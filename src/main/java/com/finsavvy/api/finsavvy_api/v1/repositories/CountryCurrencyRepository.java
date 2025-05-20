package com.finsavvy.api.finsavvy_api.v1.repositories;

import com.finsavvy.api.finsavvy_api.v1.entities.account.CountryCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryCurrencyRepository extends JpaRepository<CountryCurrency, Long> {
    Optional<CountryCurrency> findByUuid(String uuid);
}
