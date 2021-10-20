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

    protected void doGet(HttpServletRequest request, HttpServletResponse response){}

    //authenticates employee login
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        logger.trace("doPost called - authenticating employee");
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
