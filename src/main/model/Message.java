package main.model;

import java.util.Date;
import java.util.Objects;

public class Message {
    private String messageId;
    private String senderId;
    private String receiverId;
    private String messageContent;
    private Date messageDate;
    private String messageType;

    public Message(String messageId, String senderId, String receiverId, String messageContent, Date messageDate, String messageType) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageContent = messageContent;
        this.messageDate = messageDate;
        this.messageType = messageType;
    }

    public enum MessageType {
        CUSTOMER,
        EMPLOYEE
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, senderId, receiverId, messageContent, messageDate, messageType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(messageId, message.messageId) &&
                Objects.equals(senderId, message.senderId) &&
                Objects.equals(receiverId, message.receiverId) &&
                Objects.equals(messageContent, message.messageContent) &&
                Objects.equals(messageDate, message.messageDate) &&
                messageType == message.messageType;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId='" + messageId + '\'' +
                ", senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", messageDate=" + messageDate +
                ", messageType=" + messageType +
                '}';
    }
}

