package com.bvan.chatee.service.messaging.exception;

/**
 * @author bvanchuhov
 */
public class MessagingException extends Exception {

    public MessagingException() {
    }

    public MessagingException(String message) {
        super(message);
    }

    public MessagingException(String message, Throwable cause) {
        super(message, cause);
    }
}
