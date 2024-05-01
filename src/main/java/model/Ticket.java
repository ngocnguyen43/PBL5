package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Ticket {
    private String ticketId;
    private String scheduleId;
    private int quantity;
    private BigDecimal price;
    private String status;
    private String photo;

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, scheduleId, quantity, price, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return quantity == ticket.quantity &&
                Objects.equals(ticketId, ticket.ticketId) &&
                Objects.equals(scheduleId, ticket.scheduleId) &&
                Objects.equals(price, ticket.price) &&
                Objects.equals(status, ticket.status);
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "ticketId='" + ticketId + '\'' +
                ", scheduleId='" + scheduleId + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}

