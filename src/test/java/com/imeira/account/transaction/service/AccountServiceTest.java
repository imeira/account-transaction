package com.imeira.account.transaction.service;

import com.imeira.account.transaction.domain.Account;
import com.imeira.account.transaction.dto.AccountDTO;
import com.imeira.account.transaction.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

class AccountServiceTest {
    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindById() {
        when(accountRepository.findById(any())).thenReturn(Optional.of(new Account()));

        AccountDTO result = accountService.findById(BigInteger.valueOf(1));
        Assertions.assertNotNull(result);
    }

    @Test
    void testCreateIfNotFound() {
        when(accountRepository.findByDocumentNumber(anyString())).thenReturn(Optional.of(new Account()));

        Account result = accountService.createIfNotFound(new Account(BigInteger.valueOf(1), "documentNumber"));
        Assertions.assertNotNull(result);
    }

    @Test
    void testDeleteAll() {
        accountService.deleteAll();
    }

    @Test
    void testFindAll() {
        List<AccountDTO> result = accountService.findAll();
        Assertions.assertNotNull(result);
    }

    @Test
    void testCreate() {
        when(accountRepository.save(any())).thenReturn(new Account(BigInteger.valueOf(1), "documentNumber"));

        AccountDTO result = accountService.create(new AccountDTO(BigInteger.valueOf(1), "documentNumber"));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromEntity() {
        AccountDTO result = accountService.fromEntity(new Account(BigInteger.valueOf(1), "documentNumber"));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromDTO() {
        Account result = accountService.fromDTO(new AccountDTO(BigInteger.valueOf(1), "documentNumber"));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromEntity2() {
        List<AccountDTO> result = accountService.fromEntity(Arrays.<Account>asList(new Account(BigInteger.valueOf(1), "documentNumber")));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromDTO2() {
        List<Account> result = accountService.fromDTO(Arrays.<AccountDTO>asList(new AccountDTO(BigInteger.valueOf(1), "documentNumber")));
        Assertions.assertNotNull(result);
    }
}

