package com.simplepaymentplatform.repository;

import com.simplepaymentplatform.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
