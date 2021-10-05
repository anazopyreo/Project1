package dev.dinh;

import dev.dinh.data.EmployeeData;
import dev.dinh.models.Employee;
import dev.dinh.services.EmployeeService;
import dev.dinh.services.ManagerService;

import java.util.List;

public class Application {

    public void initiate() {
        EmployeeService es = new EmployeeService();
        es.promoteEmployee(19);
    }

}
