package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Tickets {
    private String ticketId;
    private String scheduleId;
    private int quantity;
    private BigDecimal price;
    private String status;
    private String photo;

    public Tickets(String ticketId, String scheduleId, int quantity, BigDecimal price, String status, String photo) {
        this.ticketId = ticketId;
        this.scheduleId = scheduleId;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.photo = photo;
    }

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
        Tickets tickets = (Tickets) o;
        return quantity == tickets.quantity &&
                Objects.equals(ticketId, tickets.ticketId) &&
                Objects.equals(scheduleId, tickets.scheduleId) &&
                Objects.equals(price, tickets.price) &&
                Objects.equals(status, tickets.status);
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

