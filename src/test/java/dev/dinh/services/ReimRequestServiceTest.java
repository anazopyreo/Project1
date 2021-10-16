package dev.dinh.services;

import dev.dinh.data.ReimRequestData;
import dev.dinh.models.ReimRequest;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static dev.dinh.models.enums.Category.FOOD;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReimRequestServiceTest {

    ReimRequestService rrs = new ReimRequestService();
    ReimRequestData rrd = new ReimRequestData();

    @BeforeEach
    public void runSetUp(){
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test")) {
            RunScript.execute(connection, new FileReader("sql-setup.sql"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

//Ignoring test as H2 database does not work with returning values in insert statements
//    @Test
//    public void testCreateReq(){
//        ReimRequest rr = rrs.createReq(17.56,FOOD,3);
//        ReimRequest rr2 = rrd.getRequest(1);
//        double expected = 17.56;
//        double actual = rr2.getAmount();
//        assertEquals(expected,actual);
//    }
}
