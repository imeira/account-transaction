package com.imeira.account.transaction.repository;

import com.imeira.account.transaction.domain.Account;
import com.imeira.account.transaction.domain.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, BigInteger> {

    Optional<List<Transaction>> findByAccount(Account account);


}
