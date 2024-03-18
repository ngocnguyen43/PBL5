package org.example.ticketbox.Model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Schedules {
    private String scheduleId;
    private String providerId;
    private String tripCode;
    private String departurePoint;
    private String arrivalPoint;
    private Date departureDatetime;
    private int estimatedTravelTime;
    private int seatCapacity;
    private BigDecimal seatPrice;
    private String notes;

    public Schedules(String scheduleId, String providerId, String tripCode, String departurePoint, String arrivalPoint, Date departureDatetime, int estimatedTravelTime, int seatCapacity, BigDecimal seatPrice, String notes) {
        this.scheduleId = scheduleId;
        this.providerId = providerId;
        this.tripCode = tripCode;
        this.departurePoint = departurePoint;
        this.arrivalPoint = arrivalPoint;
        this.departureDatetime = departureDatetime;
        this.estimatedTravelTime = estimatedTravelTime;
        this.seatCapacity = seatCapacity;
        this.seatPrice = seatPrice;
        this.notes = notes;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getTripCode() {
        return tripCode;
    }

    public void setTripCode(String tripCode) {
        this.tripCode = tripCode;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getArrivalPoint() {
        return arrivalPoint;
    }

    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }

    public Date getDepartureDatetime() {
        return departureDatetime;
    }

    public void setDepartureDatetime(Date departureDatetime) {
        this.departureDatetime = departureDatetime;
    }

    public int getEstimatedTravelTime() {
        return estimatedTravelTime;
    }

    public void setEstimatedTravelTime(int estimatedTravelTime) {
        this.estimatedTravelTime = estimatedTravelTime;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public BigDecimal getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(BigDecimal seatPrice) {
        this.seatPrice = seatPrice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheduleId, providerId, tripCode, departurePoint, arrivalPoint, departureDatetime, estimatedTravelTime, seatCapacity, seatPrice, notes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedules schedules = (Schedules) o;
        return estimatedTravelTime == schedules.estimatedTravelTime &&
                seatCapacity == schedules.seatCapacity &&
                Objects.equals(scheduleId, schedules.scheduleId) &&
                Objects.equals(providerId, schedules.providerId) &&
                Objects.equals(tripCode, schedules.tripCode) &&
                Objects.equals(departurePoint, schedules.departurePoint) &&
                Objects.equals(arrivalPoint, schedules.arrivalPoint) &&
                Objects.equals(departureDatetime, schedules.departureDatetime) &&
                Objects.equals(seatPrice, schedules.seatPrice) &&
                Objects.equals(notes, schedules.notes);
    }

    @Override
    public String toString() {
        return "Schedules{" +
                "scheduleId='" + scheduleId + '\'' +
                ", providerId='" + providerId + '\'' +
                ", tripCode='" + tripCode + '\'' +
                ", departurePoint='" + departurePoint + '\'' +
                ", arrivalPoint='" + arrivalPoint + '\'' +
                ", departureDatetime=" + departureDatetime +
                ", estimatedTravelTime=" + estimatedTravelTime +
                ", seatCapacity=" + seatCapacity +
                ", seatPrice=" + seatPrice +
                ", notes='" + notes + '\'' +
                '}';
    }
}
