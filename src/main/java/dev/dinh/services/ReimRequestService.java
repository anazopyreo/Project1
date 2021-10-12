package dev.dinh.services;

import dev.dinh.data.ReimRequestData;
import dev.dinh.models.enums.Category;
import dev.dinh.models.enums.ReimReqStatus;
import dev.dinh.models.ReimRequest;
import dev.dinh.services.interfaces.ReimReqInterface;

import java.util.List;


public class ReimRequestService implements ReimReqInterface {

    ReimRequestData rrd = new ReimRequestData();

    /**
     * Creates a reimbursement request
     * @param amount
     * @param category
     * @param reqEmployeeID
     * @return  ReimRequest object
     */
    public ReimRequest createReq(double amount, Category category, int reqEmployeeID){
        ReimRequest rr = new ReimRequest(amount,category,reqEmployeeID);
        rrd.createRequest(rr);
        return rr;
    }


    public ReimRequest getRequestById(int requestID){
        return rrd.getRequest(requestID);
    }

    public void setStatus(int requestID, int managerID, ReimReqStatus status){
        ReimRequest rr = getRequestById(requestID);
        rr.setStatus(status);
        rr.setDecManagerID(managerID);
        rrd.updateRequest(rr);
    }

    public List<ReimRequest> getRequests(String filter, int employeeID){
        return rrd.getRequests(filter, employeeID);
    }

}//end class
