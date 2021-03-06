package com.imeira.account.transaction.repository;

import com.imeira.account.transaction.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, BigInteger> {

    Optional<Account> findByDocumentNumber (String documentNumber);

}
