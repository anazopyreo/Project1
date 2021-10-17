package dev.dinh.services;

import dev.dinh.models.Employee;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {

    AuthService auth = new AuthService();

    @BeforeEach
    public void runSetUp(){
//        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test")) {
//            RunScript.execute(connection, new FileReader("sql-setup.sql"));
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testLoginGoodCredentials (){
//        Employee e = auth.login("shawn.mao", "ChangeMeNow!");
//        int expected = 2;
//        int actual = e.getEmployeeID();
//        assertEquals(expected, actual);
    }

    @Test
    public void testLoginByNullUname(){
//        Employee actual = auth.login(null, "ChangeMeNow");
//        assertNull(actual);
    }

    @Test
    public void testLoginByNullPassword(){
//        Employee actual = auth.login("shawn.mao", null);
//        assertNull(actual);
    }

    @Test
    public void testLoginByUnameNotInSystem(){
//        Employee actual = auth.login("edward.nigma", "ChangeMeNow!");
//        assertNull(actual);
    }

    @Test
    public void testLoginByWrongPassword() {
//        Employee actual = auth.login("shawn.mao", "a");
//        assertNull(actual);
    }

    @Test
    public void testIsEmployeeValidID(){
//        String token = "2:MANAGER";
//        boolean actual = auth.isEmployee(token);
//        assertTrue(actual);
    }

    @Test
    public void testIsEmployeeInvalidID(){
//        String token = "47:ASSOCIATE";
//        boolean actual = auth.isEmployee(token);
//        assertFalse(actual);
    }
}
