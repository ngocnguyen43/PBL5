package dto;

import dto.abstracts.AbstractUserDto;

public class EmployeeDto extends AbstractUserDto {
    private String fullName;
    private String gender;
    private String email;
    private String phoneNumber;
    private String position;
    private String photo;
    private String employeeId;

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "fullName='" + fullName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", position='" + position + '\'' +
                ", photo='" + photo + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
