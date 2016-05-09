package service.messaging;

import java.util.*;

/**
 * Conversation or Char Room. Includes many participants and one stream of messages.
 *
 * @author bvanchuhov
 */
public class Conversation {

    private static final int nextId = 0;

    private final int id = nextId;

    /**
     * Synchronized set of linked user ids.
     */
    private final Set<Integer> linkedUserIds = Collections.synchronizedSet(new HashSet<Integer>());

    /**
     * Synchronized list of messages.
     */
    private final List<Message> messages = Collections.synchronizedList(new LinkedList<Message>());

    public int getId() {
        return id;
    }

    public void linkUser(int userId) {
        linkedUserIds.add(userId);
    }

    public Set<Integer> getLinkedUserIds() {
        return linkedUserIds;
    }

    public void addMessage(Message message) {
        checkIsUserLinked(message.getSenderId());

        messages.add(message);
    }

    private void checkIsUserLinked(int senderId) {
        if (!isLinkedUser(senderId)) {
            throw new IllegalArgumentException("user " + senderId + " is not linked to the conversation");
        }
    }

    public boolean isLinkedUser(int userId) {
        return linkedUserIds.contains(userId);
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversation that = (Conversation) o;
        return Objects.equals(linkedUserIds, that.linkedUserIds) &&
                Objects.equals(messages, that.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkedUserIds, messages);
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", linkedUserIds=" + linkedUserIds +
                ", messages=" + messages +
                '}';
    }
}
