<%-- 
    Document   : autoRefresh.jsp
    Created on : 03-Apr-2024, 6:16:15 pm
    Author     : Infam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Auto Refresh Page</title>
    <!-- Set the page to refresh every 5 seconds -->
    <meta http-equiv="refresh" content="5">
</head>
<body>
    <h2>This page will refresh every 5 seconds.</h2>
    <p>Current Time: <%= new java.util.Date() %></p>
</body>
</html>

