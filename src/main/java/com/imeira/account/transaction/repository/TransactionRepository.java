package com.imeira.account.transaction.repository;

import com.imeira.account.transaction.domain.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, Long> {

}
