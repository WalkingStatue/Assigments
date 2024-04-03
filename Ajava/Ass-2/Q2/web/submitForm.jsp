<%-- 
    Document   : submitForm
    Created on : 03-Apr-2024, 5:05:37 pm
    Author     : Infam
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>User Information Form</title>
    </head>
    <body>
        <h2>User Information Form</h2>
        <form action="submitForm.jsp" method="post">
            Name: <input type="text" name="name" required /><br/>
            Email: <input type="email" name="email" required /><br/>
            Gender:
            <input type="radio" name="gender" value="Male" required /> Male
            <input type="radio" name="gender" value="Female" required /> Female<br/>
            Known Languages:<br/>
            <input type="checkbox" name="language" value="Java" /> Java<br/>
            <input type="checkbox" name="language" value="Python" /> Python<br/>
            <input type="checkbox" name="language" value="C" /> C<br/>
            Address:<br/>
            <textarea name="address" required></textarea><br/>
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>

