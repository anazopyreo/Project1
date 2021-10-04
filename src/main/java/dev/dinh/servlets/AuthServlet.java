package dev.dinh.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        //process GET request and prepare a response
        System.out.println("It worked!");
    }




}
