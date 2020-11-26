package com.imeira.account.transaction.service.exception;

import java.io.Serializable;

public class InvalidTransactionException extends RuntimeException implements Serializable  {

    public InvalidTransactionException(String message) {
        super(message);
    }

}
