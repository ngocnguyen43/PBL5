package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

public class Carriage {
    String id;
    String name;
    Integer totalSeats;
    String trainId;
    @JsonInclude( content= JsonInclude.Include.NON_NULL)
    List<Integer> availableSeats;
    @JsonInclude( content= JsonInclude.Include.NON_NULL)
    List<Integer> bookedSeats;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public List<Integer> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Integer> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public List<Integer> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<Integer> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }
}
