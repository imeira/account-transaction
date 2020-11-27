package com.imeira.account.transaction.service;

import com.imeira.account.transaction.domain.Account;
import com.imeira.account.transaction.domain.OperationType;
import com.imeira.account.transaction.domain.Transaction;
import com.imeira.account.transaction.dto.AccountDTO;
import com.imeira.account.transaction.dto.OperationTypeDTO;
import com.imeira.account.transaction.dto.TransactionDTO;
import com.imeira.account.transaction.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class TransactionServiceTest {
    @Mock
    TransactionRepository transactionRepository;
    @InjectMocks
    TransactionService transactionService;

    @Mock
    AccountService accountService;

    @Mock
    OperationTypeService operationTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDeleteAll() {
        transactionService.deleteAll();
    }

    private TransactionDTO getDto() {
        return new TransactionDTO(BigInteger.valueOf(1), BigInteger.valueOf(1), BigInteger.valueOf(1), new BigDecimal(1110), LocalDateTime.of(2020, Month.NOVEMBER, 25, 21, 14, 45), new BigDecimal(1110));
    }

    private Transaction getEntity() {
        return new Transaction(BigInteger.valueOf(1), new Account(BigInteger.valueOf(1), "documentNumber", BigDecimal.valueOf(1000)), new OperationType(BigInteger.valueOf(1), "description", true), new BigDecimal(1110), LocalDateTime.of(2020, Month.NOVEMBER, 25, 21, 13, 37), new BigDecimal(1110));
    }

    @Test
    void testFindById() {
        when(transactionRepository.findById(any())).thenReturn(Optional.of(getEntity()));

        TransactionDTO result = transactionService.findById(BigInteger.valueOf(1));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindAll() {
        List<TransactionDTO> result = transactionService.findAll();
        Assertions.assertNotNull(result);
    }

    @Test
    void testCreateIfNotFound() {
        Transaction result = transactionService.createIfNotFound(getEntity());
        Assertions.assertNull(result);
    }

    @Test
    void testCreate() {
        when(accountService.findById(any())).thenReturn(new AccountDTO(BigInteger.valueOf(1), "documentNumber", BigDecimal.valueOf(1000)));
        when(operationTypeService.findById(any())).thenReturn(new OperationTypeDTO(BigInteger.valueOf(1), "COMPRA A VISTA", true));
        when(transactionRepository.save(any())).thenReturn(getEntity());


        TransactionDTO result = transactionService.create(getDto());
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromEntity() {
        TransactionDTO result = transactionService.fromEntity(getEntity());
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromDTO() {
        Transaction result = transactionService.fromDTO(getDto());
        Assertions.assertNotNull(result);
    }


}

