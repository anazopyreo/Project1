package dev.dinh.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.dinh.models.enums.Role;
import dev.dinh.services.EmployeeService;

import java.time.LocalDate;
import java.util.Objects;

import static dev.dinh.models.enums.Role.*;

public class Employee {
    int employeeID;
    String uname; //username

    @JsonIgnore
    String password; //password
    String fname;
    String mname;
    String lname;
    String pemail; //personal email
    String wemail; //work email
    String pphone; //personal phone
    String wphone; //work phone

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate hireDate;
    Role role;

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
        this.wemail = uname + "@revicher.not";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPemail() {
        return pemail;
    }

    public void setPemail(String pemail) {
        this.pemail = pemail;
    }

    public String getWemail() {
        return wemail;
    }

    public void setWemail(String wemail) { this.wemail = wemail; }

    public String getPphone() {
        return pphone;
    }

    public void setPphone(String pphone) {
        this.pphone = pphone;
    }

    public String getWphone() {
        return wphone;
    }

    public void setWphone(String wphone) {
        this.wphone = wphone;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Employee() {
    }

    public Employee(String fname, String mname, String lname, String pemail, String uname) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.pemail = pemail;
        this.uname = uname;
        this.wemail = uname + "@revicher.not";
        this.password = "ChangeMeNow!";
        role = ASSOCIATE;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", uname='" + uname + '\'' +
                ", fname='" + fname + '\'' +
                ", mname='" + mname + '\'' +
                ", lname='" + lname + '\'' +
                ", pemail='" + pemail + '\'' +
                ", wemail='" + wemail + '\'' +
                ", pphone='" + pphone + '\'' +
                ", wphone='" + wphone + '\'' +
                ", hireDate=" + hireDate +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getEmployeeID() == employee.getEmployeeID() && getUname().equals(employee.getUname()) && getPassword().equals(employee.getPassword()) && getFname().equals(employee.getFname()) && Objects.equals(getMname(), employee.getMname()) && getLname().equals(employee.getLname()) && getPemail().equals(employee.getPemail()) && Objects.equals(getPphone(), employee.getPphone()) && Objects.equals(getWphone(), employee.getWphone()) && getHireDate().equals(employee.getHireDate()) && getRole() == employee.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeID(), getUname(), getPassword(), getFname(), getMname(), getLname(), getPemail(), getPphone(), getWphone(), getHireDate(), getRole());
    }
}
