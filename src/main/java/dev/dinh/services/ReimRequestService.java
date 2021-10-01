package dev.dinh.services;

import dev.dinh.models.Category;
import dev.dinh.models.ReimReqStatus;
import dev.dinh.models.ReimRequest;

public class ReimRequestService {

    public ReimRequest createRequest(double amount, Category category, int reqEmployeeID){
        //send to ReimRequestData
        return new ReimRequest(amount, category, reqEmployeeID);
    }

    public void updateStatus(ReimRequest request, ReimReqStatus status, int managerID){
        request.setApproval(status);
        request.setDecManagerID(managerID);
        //send to ReimRequestData
    }
}
