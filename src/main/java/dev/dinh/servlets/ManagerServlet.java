package dev.dinh.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.dinh.models.Employee;
import dev.dinh.services.AuthService;
import dev.dinh.services.EmployeeService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ManagerServlet extends HttpServlet {
    AuthService as = new AuthService();
    EmployeeService es = new EmployeeService();

    /**
     * Prepares list of employees to be displayed in an HTML document
     * @param req
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String authToken = req.getHeader("Authorization");
        if(!as.validToken(authToken)){
            resp.sendError(400, "Improper token format, unable to fulfill request");
        }else if(!as.isManager(authToken)){
            resp.sendError(403, "Invalid user role for current request");
        }else{
            try(PrintWriter pw = resp.getWriter();){
                List<Employee> employees = es.getAllEmployees();
                ObjectMapper om = new ObjectMapper();
                String employeeJson = om.writeValueAsString(employees);
                pw.write(employeeJson);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String authToken = req.getHeader("Authorization");
        if (!as.validToken(authToken)) {
            resp.sendError(400, "Improper token format, unable to fulfill request");
        } else if (!as.isManager(authToken)) {
            resp.sendError(403, "Invalid user role for current request");
        } else {
            String fname = req.getHeader("fname");
            String mname = req.getHeader("mname");
            String lname = req.getHeader("lname");
            String pemail = req.getHeader("pemail");
            es.createEmployee(fname,mname,lname,pemail);
            if(fname == null || fname.equals("") || lname==null || lname.equals("") || pemail == null || pemail.equals("")){
                resp.sendError(400,"First Name, Last Name and Personal Email required");
            }else{resp.setStatus(201);}
        }
    }
}
