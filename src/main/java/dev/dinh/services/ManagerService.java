package dev.dinh.services;

import dev.dinh.data.EmployeeData;
import dev.dinh.models.Employee;

import java.util.List;

public class ManagerService extends EmployeeService{


    public void viewEmployees(){

    }

    public void viewResolvedRequest(){

    }

    public void viewPendingRequests(){

    }

    public void resolveRequest(){

    }

    public void promoteEmployee(){

    }

    public Employee createEmployee(String fname, String mname, String lname, String pemail){
        String uname = createUname(fname, lname);
        //send to EmployeeData
        return new Employee(fname, mname, lname, pemail, uname);
    }

    private String createUname(String fname, String lname){
        String uname = fname.toLowerCase() + "." + lname.toLowerCase();
        List<String> similarUnames = EmployeeData.getUnames(uname);
        if(similarUnames.size() > 0){
            int max = 0;
            for(String u: similarUnames){
                int suffix = findInt(u);
                if(suffix > max){max = suffix;}
            }//end for
            uname = uname + max;
        }//end if
        return uname;
    }

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
