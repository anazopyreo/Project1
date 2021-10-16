package dev.dinh.servlets;

import dev.dinh.services.AuthService;
import dev.dinh.services.ReimRequestService;
import dev.dinh.models.enums.ReimReqStatus;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReimReqManagerServlet extends HttpServlet {

    AuthService as = new AuthService();
    ReimRequestService rrs = new ReimRequestService();

    //resolves reimrequest object
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String authToken = req.getHeader("Authorization");
        if(!as.validToken(authToken)){
            resp.sendError(400, "Improper token format, unable to fulfill request");
        }else if(!as.isEmployee(authToken)){
            resp.sendError(403,"Unable to authenticate user");
        }else{
            int requestID = Integer.parseInt(req.getHeader("requestID"));
            int managerID = Integer.parseInt(authToken.split(":")[0]);
            String statusString = req.getHeader("status").toUpperCase();
            ReimReqStatus status = ReimReqStatus.valueOf(statusString);
            rrs.setStatus(requestID,managerID,status);
        }
    }
}
