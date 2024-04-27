package model;

import java.util.Objects;

public class Provider {
    private String providersId;
    private String userId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String position;
    private String providerName;
    private String contactInfo;
    private int isConfirmed = 0;
    private String photo;

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public Provider() {
    }

    public int getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(int isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public Provider(String providersId, String userId, String fullName, String email, String phoneNumber, String position, String providerName, String contactInfo, int isConfirmed, String photo) {
        this.providersId = providersId;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.providerName = providerName;
        this.contactInfo = contactInfo;
        this.isConfirmed = isConfirmed;
        this.photo = photo;
    }

    public String getProvidersId() {
        return providersId;
    }

    public void setProvidersId(String providersId) {
        this.providersId = providersId;
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

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public int isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(int confirmed) {
        isConfirmed = confirmed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(providersId, userId, fullName, email, phoneNumber, position, providerName, contactInfo, isConfirmed);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider that = (Provider) o;
        return isConfirmed == that.isConfirmed &&
                Objects.equals(providersId, that.providersId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(position, that.position) &&
                Objects.equals(providerName, that.providerName) &&
                Objects.equals(contactInfo, that.contactInfo);
    }

    @Override
    public String toString() {
        return "Provider{" +
                "providersId='" + providersId + '\'' +
                ", userId='" + userId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", position='" + position + '\'' +
                ", providerName='" + providerName + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", isConfirmed=" + isConfirmed +
                '}';
    }
}

