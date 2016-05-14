package com.bvan.chatee.service.messaging;

import java.util.Date;
import java.util.Objects;

/**
 * The conversation's message.
 *
 * @author bvanchuhov
 */
public class Message {

    private static int nextId = 0;

    private final int id = nextId++;
    private int senderId;
    private String text;
    private long creationTime;

    public Message(int senderId, String text) {
        this.senderId = senderId;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public int getSenderId() {
        return senderId;
    }

    public Message setSenderId(int senderId) {
        this.senderId = senderId;
        return this;
    }

    public String getText() {
        return text;
    }

    public Message setText(String text) {
        this.text = text;
        return this;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public Message setCreationTime(long creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return senderId == message.senderId &&
                creationTime == message.creationTime &&
                Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderId, text, creationTime);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", text='" + text + '\'' +
                ", creationTime=" + new Date(creationTime) +
                '}';
    }
}
