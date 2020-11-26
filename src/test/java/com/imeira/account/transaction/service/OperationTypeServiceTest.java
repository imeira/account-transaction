package com.imeira.account.transaction.service;

import com.imeira.account.transaction.domain.Account;
import com.imeira.account.transaction.domain.OperationType;
import com.imeira.account.transaction.dto.OperationTypeDTO;
import com.imeira.account.transaction.repository.OperationTypeRepository;
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

import static org.mockito.Mockito.*;

class OperationTypeServiceTest {
    @Mock
    OperationTypeRepository operationTypeRepository;
    @InjectMocks
    OperationTypeService operationTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDeleteAll() {
        operationTypeService.deleteAll();
    }

    @Test
    void testFindById() {
        when(operationTypeRepository.findById(any())).thenReturn(Optional.of(new OperationType()));

        OperationTypeDTO result = operationTypeService.findById(BigInteger.valueOf(1));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindAll() {
        List<OperationTypeDTO> result = operationTypeService.findAll();
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindByDescription() {
        when(operationTypeRepository.findByDescription(anyString())).thenReturn(Optional.of(new OperationType()));

        OperationTypeDTO result = operationTypeService.findByDescription("description");
        Assertions.assertNotNull(result);
    }

    @Test
    void testCreateIfNotFound() {
        when(operationTypeRepository.findByDescription(anyString())).thenReturn(Optional.of(new OperationType()));

        OperationType result = operationTypeService.createIfNotFound(new OperationType(BigInteger.valueOf(1), "description", true));
        Assertions.assertNotNull(result);
    }

    @Test
    void testCreate() {
        when(operationTypeRepository.save(any())).thenReturn(new OperationType(BigInteger.valueOf(1), "description", true));

        OperationTypeDTO result = operationTypeService.create(new OperationTypeDTO(BigInteger.valueOf(1), "description", true));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromEntity() {
        OperationTypeDTO result = operationTypeService.fromEntity(new OperationType(BigInteger.valueOf(1), "description", true));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromDTO() {
        OperationType result = operationTypeService.fromDTO(new OperationTypeDTO(BigInteger.valueOf(1), "description", true));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromEntity2() {
        List<OperationTypeDTO> result = operationTypeService.fromEntity(Arrays.<OperationType>asList(new OperationType(BigInteger.valueOf(1), "description", true)));
        Assertions.assertNotNull(result);
    }

    @Test
    void testFromDTO2() {
        List<OperationType> result = operationTypeService.fromDTO(Arrays.<OperationTypeDTO>asList(new OperationTypeDTO(BigInteger.valueOf(1), "description", true)));
        Assertions.assertNotNull(result);
    }
}

