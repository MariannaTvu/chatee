package com.bvan.chatee.service.messaging;

import com.bvan.chatee.service.messaging.exception.ConversationNotFoundException;
import com.bvan.chatee.service.messaging.exception.MessagingException;
import com.bvan.chatee.service.messaging.exception.NotLinkedUserException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Messaging com.bvan.chatee.service intended for manipulations with conversations (chat rooms).
 *
 * @author bvanchuhov
 */
public enum MessagingService {
    INSTANCE;

    /**
     * Synchronized map id -> conversation.
     */
    private Map<Integer, Conversation> idToConversationMap = Collections.synchronizedMap(new HashMap<Integer, Conversation>());

    /**
     * Create conversation. Method is tread safe.
     *
     * @return created conversation.
     */
    public Conversation createConversation() {
        Conversation conversation = new Conversation();

        idToConversationMap.put(conversation.getId(), conversation);

        return conversation;
    }

    /**
     * Return conversation by specified id.
     *
     * @param conversationId conversation id.
     * @return conversation by specified id.
     * @throws ConversationNotFoundException if conversation not found.
     */
    public Conversation getConversationById(int conversationId) throws ConversationNotFoundException {
        Conversation conversation = idToConversationMap.get(conversationId);
        if (conversation == null) {
            throw new ConversationNotFoundException(conversationId);
        }

        return conversation;
    }

    /**
     * Link participant to conversation.
     *
     * @param conversationId conversation id.
     * @param userId         participant id.
     * @throws ConversationNotFoundException if the conversation not found.
     */
    public void linkUserToConversation(int conversationId, int userId) throws ConversationNotFoundException {
        Conversation conversation = getConversationById(conversationId);
        conversation.linkUser(userId);
    }

    /**
     * Create message.
     *
     * @param conversationId conversation id.
     * @param senderId       sender id.
     * @param text           message text.
     * @return created message.
     * @throws ConversationNotFoundException if the conversation not found.
     * @throws NotLinkedUserException        if sender is not linked to the conversation.
     */
    public Message createMessage(int conversationId, int senderId, String text) throws MessagingException {
        Conversation conversation = getConversationById(conversationId);
        if (!conversation.isLinkedUser(senderId)) {
            throw new NotLinkedUserException(conversationId, senderId);
        }

        Message message = new Message(senderId, text)
                .setCreatingTime(System.currentTimeMillis());
        conversation.addMessage(message);

        return message;
    }
}
