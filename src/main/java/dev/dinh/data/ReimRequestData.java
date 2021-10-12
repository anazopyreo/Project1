package dev.dinh.data;

import dev.dinh.data.daos.ReimRequestDAO;
import dev.dinh.models.ReimRequest;
import dev.dinh.models.enums.Category;
import dev.dinh.models.enums.ReimReqStatus;
import dev.dinh.services.ConnectionService;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReimRequestData implements ReimRequestDAO {

    private static ConnectionService connectionService = new ConnectionService();

    @Override
    public void createRequest(ReimRequest rr) {
        String sql = "insert into request (amount,category,status,req_emp_id) values (?,?,'PENDING',?) returning request_id, req_date";
        try(Connection c = connectionService.establishConnection();
            PreparedStatement pstmt = c.prepareStatement(sql);){
            pstmt.setDouble(1,rr.getAmount());
            pstmt.setString(2,rr.getCategory().toString());
            pstmt.setInt(3,rr.getReqEmployeeID());
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            rr.setRequestID(rs.getInt("request_id"));
            rr.setReqDate(rs.getTimestamp("req_date").toLocalDateTime());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public ReimRequest getRequest(int requestID) {
        String sql = "select * from request where request_id = ?";
        ReimRequest rr = new ReimRequest();
        try(Connection c = connectionService.establishConnection();
            PreparedStatement pstmt = c.prepareStatement(sql);){
            pstmt.setInt(1,requestID);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            rr.setRequestID(rs.getInt("request_id"));
            rr.setAmount(rs.getDouble("amount"));
            rr.setCategory(Category.valueOf(rs.getString("category")));
            rr.setStatus(ReimReqStatus.valueOf(rs.getString("status")));
            rr.setReqDate(rs.getTimestamp("req_date").toLocalDateTime());
            if(null != rs.getTimestamp("dec_date")){
            rr.setDecDate(rs.getTimestamp("dec_date").toLocalDateTime());}
            rr.setReqEmployeeID(rs.getInt("req_emp_id"));
            rr.setDecManagerID((rs.getInt("dec_manager_id")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rr;
    }

    @Override
    public void updateRequest(ReimRequest rr) {
        String sql = "update request " +
                     "set status = ?, " +
                     "dec_manager_id = ? " +
                     "where request_id = ? returning dec_date";
        try(Connection c = connectionService.establishConnection();
            PreparedStatement pstmt = c.prepareStatement(sql);){
            pstmt.setString(1,rr.getStatus().toString());
            pstmt.setInt(2,rr.getDecManagerID());
            pstmt.setInt(3,rr.getRequestID());
            ResultSet rs = pstmt.executeQuery();
            rs.next();
//            rr.setDecDate(rs.getTimestamp("dec_date").toLocalDateTime());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private String getClause(String filter) {
        StringBuilder bob = new StringBuilder("select * from request ");
        switch(filter){
            case "pending":
                return "where status = 'PENDING'";
            case "resolved":
                return "where status != 'PENDING'";
        }
        return "";
    }

    @Override
    public List<ReimRequest> getRequests(String filter, int employeeID) {
        List<ReimRequest> requests = new ArrayList<>();
        StringBuilder bob = new StringBuilder("select * from request ");
        if(!"all".equals(filter)){
            bob.append(getClause(filter));
            if(employeeID > 0){
                bob.append(" and req_emp_id = ?");
            }
        }
        else if(employeeID > 0){
            bob.append(" where req_emp_id = ?");
        }
        String sql = bob.append(" order by request_id").toString();
        try(Connection c = connectionService.establishConnection();
            PreparedStatement pstmt = c.prepareStatement(sql);){
            if(employeeID > 0){
                pstmt.setInt(1,employeeID);
            }
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                ReimRequest rr = new ReimRequest();
                rr.setRequestID(rs.getInt("request_id"));
                rr.setAmount(rs.getDouble("amount"));
                rr.setCategory(Category.valueOf(rs.getString("category")));
                rr.setStatus(ReimReqStatus.valueOf(rs.getString("status")));
                rr.setReqDate(rs.getTimestamp("req_date").toLocalDateTime());
                if(null != rs.getTimestamp("dec_date")){
                    rr.setDecDate(rs.getTimestamp("dec_date").toLocalDateTime());}
                rr.setReqEmployeeID(rs.getInt("req_emp_id"));
                rr.setDecManagerID((rs.getInt("dec_manager_id")));
                requests.add(rr);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return requests;
    }//end getList
}//end class
