package com.finsavvy.api.finsavvy_api.v1.repositories;

import com.finsavvy.api.finsavvy_api.v1.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
