package dev.dinh.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.dinh.models.Employee;
import dev.dinh.models.ReimRequest;
import dev.dinh.services.AuthService;
import dev.dinh.services.EmployeeService;
import dev.dinh.services.ReimRequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class EmployeeServlet extends HttpServlet {

    AuthService as = new AuthService();
    EmployeeService es = new EmployeeService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String authToken = req.getHeader("Authorization");
        if(!as.validToken(authToken)){
            resp.sendError(400, "Improper token format, unable to fulfill request");
        }else{
            try(PrintWriter pw = resp.getWriter();){
                Employee e = es.getEmployeeByID(Integer.parseInt(authToken.split(":")[0]));
                ObjectMapper om = new ObjectMapper();
                String employeeJson = om.writeValueAsString(e);
                pw.write(employeeJson);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

}

