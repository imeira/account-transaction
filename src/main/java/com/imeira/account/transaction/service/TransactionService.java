package com.imeira.account.transaction.service;

import com.imeira.account.transaction.domain.Account;
import com.imeira.account.transaction.domain.OperationType;
import com.imeira.account.transaction.domain.Transaction;
import com.imeira.account.transaction.dto.AccountDTO;
import com.imeira.account.transaction.dto.OperationTypeDTO;
import com.imeira.account.transaction.dto.TransactionDTO;
import com.imeira.account.transaction.repository.TransactionRepository;
import com.imeira.account.transaction.service.exception.InvalidTransactionException;
import com.imeira.account.transaction.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    OperationTypeService operationTypeService;

    @Autowired
    AccountService accountService;



    public void deleteAll() {
        transactionRepository.deleteAll();
    }

    public TransactionDTO findById(BigInteger id) {
        Optional<Transaction> obj = transactionRepository.findById(id);
        return obj.map(this::fromEntity).orElseThrow(() -> new ObjectNotFoundException("Transação não encontrada!"));
    }

    public List<TransactionDTO> find(Account account) {
        Optional<List<Transaction>> all = transactionRepository.findByAccount(account);
        return fromEntity(all.orElseThrow(() -> new ObjectNotFoundException("Transação não encontrada por conta!")));
    }


    public  List<TransactionDTO> findAll() {
        List<Transaction> all = transactionRepository.findAll();
        return fromEntity(all);
    }

    public Transaction createIfNotFound(Transaction transaction) {
        Optional<Transaction> obj = transactionRepository.findById(transaction.getId());
        return obj.orElseGet(() -> transactionRepository.save(transaction));
    }


    public TransactionDTO create(TransactionDTO transactionDTO) {

        try {
            AccountDTO accountDTO = accountService.findById(transactionDTO.getAccountId());
            Objects.requireNonNull(accountDTO, String.format("Conta inválida!"));
            Objects.requireNonNull(transactionDTO.getAmount(), String.format("Amount inválido!"));

            OperationTypeDTO operationTypeDTO = operationTypeService.findById(transactionDTO.getOperationTypeId());
            Objects.requireNonNull(operationTypeDTO, String.format("Tipo de operação inválida!"));

            calculateValues(transactionDTO, operationTypeDTO);

            accountService.update(accountDTO, transactionDTO);
            
            transactionDTO = fromEntity(transactionRepository.save(fromDTO(transactionDTO)));

        } catch (Exception e) {
            throw new InvalidTransactionException(e.getMessage());
        }
        return transactionDTO;
    }

    public void calculateValues(TransactionDTO transactionDTO, OperationTypeDTO operationTypeDTO) {
        if (operationTypeDTO.isNegative()) {
            transactionDTO.setAmount(transactionDTO.getAmount().multiply(BigDecimal.valueOf(-1)));
            transactionDTO.setBalance(transactionDTO.getAmount());
        } else {
            List<TransactionDTO> transactionDTOS = find(Account.builder().id(transactionDTO.getAccountId()).build());
            Double summingDouble = transactionDTOS.stream()
                    .filter(t -> t.getBalance().doubleValue() > 0)
                    .collect(Collectors.summingDouble(t -> t.getBalance().doubleValue()));

            transactionDTO.setBalance(transactionDTO.getAmount().add(BigDecimal.valueOf(summingDouble)));
        }
    }

    public TransactionDTO fromEntity(Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .accountId(transaction.getAccount().getId())
                .operationTypeId(transaction.getOperationType().getId())
                .amount(transaction.getAmount())
                .balance(transaction.getBalance())
                .eventDate(transaction.getEventDate()).build();
    }


    public Transaction fromDTO (TransactionDTO transactionDTO) {
        return Transaction.builder()
                .id(transactionDTO.getId())
                .account(Account.builder().id(transactionDTO.getAccountId()).build())
                .operationType(OperationType.builder().id(transactionDTO.getAccountId()).build())
                .amount(transactionDTO.getAmount())
                .balance(transactionDTO.getBalance())
                .eventDate(transactionDTO.getEventDate()).build();
    }

    public List<TransactionDTO> fromEntity(List<Transaction> transactions) {
        return transactions.stream()
                .map(a -> new TransactionDTO(a.getId(), a.getAccount().getId(), a.getOperationType().getId(), a.getAmount(), a.getEventDate(), a.getBalance())
        ).collect(Collectors.toList());
    }

    public List<Transaction> fromDTO (List<TransactionDTO> dtos) {
        return dtos.stream().map(
                a -> new Transaction(a.getId(), Account.builder().id(a.getAccountId()).build(), OperationType.builder().id(a.getAccountId()).build(), a.getAmount(), a.getEventDate(), a.getBalance())
        ).collect(Collectors.toList());
    }

}
