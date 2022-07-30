package edu.school21.sockets.models;

import java.time.LocalDateTime;

public class Message {
    private Long identifier = null;
    private User sender;
    private String message;
    private LocalDateTime sendingTime;

    public Message(Long identifier, User sender, String message) {
        this.identifier = identifier;
        this.sender = sender;
        this.message = message;
        this.sendingTime = LocalDateTime.now();
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(LocalDateTime sendingTime) {
        this.sendingTime = sendingTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "identifier=" + identifier +
                ", sender=" + sender +
                ", message='" + message + '\'' +
                ", sendingTime=" + sendingTime +
                '}';
    }
}
