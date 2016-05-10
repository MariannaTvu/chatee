package com.bvan.chatee.service.account.exception;

import com.bvan.chatee.service.messaging.exception.MessagingException;

/**
 * Created by Maryana on 10.05.2016.
 */
public class AccountNotFoundException extends MessagingException {

    public AccountNotFoundException(int accountId) {
        super("account " + accountId + " not found");
    }
}

