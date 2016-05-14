package com.bvan.chatee.common;

/**
 * Constants for chat project.
 *
 * @author bvanchuhov
 */
public final class ChatConstants {
    private ChatConstants() {
    }

    /**
     * HTTP parameter constants.
     */
    public static class Params {
        public static final String PARAM_CONVERSATION_ID = "convId";
        public static final String PARAM_MESSAGE_ID = "msgId";
        public static final String PARAM_USER_ID = "userId";
        public static final String PARAM_MESSAGE_TEXT = "text";
        public static final String PARAM_SENDER_USERNAME = "username";
        public static final String PARAM_SENDER_PASSWORD = "password";
        public static final String PARAM_RESPONSE_TYPE = "respType";
        private Params() {
        }
    }
}
