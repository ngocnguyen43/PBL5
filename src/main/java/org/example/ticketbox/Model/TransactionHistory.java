package org.example.ticketbox.Model;

import java.util.Date;
import java.util.Objects;

public class TransactionHistory {
    private String transactionId;
    private String userId;
    private String ticketId;
    private Date transactionDate;
    private int quantitySold;

    public TransactionHistory(String transactionId, String userId, String ticketId, Date transactionDate, int quantitySold) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.ticketId = ticketId;
        this.transactionDate = transactionDate;
        this.quantitySold = quantitySold;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, userId, ticketId, transactionDate, quantitySold);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionHistory that = (TransactionHistory) o;
        return quantitySold == that.quantitySold &&
                Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(ticketId, that.ticketId) &&
                Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public String toString() {
        return "TransactionHistory{" +
                "transactionId='" + transactionId + '\'' +
                ", userId='" + userId + '\'' +
                ", ticketId='" + ticketId + '\'' +
                ", transactionDate=" + transactionDate +
                ", quantitySold=" + quantitySold +
                '}';
    }
}

