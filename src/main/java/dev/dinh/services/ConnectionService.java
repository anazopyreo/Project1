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
        String username;
        String password;
        String url = System.getenv("DB_URL");
        username = System.getenv("PostgreSQLUname");
        password = System.getenv("PostgreSQLPword");
//            url = "jdbc:postgresql://project1.cx1u2au4pz0d.us-east-2.rds.amazonaws.com:5432/postgres";
//            username = "postgres";
//            password = "C3rb3rus";
        return DriverManager.getConnection(url, username, password);
    }
}
