package dev.dinh.data.daos;

import dev.dinh.models.Employee;
import dev.dinh.models.enums.Role;

import java.util.List;

public interface EmployeeDAO {

    void createEmployee(Employee e);

    Employee getEmployeeByID(int employeeID);

    Employee getEmployeeByUname(String uname);

    List<Employee> getAllEmployees();

    void setRole(int employeeID, Role role);

    List<String> getUnames(String uname);
}
