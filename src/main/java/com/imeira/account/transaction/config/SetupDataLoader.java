package com.imeira.account.transaction.config;

import com.imeira.account.transaction.domain.Account;
import com.imeira.account.transaction.domain.OperationType;
import com.imeira.account.transaction.domain.Transaction;
import com.imeira.account.transaction.service.AccountService;
import com.imeira.account.transaction.service.OperationTypeService;
import com.imeira.account.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    AccountService accountService;

    @Autowired
    OperationTypeService operationTypeService;

    @Autowired
    TransactionService transactionService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        //cleanTables();
        insertTables();

    }

    private void cleanTables() {
        accountService.deleteAll();
        operationTypeService.deleteAll();
        transactionService.deleteAll();
    }

    private void insertTables() {

        //create accounts
        Account account = new Account(1L, "12345678900");
        accountService.createIfNotFound(account);
        Account account2 = new Account(2L, "82532303549");
        accountService.createIfNotFound(account2);

        //create operationTypes
        OperationType operationTypeCash = new OperationType(1L, "COMPRA A VISTA", true);
        operationTypeService.createIfNotFound(operationTypeCash);
        OperationType operationTypeInstallments = new OperationType(2L, "COMPRA PARCELADA", true);
        operationTypeService.createIfNotFound(operationTypeInstallments);
        OperationType operationTypeWithdraw = new OperationType(3L, "SAQUE", true);
        operationTypeService.createIfNotFound(operationTypeWithdraw);
        OperationType operationTypePayment = new OperationType(4L, "PAGAMENTO", false);
        operationTypeService.createIfNotFound(operationTypePayment);

        //create transactions
        transactionService.createIfNotFound(Transaction.builder()
                            .id(1L)
                            .account(account)
                            .operationType(operationTypeCash)
                            .amount(BigDecimal.valueOf(1000.50))
                            .eventDate(LocalDateTime.now()).build());
        transactionService.createIfNotFound((Transaction.builder()
                .id(2L)
                .account(account2)
                .operationType(operationTypePayment)
                .amount(BigDecimal.valueOf(57853.50))
                .eventDate(LocalDateTime.now()).build()));
    }


}
