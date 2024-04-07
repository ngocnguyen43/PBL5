package org.example.ticketbox.Model;

import java.util.Date;
import java.util.Objects;

public class ScheduleRequest {
    private String requestId;
    private String providerId;
    private Date submissionDate;
    private String status;
    private String requestDetails;

    public ScheduleRequest(String requestId, String providerId, Date submissionDate, String status, String requestDetails) {
        this.requestId = requestId;
        this.providerId = providerId;
        this.submissionDate = submissionDate;
        this.status = status;
        this.requestDetails = requestDetails;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestDetails() {
        return requestDetails;
    }

    public void setRequestDetails(String requestDetails) {
        this.requestDetails = requestDetails;
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, providerId, submissionDate, status, requestDetails);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleRequest that = (ScheduleRequest) o;
        return Objects.equals(requestId, that.requestId) &&
                Objects.equals(providerId, that.providerId) &&
                Objects.equals(submissionDate, that.submissionDate) &&
                Objects.equals(status, that.status) &&
                Objects.equals(requestDetails, that.requestDetails);
    }

    @Override
    public String toString() {
        return "ScheduleRequest{" +
                "requestId='" + requestId + '\'' +
                ", providerId='" + providerId + '\'' +
                ", submissionDate=" + submissionDate +
                ", status='" + status + '\'' +
                ", requestDetails='" + requestDetails + '\'' +
                '}';
    }
}
