<%-- 
    Document   : EmployeeDetails.jsp
    Created on : 03-Apr-2024, 5:49:34 pm
    Author     : Infam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="emp" class="bean.Employee"/>
<jsp:setProperty name="emp" property="name" value="Dhruv Saija"/>
<jsp:setProperty name="emp" property="dob" value="08-08-2003"/>
<jsp:setProperty name="emp" property="email" value="saijadhruv8803@gmail.com"/>
<jsp:setProperty name="emp" property="address" value="JivrajPark"/>
<jsp:setProperty name="emp" property="city" value="Ahmedabad"/>
<jsp:setProperty name="emp" property="companyName" value="Facebook Inc."/>
<jsp:setProperty name="emp" property="employeeId" value="3159"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Details</title>
    </head>
    <body>
        <h3>Employee Name: <jsp:getProperty name="emp" property="name" /></h3>
        <h3>Date of Birth: <jsp:getProperty name="emp" property="dob" /></h3>
        <h3>Email: <jsp:getProperty name="emp" property="email" /></h3>
        <h3>Address: <jsp:getProperty name="emp" property="address" /></h3>
        <h3>City: <jsp:getProperty name="emp" property="city" /></h3>
        <h3>Company Name: <jsp:getProperty name="emp" property="companyName" /></h3>
        <h3>Employee ID: <jsp:getProperty name="emp" property="employeeId" /></h3>
    </body>
</html>

