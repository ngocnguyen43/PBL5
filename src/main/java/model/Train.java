package model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Train {
    String id;
    String name;
    Integer totalCarriage;
    String status;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public Integer getTotalCarriage() {
        return totalCarriage;
    }

    public void setTotalCarriage(Integer totalCarriage) {
        this.totalCarriage = totalCarriage;
    }
}
