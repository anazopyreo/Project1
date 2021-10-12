package dev.dinh.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {
    public Connection establishConnection() throws SQLException {

        String testEnv = System.getenv("IS_TEST");
        if(Boolean.parseBoolean(testEnv)){
            return DriverManager.getConnection("jdbc:h2:~/test");
        }

        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url;
        String host = System.getenv("DB_HOST");
        if(host==null){
            url = "jdbc:postgresql://localhost:5432/project1";
        } else {
            url = "jdbc:postgresql://"+host+":5432/project1";
        }
        String username = "postgres";
        String password = "p4ssw0rd";
        return DriverManager.getConnection(url, username, password);
    }
}
