package com.imeira.account.transaction.controller;

import com.imeira.account.transaction.dto.TransactionDTO;
import com.imeira.account.transaction.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class TransactionControllerTest {
    @Mock
    TransactionService transactionService;
    @InjectMocks
    TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    private TransactionDTO getDto() {
        return new TransactionDTO(BigInteger.valueOf(1), BigInteger.valueOf(1), BigInteger.valueOf(1), new BigDecimal(1110), LocalDateTime.of(2020, Month.NOVEMBER, 25, 21, 14, 45), new BigDecimal(1110));
    }


    @Test
    void testCreate() {
        when(transactionService.create(any())).thenReturn(getDto());

        ResponseEntity<TransactionDTO> result = transactionController.create(getDto());
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build().getStatusCode(), result.getStatusCode());
    }

    @Test
    void testFindAll() {
        when(transactionService.findAll()).thenReturn(Arrays.<TransactionDTO>asList(getDto()));

        ResponseEntity<List<TransactionDTO>> result = transactionController.findAll();
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build().getStatusCode(), result.getStatusCode());
    }
}

