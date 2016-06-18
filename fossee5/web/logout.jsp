<%-- 
    Document   : logout
    Created on : 16 Jun, 2016, 5:57:28 PM
    Author     : Anamika Modi
--%>
<%-- 
    Session invalidation on logout
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
     session.invalidate();
     response.sendRedirect("index.html");
     %>
</html>
