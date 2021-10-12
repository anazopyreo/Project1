package dev.dinh.servlets;

import dev.dinh.models.ReimRequest;
import dev.dinh.models.enums.Category;
import dev.dinh.services.AuthService;
import dev.dinh.services.ReimRequestService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReimRequestServlet extends HttpServlet {

    AuthService as = new AuthService();
    ReimRequestService rrs = new ReimRequestService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String authToken = req.getHeader("Authorization");
        System.out.println("Got here");
        if(!as.validToken(authToken)){
            resp.sendError(400, "Improper token format, unable to fulfill request");
        }
        if(!as.isEmployee(authToken)){
            resp.sendError(403,"Unable to authenticate user");
        }else{
            double amount = Double.parseDouble(req.getHeader("Amount"));
            System.out.println(amount);
            String category = req.getHeader("Category");
            System.out.println(category);
            int employeeID = Integer.parseInt(authToken.split(":")[0]);
            System.out.println(employeeID);
            resp.setStatus(201);
            rrs.createReq(amount, Category.valueOf(category), employeeID);
        }
    }
}
