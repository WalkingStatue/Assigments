/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bean;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.util.Date;

public class Generic extends GenericServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("Generic servlet ");
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h1>Generic Servlet</h1>");
        out.println("<h1>" + new Date().toString() + "</h1>");
        out.println("<h1>Name: Dhruv Saija</h1>");
        out.println("<h1>Roll NO: 3159</h1>");
        out.println("<h1>Div: B</h1>");
        out.println("<h1>This is Question 2</h1>");
    }
}
