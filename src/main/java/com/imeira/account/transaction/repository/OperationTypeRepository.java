package com.imeira.account.transaction.repository;

import com.imeira.account.transaction.domain.OperationType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationTypeRepository extends MongoRepository<OperationType, Long> {

    Optional<OperationType> findByDescription(String description);

}
