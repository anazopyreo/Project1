package dev.dinh.services;

import dev.dinh.models.enums.Category;
import dev.dinh.models.enums.ReimReqStatus;
import dev.dinh.models.ReimRequest;

import static dev.dinh.models.enums.ReimReqStatus.*;

public class ReimRequestService {

    public ReimRequest createReq(double amount, Category category, int reqEmployeeID){
        //send to ReimRequestData
        return new ReimRequest(amount, category, reqEmployeeID);
    }

    public void denyReq(ReimRequest request, int managerID){
        request.setApproval(DENIED);
        request.setDecManagerID(managerID);
        //send to ReimRequestData

    }

    public void approveReq(ReimRequest request, int managerID){
        request.setApproval(APPROVED);
        request.setDecManagerID(managerID);
        //send to ReimRequestData
    }
}
