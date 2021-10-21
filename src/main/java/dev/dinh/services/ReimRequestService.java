package dev.dinh.services;

import dev.dinh.data.ReimRequestData;
import dev.dinh.data.daos.ReimRequestDAO;
import dev.dinh.models.enums.Category;
import dev.dinh.models.enums.ReimReqStatus;
import dev.dinh.models.ReimRequest;
import dev.dinh.services.interfaces.ReimServiceReqInterface;

import java.util.List;


public class ReimRequestService implements ReimServiceReqInterface {

    ReimRequestDAO rrd = new ReimRequestData();

    /**
     * Forwards creation of Reimbursement Request to DAO
     * @param amount
     * @param category
     * @param reqEmployeeID
     * @return  ReimRequest object
     */
    public ReimRequest createReq(double amount, Category category, int reqEmployeeID){
        ReimRequest rr = new ReimRequest(amount,category,reqEmployeeID);
        ReimRequest returnReq = rrd.createRequest(rr);
        return returnReq;
    }

    /**
     * Retrieves list of ReimRequest Objects from DAO
     * @param filter "all", "pending" or "resolved"
     * @param employeeID 0 for all employees
     * @return list of ReimRequest Objects
     */
    public List<ReimRequest> getRequestList(String filter, int employeeID, int managerID){
        return rrd.getRequestList(filter, employeeID, managerID);
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

}//end class
