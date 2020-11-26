package com.imeira.account.transaction.controller;

import com.imeira.account.transaction.dto.OperationTypeDTO;
import com.imeira.account.transaction.service.OperationTypeService;
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

class OperationTypeControllerTest {
    @Mock
    OperationTypeService operationTypeService;
    @InjectMocks
    OperationTypeController operationTypeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreate() {
        when(operationTypeService.create(any())).thenReturn(new OperationTypeDTO(BigInteger.valueOf(1), "description", true));

        ResponseEntity<OperationTypeDTO> result = operationTypeController.create(new OperationTypeDTO(BigInteger.valueOf(1), "description", true));
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build().getStatusCode(), result.getStatusCode());
    }

    @Test
    void testFindAll() {
        when(operationTypeService.findAll()).thenReturn(Arrays.<OperationTypeDTO>asList(new OperationTypeDTO(BigInteger.valueOf(1), "description", true)));

        ResponseEntity<List<OperationTypeDTO>> result = operationTypeController.findAll();
        Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build().getStatusCode(), result.getStatusCode());
    }
}

