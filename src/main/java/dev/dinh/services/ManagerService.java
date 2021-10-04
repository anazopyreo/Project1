package dev.dinh.services;

import dev.dinh.data.EmployeeData;
import dev.dinh.models.Employee;

import java.util.List;

import static dev.dinh.models.enums.Role.ASSOCIATE;
import static dev.dinh.models.enums.Role.MANAGER;

public class ManagerService extends EmployeeService{

    EmployeeData employeeData = new EmployeeData();

    public void viewEmployees(){

    }

    public void viewResolvedRequest(){

    }

    public void viewPendingRequests(){

    }

    public void resolveRequest(){

    }

    /**
     * sets employee status as manager
     * @param employeeID
     */
    public void promoteEmployee(int employeeID){
        employeeData.setStatus(employeeID, MANAGER);
    }

    /**
     * sets employee status as associate
     * @param employeeID
     */
    public void demoteEmployee(int employeeID){
        employeeData.setStatus(employeeID, ASSOCIATE);
    }

    /**
     * Creates a new employee object and submits it to the database
     * @param fname employee's first name
     * @param mname employee's middle name
     * @param lname employee's last name
     * @param pemail employee's personal email address
     * @return new employee object
     */
    public Employee createEmployee(String fname, String mname, String lname, String pemail){
        String uname = createUname(fname, lname);
        Employee e = new Employee(fname, mname, lname, pemail, uname);
        employeeData.createEmployee(e);
        return e;
    }

    /**
     * Creates a unique username based on a provided first name and last name
     * @param fname a first name
     * @param lname a last name
     * @return a unique username based on the provided first name and last name
     */
    private String createUname(String fname, String lname){
        String uname = fname.toLowerCase() + "." + lname.toLowerCase();
        List<String> similarUnames = employeeData.getUnames(uname);
        if(similarUnames.size() > 0){
            int max = 1;
            for(String u: similarUnames){
                int suffix = findInt(u)+1;
                if(suffix > max){max = suffix;}
            }//end for
            uname = uname + max;
        }//end if
        return uname;
    }

    /**
     * Creates a unique username based on a given proposed username
     * @param uname proposed username
     * @return the next available username composed of the proposed username and an integer if needed
     */
    private int findInt(String uname){
        int output = 0;
        int i = uname.length();
        if(Character.isDigit(uname.charAt(i - 1))){
            while (i > 0 && Character.isDigit(uname.charAt(i - 1))) {
                i--;
            }
            output = Integer.parseInt(uname.substring(i));
        }
        return output;
    }
}
