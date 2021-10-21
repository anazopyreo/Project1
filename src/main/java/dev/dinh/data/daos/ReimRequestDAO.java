package dev.dinh.data.daos;

import dev.dinh.models.ReimRequest;

import java.util.List;

public interface ReimRequestDAO {

    ReimRequest createRequest(ReimRequest rr);

    List<ReimRequest> getRequestList(String filter, int employeeID, int managerID);

    List<ReimRequest> getRequestListWithNames(String filter, int employeeID, String role);

    void updateRequest(ReimRequest rr);



    ReimRequest getRequest(int requestID);

}

