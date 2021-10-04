package dev.dinh.models;

import dev.dinh.models.enums.Category;
import dev.dinh.models.enums.ReimReqStatus;

import java.time.LocalDateTime;

import static dev.dinh.models.enums.ReimReqStatus.*;

public class ReimRequest {
    double amount;
    Category category;
    ReimReqStatus status;
    LocalDateTime subDate; //submission date
    LocalDateTime decDate; //decision date
    int reqEmployeeID; //requesting employee ID
    int decManagerID; //deciding manager ID

    public double getAmount() {
        return amount;
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

    public ReimReqStatus getApproval() {
        return status;
    }

    public void setApproval(ReimReqStatus status) {
        this.status = status;
    }

    public LocalDateTime getSubDate() {
        return subDate;
    }

    public void setSubDate(LocalDateTime subDate) {
        this.subDate = subDate;
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
}
