package model;

import java.util.Objects;

public class Customer {
    private String customerId;
    private String userId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String position;
    private String photo;

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", userId='" + userId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", position='" + position + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public Customer(String customerId, String userId, String fullName, String email, String phoneNumber, String position, String photo) {
        this.customerId = customerId;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.photo = photo;
    }

    public Customer() {
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
        return Objects.hash(customerId, userId, fullName, email, phoneNumber, position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) &&
                Objects.equals(userId, customer.userId) &&
                Objects.equals(fullName, customer.fullName) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(phoneNumber, customer.phoneNumber) &&
                Objects.equals(position, customer.position);
    }

}
