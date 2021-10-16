package dev.dinh.services;

import dev.dinh.data.EmployeeData;
import dev.dinh.data.daos.EmployeeDAO;
import dev.dinh.models.Employee;
import dev.dinh.models.enums.Role;
import dev.dinh.services.interfaces.AuthServiceInterface;

public class AuthService implements AuthServiceInterface {

    EmployeeDAO employeeData = new EmployeeData();

    public boolean isManager(String token){
        if("MANAGER".equals(token.split(":")[1])){
            return true;
        }
        return false;
    }//end isManager

    public boolean validToken(String token){
        if(token==null){
            return false;
        }
        String[] tokenArr = token.split(":");
        if(tokenArr.length != 2) {
            return false;
        }
        if(!tokenArr[0].matches("^\\d+$")){
            return false;
        }
        Role[] roles = Role.values();
        for(Role r: roles){
            if(r.toString().equals(tokenArr[1])){
                return true;
            }
        }
        return false;
    }//end validToken

    /**
     * Authenticates user by provided username and password
     * @param uname username
     * @param password
     * @return Employee object, will be null if unable to authenticate user
     */
    @Override
    public Employee login(String uname, String password){
        if(uname!=null && !uname.isEmpty() && password!=null && !password.isEmpty()){
            Employee e = employeeData.getEmployeeByUname(uname);
            if(password.equals(e.getPassword())){
                return e;
            }
        }
        return null;
    }

    /**
     * Verifies supplied token belongs to an employee in the database
     * @param token
     * @return
     */
    @Override
    public boolean isEmployee(String token) {
        int employeeID = Integer.parseInt(token.split(":")[0]);
        Employee result = employeeData.getEmployeeByID(employeeID);
        if(result.getEmployeeID() == employeeID){
            return true;
        }
        return false;
    }

}
