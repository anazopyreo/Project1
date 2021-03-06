package dev.dinh.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.dinh.models.enums.Category;
import dev.dinh.models.enums.ReimReqStatus;

import java.time.LocalDateTime;
import java.util.Objects;

import static dev.dinh.models.enums.ReimReqStatus.*;

public class ReimRequest {
    int requestID;

    double amount;
    Category category;
    ReimReqStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime reqDate; //request submission date

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime decDate; //decision date

    int reqEmployeeID; //requesting employee ID
    int decManagerID; //deciding manager ID

    public double getAmount() {
        return amount;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ReimReqStatus getStatus() {
        return status;
    }

    public void setStatus(ReimReqStatus status) {
        this.status = status;
    }

    public LocalDateTime getReqDate() {
        return reqDate;
    }

    public void setReqDate(LocalDateTime subDate) {
        this.reqDate = subDate;
    }

    public LocalDateTime getDecDate() {
        return decDate;
    }

    public void setDecDate(LocalDateTime decDate) {
        this.decDate = decDate;
    }

    public int getReqEmployeeID() {
        return reqEmployeeID;
    }

    public void setReqEmployeeID(int reqEmployeeID) {
        this.reqEmployeeID = reqEmployeeID;
    }

    public int getDecManagerID() {
        return decManagerID;
    }

    public void setDecManagerID(int decManagerID) {
        this.decManagerID = decManagerID;
    }

    public ReimRequest() {
    }

    public ReimRequest(double amount, Category category, int reqEmployeeID) {
        this.amount = amount;
        this.category = category;
        this.reqEmployeeID = reqEmployeeID;
        this.status = PENDING;
    }

    @Override
    public String toString() {
        return "ReimRequest{" +
                "requestID=" + requestID +
                ", amount=" + amount +
                ", category=" + category +
                ", status=" + status +
                ", reqDate=" + reqDate +
                ", decDate=" + decDate +
                ", reqEmployeeID=" + reqEmployeeID +
                ", decManagerID=" + decManagerID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReimRequest)) return false;
        ReimRequest that = (ReimRequest) o;
        return getRequestID() == that.getRequestID() && Double.compare(that.getAmount(), getAmount()) == 0 && getReqEmployeeID() == that.getReqEmployeeID() && getDecManagerID() == that.getDecManagerID() && getCategory() == that.getCategory() && getStatus() == that.getStatus() && getReqDate().equals(that.getReqDate()) && Objects.equals(getDecDate(), that.getDecDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequestID(), getAmount(), getCategory(), getStatus(), getReqDate(), getDecDate(), getReqEmployeeID(), getDecManagerID());
    }
}
