<%-- 
    Document   : loginOutput.jsp
    Created on : 03-Apr-2024, 5:58:44 pm
    Author     : Infam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="valid" class="Bean.Login" />
<jsp:setProperty name="valid" property="username"/>
<jsp:setProperty name="valid" property="password"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% if(valid.login1("admin", "admin")) {%>
        <h1>Login Success Full</h1>
        <% } else { %>
        <h1>Incorrect Username or Password</h1>
        <% } %>
    </body>
</html>

