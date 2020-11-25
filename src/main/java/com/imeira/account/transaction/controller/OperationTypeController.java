package com.imeira.account.transaction.controller;


import com.imeira.account.transaction.dto.OperationTypeDTO;
import com.imeira.account.transaction.service.OperationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/operationtypes")
public class OperationTypeController {

    @Autowired
    OperationTypeService operationTypeService;


    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OperationTypeDTO> create(@RequestBody OperationTypeDTO operationTypeDTO) {
        operationTypeDTO = operationTypeService.create(operationTypeDTO);
        return ResponseEntity.ok().body(operationTypeDTO);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<OperationTypeDTO>> findAll() {
        List<OperationTypeDTO> all = operationTypeService.findAll();
        return ResponseEntity.ok().body(all);
    }

}
