package com.imeira.account.transaction.controller;

import com.imeira.account.transaction.dto.AccountDTO;
import com.imeira.account.transaction.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class AccountControllerTest {
    @Mock
    AccountService accountService;
    @InjectMocks
    AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreate() {
        when(accountService.create(any())).thenReturn(new AccountDTO(BigInteger.valueOf(1), "documentNumber"));

        ResponseEntity<AccountDTO> result = accountController.create(new AccountDTO(BigInteger.valueOf(1), "documentNumber"));
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build().getStatusCode(), result.getStatusCode());
    }

    @Test
    void testFindAll() {
        when(accountService.findAll()).thenReturn(Arrays.<AccountDTO>asList(new AccountDTO(BigInteger.valueOf(1), "documentNumber")));

        ResponseEntity<List<AccountDTO>> result = accountController.findAll();
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build().getStatusCode(), result.getStatusCode());
    }

    @Test
    void testFindById() {
        when(accountService.findById(any())).thenReturn(new AccountDTO(BigInteger.valueOf(1), "documentNumber"));

        ResponseEntity<AccountDTO> result = accountController.findById("1");
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build().getStatusCode(), result.getStatusCode());
    }
}
