package com.imeira.account.transaction.service;

import com.imeira.account.transaction.domain.OperationType;
import com.imeira.account.transaction.dto.OperationTypeDTO;
import com.imeira.account.transaction.repository.OperationTypeRepository;
import com.imeira.account.transaction.service.exception.ObjectAlreadyExistException;
import com.imeira.account.transaction.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OperationTypeService {

    @Autowired
    private OperationTypeRepository operationTypeRepository;


    public void deleteAll() {
        operationTypeRepository.deleteAll();
    }

    public OperationTypeDTO findById(BigInteger id) {
        Optional<OperationType> obj = operationTypeRepository.findById(id);
        return obj.map(this::fromEntity).orElseThrow(() -> new ObjectNotFoundException("Tipo de operação não encontrado!"));
    }


    public  List<OperationTypeDTO> findAll() {
        List<OperationType> all = operationTypeRepository.findAll();
        return fromEntity(all);
    }

    public OperationTypeDTO findByDescription(String description) {
        Optional<OperationType> obj = operationTypeRepository.findByDescription(description);
        return obj.map(this::fromEntity).orElse(null);
    }

    public OperationType createIfNotFound(OperationType operationType) {
        Optional<OperationType> obj = operationTypeRepository.findByDescription(operationType.getDescription());
        return obj.orElseGet(() -> operationTypeRepository.save(operationType));
    }

    public OperationTypeDTO create(OperationTypeDTO operationTypeDTO) {
        Optional<OperationType> obj = operationTypeRepository.findByDescription(operationTypeDTO.getDescription());
        if (obj.isPresent()) {
            throw new ObjectAlreadyExistException(String.format("Já existe um tipo de operação com essa descrição!"));
        }
        return fromEntity(operationTypeRepository.save(fromDTO(operationTypeDTO)));
    }

    public OperationTypeDTO fromEntity(OperationType operationType) {
        return OperationTypeDTO.builder()
                .id(operationType.getId())
                .description(operationType.getDescription())
                .negative(operationType.isNegative())
                .build();
    }

    public OperationType fromDTO (OperationTypeDTO operationTypeDTO) {
        return OperationType.builder()
                .id(operationTypeDTO.getId())
                .description(operationTypeDTO.getDescription())
                .negative(operationTypeDTO.isNegative())
                .build();
    }

    public List<OperationTypeDTO> fromEntity(List<OperationType> operationTypes) {
        return operationTypes.stream()
                .map(a -> new OperationTypeDTO(a.getId(), a.getDescription(), a.isNegative())
        ).collect(Collectors.toList());
    }

    public List<OperationType> fromDTO (List<OperationTypeDTO> dtos) {
        return dtos.stream().map(
                a -> new OperationType(a.getId(), a.getDescription(), a.isNegative())
        ).collect(Collectors.toList());
    }


}
