package main.model;

import java.math.BigDecimal;
import java.util.Objects;

public class SeatPositions {
    private String seatId;
    private String scheduleId;
    private int seatNumber;
    private BigDecimal price;
    private String status;

    public SeatPositions(String seatId, String scheduleId, int seatNumber, BigDecimal price, String status) {
        this.seatId = seatId;
        this.scheduleId = scheduleId;
        this.seatNumber = seatNumber;
        this.price = price;
        this.status = status;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
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
        return Objects.hash(seatId, scheduleId, seatNumber, price, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatPositions that = (SeatPositions) o;
        return seatNumber == that.seatNumber &&
                Objects.equals(seatId, that.seatId) &&
                Objects.equals(scheduleId, that.scheduleId) &&
                Objects.equals(price, that.price) &&
                Objects.equals(status, that.status);
    }

    @Override
    public String toString() {
        return "SeatPositions{" +
                "seatId='" + seatId + '\'' +
                ", scheduleId='" + scheduleId + '\'' +
                ", seatNumber=" + seatNumber +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
