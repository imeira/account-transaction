package com.imeira.account.transaction.service;

import com.imeira.account.transaction.domain.Account;
import com.imeira.account.transaction.dto.AccountDTO;
import com.imeira.account.transaction.dto.TransactionDTO;
import com.imeira.account.transaction.repository.AccountRepository;
import com.imeira.account.transaction.service.exception.InvalidTransactionException;
import com.imeira.account.transaction.service.exception.ObjectAlreadyExistException;
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
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public  AccountDTO findById(BigInteger id) {
        Optional<Account> obj = accountRepository.findById(id);
        return obj.map(this::fromEntity).orElseThrow(() -> new ObjectNotFoundException("Conta nao encontrada!"));
    }

    public Account createIfNotFound(Account account) {
        Optional<Account> obj = accountRepository.findByDocumentNumber(account.getDocumentNumber());
        return  obj.orElseGet(() -> accountRepository.save(account));
    }

    public void deleteAll() {
        accountRepository.deleteAll();
    }

    public  List<AccountDTO> findAll() {
        List<Account> accountList = accountRepository.findAll();
        return fromEntity(accountList);
    }


    public AccountDTO create(AccountDTO accountDTO) {
        Optional<Account> obj = accountRepository.findByDocumentNumber(accountDTO.getDocumentNumber());
        if (obj.isPresent()) {
            throw new ObjectAlreadyExistException(String.format("Já existe uma conta com esse de documento!"));
        }
        return fromEntity(accountRepository.save(fromDTO(accountDTO)));
    }

    public AccountDTO update(AccountDTO accountDTO, TransactionDTO transactionDTO) {
        Objects.requireNonNull(accountDTO, String.format("Conta inválida!"));

        BigDecimal diff = accountDTO.getAvailableCreditLimit().add(transactionDTO.getAmount());
        if (diff.doubleValue() < 0) {
            throw new InvalidTransactionException("Limite de credito indisponivel");
        }
        accountDTO.setAvailableCreditLimit(diff);

        return fromEntity(accountRepository.save(fromDTO(accountDTO)));
    }

    public AccountDTO fromEntity(Account account) {
        return AccountDTO.builder()
                .id(account.getId())
                .documentNumber(account.getDocumentNumber())
                .availableCreditLimit(account.getAvailableCreditLimit())
                .build();
    }

    public Account fromDTO (AccountDTO accountDTO) {
        return Account.builder()
                .id(accountDTO.getId())
                .documentNumber(accountDTO.getDocumentNumber())
                .availableCreditLimit(accountDTO.getAvailableCreditLimit())
                .build();
    }

    public List<AccountDTO> fromEntity(List<Account> account) {
        return account.stream()
                .map(a -> new AccountDTO(a.getId(), a.getDocumentNumber(), a.getAvailableCreditLimit())
        ).collect(Collectors.toList());
    }

    public List<Account> fromDTO (List<AccountDTO> accountDTO) {
        return accountDTO.stream().map(
                a -> new Account(a.getId(), a.getDocumentNumber(), a.getAvailableCreditLimit())
        ).collect(Collectors.toList());
    }


}
