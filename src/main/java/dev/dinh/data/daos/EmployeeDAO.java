package dev.dinh.data.daos;

import dev.dinh.models.Employee;

import java.util.List;

public interface EmployeeDAO {

    public void createEmployee(Employee e);

    public Employee getEmployeeByID(int employeeID);

    public Employee getEmployeeByUname(String uname);

    public List<Employee> getAllEmployees();
}
