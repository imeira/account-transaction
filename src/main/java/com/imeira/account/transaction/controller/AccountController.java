package com.imeira.account.transaction.controller;


import com.imeira.account.transaction.dto.AccountDTO;
import com.imeira.account.transaction.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;


    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDTO> create(@RequestBody AccountDTO accountDTO) {
        accountDTO = accountService.create(accountDTO);
        return ResponseEntity.ok().body(accountDTO);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<AccountDTO>> findAll() {
        List<AccountDTO> accountDTO = accountService.findAll();
        return ResponseEntity.ok().body(accountDTO);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable String id) {
        AccountDTO accountDTO = accountService.findById(new BigInteger(id));
        return ResponseEntity.ok().body(accountDTO);
    }
}
