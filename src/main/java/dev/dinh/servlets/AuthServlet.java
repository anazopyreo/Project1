package dev.dinh.servlets;

import dev.dinh.Application;
import dev.dinh.models.Employee;
import dev.dinh.services.AuthService;
import dev.dinh.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet{

    EmployeeService es = new EmployeeService();
    AuthService as = new AuthService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response){

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Employee e = as.login(username, password);
        if(e == null){
            resp.sendError(401,"Unable to authenticate user");
        }
        else{
            resp.setStatus(200);
            String token = e.getEmployeeID()+":"+e.getRole();
            resp.setHeader("Authorization", token);
        }
    }



}
