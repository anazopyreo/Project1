package dev.dinh.data;

import dev.dinh.data.daos.EmployeeDAO;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData implements EmployeeDAO {

    /**
     * returns a list of usernames that begin with the provided uname
     * @param uname the username to be checked
     * @return a String Array of usernames beginning with uname
     */
    public static List<String> getUnames(String uname) {
        List<String> unames = new ArrayList<String>();
        //get all unames from the database that start with the uname provided
        return unames;
    }


}
