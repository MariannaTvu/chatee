package service.messaging.exception;

/**
 * @author bvanchuhov
 */
public class ConversationNotFoundException extends MessagingException {

    public ConversationNotFoundException(int conversationId) {
        super("conversation " + conversationId + " not found");
    }
}
