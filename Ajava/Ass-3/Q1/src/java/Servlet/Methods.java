package Servlet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import jakarta.servlet.*;

public class Methods implements Servlet {
    ServletConfig conf;
    @Override
    public void init(ServletConfig conf) {
        this.conf = conf;
        System.out.println("Creating....");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("Servicing....");
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
         out.println("<h1>Simple Servlet</h1>");
        out.println("<h1>" + new Date().toString() + "</h1>");
        out.println("<h1>Name: Dhruv Saija</h1>");
        out.println("<h1>Roll NO: 3159</h1>");
        out.println("<h1>Div: B</h1>");
        out.println("<h1>This is Question 1</h1>");
    }

    @Override
    public void destroy() {
        System.out.println("Destroying...");
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.conf;
    }
    
    @Override
    public String getServletInfo() {
        return "this servlet is made by me";
    }
}

