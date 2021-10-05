package dev.dinh.data;

import dev.dinh.data.daos.EmployeeDAO;
import dev.dinh.models.Employee;
import dev.dinh.models.enums.Role;
import dev.dinh.services.ConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dev.dinh.models.enums.Role.*;

public class EmployeeData implements EmployeeDAO {

    private static ConnectionService connectionService = new ConnectionService();

    /**
     * returns a list of usernames that begin with the provided uname
     *
     * @param uname the username to be checked
     * @return a String Array of usernames beginning with uname
     */
    public List<String> getUnames(String uname) {
        String sql = "select username from employee where username like ?";
        List<String> unames = new ArrayList<String>();
        try (Connection c = connectionService.establishConnection();
             PreparedStatement pstmt = c.prepareStatement(sql);) {
            pstmt.setString(1, uname + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                unames.add(rs.getString("username"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return unames;
    }

    /**
     * Verifies that an employee with the provided ID is a manager
     * @param employeeID
     * @return true if the employee is a manager
     */
    public boolean isManager(int employeeID){
        String sql = "select employee_role from employee where employee_id = ?";
        Role r = null;
        try(Connection c = connectionService.establishConnection();
            PreparedStatement pstmt = c.prepareStatement(sql)){
            pstmt.setInt(1,employeeID);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                r = Role.valueOf(rs.getString("employee_role"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return r == MANAGER;
    }

    @Override
    public void createEmployee(Employee e) {
        String sql = "insert into employee (username, passwd, fname, mname, lname, pemail, hire_date, employee_role)" +
                "values (?,?,?,?,?,?,now(),?) returning employee_id, hire_date";
        try (Connection c = connectionService.establishConnection();
             PreparedStatement pstmt = c.prepareStatement(sql);) {
            pstmt.setString(1,e.getUname());
            pstmt.setString(2,e.getPassword());
            pstmt.setString(3,e.getFname());
            pstmt.setString(4, e.getMname());
            pstmt.setString(5,e.getLname());
            pstmt.setString(6,e.getPemail());
            pstmt.setString(7,e.getRole().toString());
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            e.setEmployeeID(rs.getInt("employee_id"));
            e.setHireDate(rs.getDate("hire_date").toLocalDate());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }//end createEmployee

    @Override
    public Employee getEmployeeByID(int employeeID) {
        String sql = "select * from employee where employee_id = ?";
        Employee e = new Employee();
        try (Connection c = connectionService.establishConnection();
             PreparedStatement pstmt = c.prepareStatement(sql);) {
        pstmt.setInt(1,employeeID);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next() == true) {
            e.setEmployeeID(rs.getInt("employee_id"));
            e.setUname(rs.getString("username"));
            e.setPassword(rs.getString("passwd"));
            e.setFname(rs.getString("fname"));
            e.setMname(rs.getString("mname"));
            e.setLname(rs.getString("lname"));
            e.setPemail(rs.getString("pemail"));
            e.setPphone(rs.getString("pphone"));
            e.setWphone(rs.getString("wphone"));
            e.setHireDate(rs.getDate("hire_date").toLocalDate());
            e.setRole(Role.valueOf(rs.getString("employee_role")));
            e.setWemail(rs.getString("username") + "@revicher.not");
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return e;
    }

    @Override
    public Employee getEmployeeByUname(String uname) {
        String sql = "select * from employee where username = ?";
        Employee e = new Employee();
        try (Connection c = connectionService.establishConnection();
             PreparedStatement pstmt = c.prepareStatement(sql);) {
            pstmt.setString(1,uname);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next() == true) {
                e.setEmployeeID(rs.getInt("employee_id"));
                e.setUname(rs.getString("username"));
                e.setPassword(rs.getString("passwd"));
                e.setFname(rs.getString("fname"));
                e.setMname(rs.getString("mname"));
                e.setLname(rs.getString("lname"));
                e.setPemail(rs.getString("pemail"));
                e.setPphone(rs.getString("pphone"));
                e.setWphone(rs.getString("wphone"));
                e.setHireDate(rs.getDate("hire_date").toLocalDate());
                e.setRole(Role.valueOf(rs.getString("employee_role")));
                e.setWemail(rs.getString("username") + "@revicher.not");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return e;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "select * from employee";
        try (Connection c = connectionService.establishConnection();
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {
            while(rs.next()){
                Employee e = new Employee();
                e.setEmployeeID(rs.getInt("employee_id"));
                e.setUname(rs.getString("username"));
//                e.setPassword(rs.getString("passwd"));
                e.setFname(rs.getString("fname"));
                e.setMname(rs.getString("mname"));
                e.setLname(rs.getString("lname"));
                e.setPemail(rs.getString("pemail"));
                e.setPphone(rs.getString("pphone"));
                e.setWphone(rs.getString("wphone"));
                e.setHireDate(rs.getDate("hire_date").toLocalDate());
                e.setRole(Role.valueOf(rs.getString("employee_role")));
                e.setWemail(rs.getString("username") + "@revicher.not");
                employees.add(e);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return employees;
    }

    /**
     * sets employee role in the database
     * @param employeeID
     * @param role
     */
    public void setRole(int employeeID, Role role) {
        String sql = "update employee set employee_role = ? where employee_id = ?";
        try(Connection c = connectionService.establishConnection();
            PreparedStatement pstmt = c.prepareStatement(sql);){
            pstmt.setString(1, String.valueOf(role));
            pstmt.setInt(2,employeeID);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}//end class