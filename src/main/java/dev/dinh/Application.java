package dev.dinh;

import dev.dinh.data.EmployeeData;
import dev.dinh.data.ReimRequestData;
import dev.dinh.models.Employee;
import dev.dinh.models.ReimRequest;
import dev.dinh.models.enums.Category;
import dev.dinh.models.enums.ReimReqStatus;
import dev.dinh.models.enums.ReimReqStatus.*;
import dev.dinh.services.EmployeeService;
import dev.dinh.services.ManagerService;
import dev.dinh.services.ReimRequestService;

import java.util.List;

import static dev.dinh.models.enums.Category.*;

public class Application {

    public void initiate() {
        ReimRequestService rrs = new ReimRequestService();
        List<ReimRequest> reqs = rrs.getRequests("all",19);
        for(ReimRequest rr: reqs){
            System.out.println(rr);
        }

    }
}
