package dev.dinh.data.daos;

import dev.dinh.models.ReimRequest;

import java.util.List;

public interface ReimRequestDAO {

    void createRequest(ReimRequest rr);

    List<ReimRequest> getRequestList(String filter, int employeeID);

    void updateRequest(ReimRequest rr);



    ReimRequest getRequest(int requestID);

}

