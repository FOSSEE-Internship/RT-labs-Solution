<%-- 
    Document   : close
    Created on : 9 Jun, 2016, 2:34:41 PM
    Author     : root
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
            if(((Con) session.getAttribute("con"))!=null) {
                ((Con) session.getAttribute("con")).disconnect();
            }
            if(((Scilab) session.getAttribute("sci"))!=null){
               ((Scilab) session.getAttribute("sci")).close();
            }
            if(((PrintWriter)session.getAttribute("writerfile"))!=null){
               ((PrintWriter)session.getAttribute("writerfile")).close();
            }
                session.removeAttribute("con");
                session.removeAttribute("sci");
                session.removeAttribute("check");
                session.removeAttribute("writerfile");
                session.removeAttribute("counter");
              session.invalidate();
                
        }
  %>
  <body>
      <p>Experiment closed or stopped </p>
  </body>
  <script>
      setTimeout(function(){window.close();},10); 
  </script>