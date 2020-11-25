package com.imeira.account.transaction.controller;


import com.imeira.account.transaction.dto.TransactionDTO;
import com.imeira.account.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;


    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTO> create(@RequestBody TransactionDTO transactionDTO) {
        transactionDTO = transactionService.create(transactionDTO);
        return ResponseEntity.ok().body(transactionDTO);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<TransactionDTO>> findAll() {
        List<TransactionDTO> all = transactionService.findAll();
        return ResponseEntity.ok().body(all);
    }

}
