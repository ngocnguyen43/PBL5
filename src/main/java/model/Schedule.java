package model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class Schedule {
    private final long unix = System.currentTimeMillis() / 1000L;
    private String scheduleId;
    private String userId;
    private String tripCode;
    private String departurePoint;
    private String arrivalPoint;
    private String arrivalAt;
    private String startAt;
    private float estimatedTravelTime;
    private String notes;
    private String photo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createdAt = unix + "";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String updatedAt = unix + "";
    private String status;
    private String trainId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Train train;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Carriage> carriages;


    public List<Carriage> getCarriages() {
        return carriages;
    }

    public void setCarriages(List<Carriage> carriages) {
        this.carriages = carriages;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getArrivalAt() {
        return arrivalAt;
    }

    public void setArrivalAt(String arrivalAt) {
        this.arrivalAt = arrivalAt;
    }

    public float getEstimatedTravelTime() {
        return estimatedTravelTime;
    }

    public void setEstimatedTravelTime(float estimatedTravelTime) {
        this.estimatedTravelTime = estimatedTravelTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }
}
