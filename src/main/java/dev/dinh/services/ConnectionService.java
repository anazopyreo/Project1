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

        //useless comment
        String url;
        String username;
        String password;
        url = System.getenv("DB_URL");
        username = System.getenv("PostgreSQLUname");
        password = System.getenv("PostgreSQLPword");
//        if(url==null){
//            url = "jdbc:postgresql://project1.cx1u2au4pz0d.us-east-2.rds.amazonaws.com:5432/postgres";
//        }
//        if(username==null) {
//            username = "postgres";
//        }
//        if(password==null) {
//            password = "C3rb3rus";
//        }
        return DriverManager.getConnection(url, username, password);
    }
}
