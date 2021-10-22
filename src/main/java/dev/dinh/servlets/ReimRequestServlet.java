package dev.dinh.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.dinh.models.ReimRequest;
import dev.dinh.models.enums.Category;
import dev.dinh.services.AuthService;
import dev.dinh.services.ReimRequestService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ReimRequestServlet extends HttpServlet {

    AuthService as = new AuthService();
    ReimRequestService rrs = new ReimRequestService();

    //gets list of reimrequest objects
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean managerReq = Boolean.parseBoolean(req.getHeader("managerReq"));
        String authToken = req.getHeader("Authorization");
        String status = req.getHeader("status");
        boolean isManager = false;
        int managerID;
        int employeeID;
        if (!as.validToken(authToken)) {
            resp.sendError(400, "Improper token format, unable to fulfill request");
            return;
        }
        if(!as.isEmployee(authToken)){
            resp.sendError(401,"Unable to authenticate user");
            return;
        }
        if(managerReq){
            if(!as.isManager(authToken)){
                resp.sendError(403,"Manager access only");
                return;
            }
            isManager = true;
        }
        if(isManager){
            managerID =  Integer.parseInt(authToken.split(":")[0]);
            employeeID = 0;
        } else {
            managerID = 0;
            employeeID = Integer.parseInt(authToken.split(":")[0]);
        }
            try (PrintWriter pw = resp.getWriter()) {
                List<ReimRequest> requests = rrs.getRequestList(status,employeeID,managerID);
                ObjectMapper om = new ObjectMapper();
                om.registerModule(new JavaTimeModule());
                String requestJson = om.writeValueAsString(requests);
                pw.write(requestJson);
            } catch (IOException e) {
                e.printStackTrace();
        }
    }

    //creates reimrequest object
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String authToken = req.getHeader("Authorization");
        if(!as.validToken(authToken)){
            resp.sendError(400, "Improper token format, unable to fulfill request");
        }else if(!as.isEmployee(authToken)){
            resp.sendError(403,"Unable to authenticate user");
        }else{
            double amount = Double.parseDouble(req.getHeader("Amount"));
            String category = req.getHeader("Category");
            int employeeID = Integer.parseInt(authToken.split(":")[0]);
            ReimRequest returnReq = rrs.createReq(amount, Category.valueOf(category), employeeID);
            resp.setStatus(201);
        }
    }
}
