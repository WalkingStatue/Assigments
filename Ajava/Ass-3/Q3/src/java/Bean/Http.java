/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bean;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class Http extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get method.......");
        resp.setContentType("text/html");
        PrintWriter out= resp.getWriter();
        out.println("<h1>HTTP Servlet</h1>");
        out.println("<h1>" + new Date().toString() + "</h1>");
        out.println("<h1>Name: Dhruv Saija</h1>");
        out.println("<h1>Roll NO: 3159</h1>");
        out.println("<h1>Div: B</h1>");
        out.println("<h1>This is Question 3</h1>");
    }
}
