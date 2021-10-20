package dev.dinh.servlets;

import dev.dinh.models.Employee;
import dev.dinh.services.AuthService;
import dev.dinh.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class AuthServlet extends HttpServlet{

    private static final Logger logger = LogManager.getLogger(AuthServlet.class);
    EmployeeService es = new EmployeeService();
    AuthService as = new AuthService();

    //verifies token belongs to valid manager
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.trace("authenticating token");

        String authToken = req.getHeader("Authorization");

        if(!as.validToken(authToken)){
            logger.error("improper token format");
            resp.sendError(400, "Improper token format, unable to fulfill request");
        }else if(!as.isEmployee(authToken)){
            logger.error("employee id not in system");
            resp.sendError(403,"Token does not belong to a current employee");
        }else if(!as.isManager(authToken)) {
            logger.error("token does not belong to a current manager");
            resp.sendError(403, "Invalid user role for current request");
        }else{
            logger.trace("passed authentication");
            resp.setStatus(200);
        }
    }

    //authenticates employee login
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        logger.trace("authenticating employee");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Employee e = as.login(username, password);
        if(e == null){
            logger.error("failed authentication");
            resp.sendError(401,"Unable to authenticate user");
        }
        else{
            logger.trace("passed authentication");
            resp.setStatus(200);
            String token = e.getEmployeeID()+":"+e.getRole();
            resp.setHeader("Authorization", token);
        }
    }



}
