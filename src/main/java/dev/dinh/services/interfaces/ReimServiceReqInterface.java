package dev.dinh.services.interfaces;

import dev.dinh.models.ReimRequest;
import dev.dinh.models.enums.Category;

import java.util.List;

public interface ReimServiceReqInterface {
    public ReimRequest createReq(double amount, Category category, int reqEmployeeID);

    public List<ReimRequest> getRequestList(String filter, int employeeID);


}
