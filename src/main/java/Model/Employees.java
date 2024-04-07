package org.example.ticketbox.Model;

import java.util.Date;
import java.util.Objects;

public class Employees {
    private String employeeId;
    private String userId;
    private String fullName;
    private String gender;
    private String email;
    private String phoneNumber;
    private String position;
    private Date dateOfBirth;
    private String photo;

    public Employees(String employeeId, String userId, String fullName, String gender, String email, String phoneNumber, String position, Date dateOfBirth, String photo) {
        this.employeeId = employeeId;
        this.userId = userId;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.dateOfBirth = dateOfBirth;
        this.photo = photo;
    }

    public Employees() {
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, userId, fullName, email, phoneNumber, position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employees employees = (Employees) o;
        return Objects.equals(employeeId, employees.employeeId) &&
                Objects.equals(userId, employees.userId) &&
                Objects.equals(fullName, employees.fullName) &&
                Objects.equals(email, employees.email) &&
                Objects.equals(phoneNumber, employees.phoneNumber) &&
                Objects.equals(position, employees.position);
    }

    @Override
    public String toString() {
        return "Employees{" +
                "employeeId='" + employeeId + '\'' +
                ", userId='" + userId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

