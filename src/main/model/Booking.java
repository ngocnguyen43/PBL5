package main.model;

import java.util.Date;
import java.util.Objects;

public class Booking {
    private String bookingId;
    private String userId;
    private String ticketId;
    private Date bookingDate;
    private String status;

    public Booking(String bookingId, String userId, String ticketId, Date bookingDate, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.ticketId = ticketId;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
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

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, userId, ticketId, bookingDate, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(bookingId, booking.bookingId) &&
                Objects.equals(userId, booking.userId) &&
                Objects.equals(ticketId, booking.ticketId) &&
                Objects.equals(bookingDate, booking.bookingDate) &&
                Objects.equals(status, booking.status);
    }

    @Override
    public String toString() {
        return "Bookings{" +
                "bookingId='" + bookingId + '\'' +
                ", userId='" + userId + '\'' +
                ", ticketId='" + ticketId + '\'' +
                ", bookingDate=" + bookingDate +
                ", status='" + status + '\'' +
                '}';
    }
}

