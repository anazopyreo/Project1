package dev.dinh.services;

import dev.dinh.data.EmployeeData;

import java.util.List;

public class EmployeeService {
    public static int getNextUname(String uname) {
        int output = 0;
        List<String> unames = EmployeeData.getUnames(uname);
        //parse list of unames and return next avalable number to be appended on end of uname i.e. chuck.jones6
        return output;
    }
}
