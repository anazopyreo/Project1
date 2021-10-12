package dev.dinh.services.interfaces;

import dev.dinh.models.Employee;

public interface AuthInterface {
    public Employee login(String uname, String password);

    public boolean isManager(String token);

    public boolean isEmployee(String token);
}
