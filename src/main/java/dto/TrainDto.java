package dto;

public class TrainDto {
    private String name;
    private Integer totalCarriage;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
