package dev.dinh;

import dev.dinh.data.EmployeeData;
import dev.dinh.models.Employee;
import dev.dinh.services.EmployeeService;
import dev.dinh.services.ManagerService;

public class Application {

    public void initiate() {
        ManagerService m = new ManagerService();
        m.demoteEmployee(5);
    }
}
