    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bean;


import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;

/**
 *
 * @author Infam
 */

public class ReadData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html");
       PrintWriter out = response.getWriter();
       out.println("<h2>Welcome</h2>");
       String n = request.getParameter("fname");
       Enumeration e = request.getParameterNames();
       while(e.hasMoreElements()){
         String name = (String)e.nextElement();
         String[] values = request.getParameterValues(name);
         for(int i=0;i<values.length;i++){
           out.println("<html><head><title> Client data </title></head>");
           out.println("<body>");
           out.println("<h1>" + name + ":<i>"+values[i]+"</i><br></h1>");
           out.println("</body></html>"); 
       }
       }     
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

}
