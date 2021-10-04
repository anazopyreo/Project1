package dev.dinh.data.daos;

import dev.dinh.models.Employee;

public interface EmployeeDAO {

    public void createEmployee(Employee e);

    public Employee getEmployeeByID(int employeeID);
}
