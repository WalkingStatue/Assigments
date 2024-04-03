<%-- 
    Document   : inputForm
    Created on : 03-Apr-2024, 5:05:14 pm
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
        <form action="submitForm.jsp">
            Name: <input type="text" name="name" value="" /><br></br>
            Email: <input type="text" name="email" value="" /><br></br>
            Gender: <input type="radio" name="gender" value="Male" checked="checked" />Male
                    <input type="radio" name="gender" value="Female" />Female<br></br>
            Language: <input type="checkbox" name="language" value="Gujrati" />Gujrati
                      <input type="checkbox" name="language" value="English" />English
                      <input type="checkbox" name="language" value="Hindi" />Hindi<br></br>
            Address: <textarea name="address" rows="4" cols="20"> </textarea><br></br>
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>



