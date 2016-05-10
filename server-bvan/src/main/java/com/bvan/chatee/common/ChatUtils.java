package com.bvan.chatee.common;

/**
 * Created by Maryana on 10.05.2016.
 */
public class ChatUtils {
    private static final String PARAM_CONVERSATION_ID = "conversationId";
    private static final String PARAM_SENDER_ID = "userId";
    private static final String PARAM_SENDER_USERNAME = "username";
    private static final String PARAM_SENDER_PASSWORD = "password";

    public static String getParamSenderId() {
        return PARAM_SENDER_ID;
    }

    public static String getParamConversationId() {
        return PARAM_CONVERSATION_ID;
    }

    public static String getParamSenderUsername() {
        return PARAM_SENDER_USERNAME;
    }

    public static String getParamSenderPassword() {
        return PARAM_SENDER_PASSWORD;
    }
}
