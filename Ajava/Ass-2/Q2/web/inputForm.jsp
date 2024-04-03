<%-- 
    Document   : inputForm
    Created on : 03-Apr-2024, 5:05:14 pm
    Author     : Infam
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Submitted Information</title>
</head>
<body>
    <h2>Submitted Information</h2>
    <p>Name: ${param.name}</p>
    <p>Email: ${param.email}</p>
    <p>Gender: ${param.gender}</p>
    <p>Known Languages: 
        <c:forEach var="language" items="${paramValues.language}">
            ${language} 
        </c:forEach>
    </p>
    <p>Address: ${param.address}</p>
</body>
</html>


