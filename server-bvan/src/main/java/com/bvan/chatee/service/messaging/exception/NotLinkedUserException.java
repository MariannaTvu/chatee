package com.bvan.chatee.service.messaging.exception;

/**
 * @author bvanchuhov
 */
public class NotLinkedUserException extends MessagingException {

    public NotLinkedUserException(int conversationId, int userId) {
        super("user " + userId + " is not linked to conversation " + conversationId);
    }
}
