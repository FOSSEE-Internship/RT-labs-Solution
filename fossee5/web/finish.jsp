<%-- 
    Document   : finish
    Created on : 15 Jun, 2016, 8:35:11 PM
    Author     : Anamika Modi
--%>

<%-- 
    This page is for basically closing all the current resources
    rather attributes that are attached to user's current session.
    Called when session time for experiment that is 55 mins are over.
   
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.scilab.modules.javasci.Scilab"%>
<%@page import="Comm.Con"%>
<%@page import="Comm.Con"%>

<!DOCTYPE html>
  <%
        if(session!=null){
            try{
                /*
                Releases the connection object .
                */
                ((Con) session.getAttribute("con")).disconnect();
            }
            catch(NullPointerException e){
                
            }
           try{
               /*
               for closing scilab instance
               */
               ((Scilab) session.getAttribute("sci")).close();
            }
           catch(NullPointerException e){
                
            }
            try{
                /*
               for closing log file instance
               */
               ((PrintWriter)session.getAttribute("writerfile")).close();
            }
            catch(NullPointerException e){
                
            }
            /*
            removing all the attribute realated to the session.
            session will still be live and will get closed when user
            logs out.
            */
                session.removeAttribute("con");
                session.removeAttribute("sci");
                session.removeAttribute("check");
                session.removeAttribute("writerfile");
                session.removeAttribute("counter");
                session.removeAttribute("code");
                session.removeAttribute("filename");
              
        }
  %>
  <body>
      <p>Experiment closed or stopped </p>
  </body>
  /*
  This window is basically opened up 
  as a pop-up window , with a timer !
  Thus we will want the user to allow pop-ups 
  on his/her browser .
  */
  <script>
      setTimeout(function(){window.close();},10); 
  </script>