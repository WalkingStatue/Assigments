<%-- 
    Document   : submitForm
    Created on : 03-Apr-2024, 5:05:37 pm
    Author     : Infam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String name= request.getParameter("name");
            String email= request.getParameter("email");
            String gender= request.getParameter("gender");
            String language= request.getParameter("language");
            String address = request.getParameter("address");
        %>
        <h3><%= name %></h3><br></br>
        <h3><%= email %></h3><br></br>
        <h3><%= gender %></h3><br></br>
        <h3><%= language %></h3><br></br>
        <h3><%= address %></h3><br></br>
    </body>
</html>


