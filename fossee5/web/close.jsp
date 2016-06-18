<%-- 
    Document   : close
    Created on : 9 Jun, 2016, 2:34:41 PM
    Author     : Anamika Modi
--%>
<%--
    This page is when an error occurs in the scilab
    code.The old scilab instance is stopped and a new one is started.
    @attribute sci-Scilab(session)
--%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.scilab.modules.javasci.Scilab"%>
<%@page import="Comm.Con"%>
<%@page import="Comm.Con"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <%
        if(session!=null){ 
           try{
              ((Scilab) session.getAttribute("sci")).close();
            }
           catch(NullPointerException e){
                
            }
            session.removeAttribute("sci");     
            Scilab sci=new Scilab();
            session.setAttribute("sci",sci);         
        }
  %>
  <body>
      <p>Experiment closed or stopped </p>
  </body>
  <script>
      setTimeout(function(){window.close();},10); 
  </script>
  