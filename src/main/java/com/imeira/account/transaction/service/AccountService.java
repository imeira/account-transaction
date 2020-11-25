package com.imeira.account.transaction.service;

import com.imeira.account.transaction.domain.Account;
import com.imeira.account.transaction.dto.AccountDTO;
import com.imeira.account.transaction.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public  AccountDTO findById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.map(this::fromEntity).orElse(null);
    }

    public Account createIfNotFound(Account account) {
        Optional<Account> obj = accountRepository.findByDocumentNumber(account.getDocumentNumber());
        return obj.orElseGet(() -> accountRepository.save(account));
    }

    public void deleteAll() {
        accountRepository.deleteAll();
    }

    public  List<AccountDTO> findAll() {
        List<Account> accountList = accountRepository.findAll();
        return fromEntity(accountList);
    }


    public AccountDTO create(AccountDTO accountDTO) {
        return fromEntity(accountRepository.save(fromDTO(accountDTO)));
    }

    public AccountDTO fromEntity(Account account) {
        return AccountDTO.builder()
                .id(account.getId())
                .documentNumber(account.getDocumentNumber())
                .build();
    }

    public Account fromDTO (AccountDTO accountDTO) {
        return Account.builder()
                .id(accountDTO.getId())
                .documentNumber(accountDTO.getDocumentNumber())
                .build();
    }

    public List<AccountDTO> fromEntity(List<Account> account) {
        return account.stream()
                .map(a -> new AccountDTO(a.getId(), a.getDocumentNumber())
        ).collect(Collectors.toList());
    }

    public List<Account> fromDTO (List<AccountDTO> accountDTO) {
        return accountDTO.stream().map(
                a -> new Account(a.getId(), a.getDocumentNumber())
        ).collect(Collectors.toList());
    }


}
