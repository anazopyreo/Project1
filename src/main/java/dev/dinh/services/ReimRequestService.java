package dev.dinh.services;

import dev.dinh.data.ReimRequestData;
import dev.dinh.models.enums.Category;
import dev.dinh.models.enums.ReimReqStatus;
import dev.dinh.models.ReimRequest;
import jdk.nashorn.internal.ir.RuntimeNode;

import java.util.List;

import static dev.dinh.models.enums.ReimReqStatus.*;

public class ReimRequestService {

    ReimRequestData rrd = new ReimRequestData();

    public ReimRequest createReq(double amount, Category category, int reqEmployeeID){
        ReimRequest rr = new ReimRequest(amount,category,reqEmployeeID);
        rrd.createRequest(rr);
        return rr;
    }

    public ReimRequest getRequest(int requestID){
        return rrd.getRequest(requestID);
    }

    public void setStatus(int requestID, int managerID, ReimReqStatus status){
        ReimRequest rr = getRequest(requestID);
        rr.setStatus(status);
        rr.setDecManagerID(managerID);
        rrd.updateRequest(rr);
    }

    public List<ReimRequest> getRequests(String filter, int employeeID){
        return rrd.getRequests(filter, employeeID);
    }

}//end class
