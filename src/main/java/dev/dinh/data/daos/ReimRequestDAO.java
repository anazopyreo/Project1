package dev.dinh.data.daos;

import dev.dinh.models.ReimRequest;

import java.util.List;

public interface ReimRequestDAO {

    void createRequest(ReimRequest rr);

    ReimRequest getRequest(int requestID);

    void updateRequest(ReimRequest rr);

    List<ReimRequest> getRequests(String filter, int employeeID);
}

