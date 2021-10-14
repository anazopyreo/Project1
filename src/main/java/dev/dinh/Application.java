package dev.dinh;

import dev.dinh.models.ReimRequest;
import dev.dinh.services.ReimRequestService;

import java.util.List;

public class Application {

    public void initiate() {
        ReimRequestService rrs = new ReimRequestService();
        List<ReimRequest> reqs = rrs.getRequestList("all",19);
        for(ReimRequest rr: reqs){
            System.out.println(rr);
        }

    }
}
