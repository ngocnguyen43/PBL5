package main.model;

import java.util.Objects;

public class SeatTickets {
    private String seatId;
    private String ticketId;

    public SeatTickets(String seatId, String ticketId) {
        this.seatId = seatId;
        this.ticketId = ticketId;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatId, ticketId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatTickets that = (SeatTickets) o;
        return Objects.equals(seatId, that.seatId) &&
                Objects.equals(ticketId, that.ticketId);
    }

    @Override
    public String toString() {
        return "SeatTickets{" +
                "seatId='" + seatId + '\'' +
                ", ticketId='" + ticketId + '\'' +
                '}';
    }
}
