<%-- 
    Document   : createnewlog
    Created on : 16 Jun, 2016, 5:16:28 PM
    Author     : Anamika Modi
--%>
<%-- 
The page caters the need  of creating  a new log file 
for a new experiment which user starts by pressing "STOP"
button.

@attribute(attached to session
writerfile-PrintWriter object for log file 
log file is currently is created in /home/anamika/testlog/date-time-stamp.txt
--%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
    String tempName= dateFormat.format(date);
            String nameFile[]=tempName.split(" ");
            tempName=nameFile[0]+"-"+nameFile[1]+".txt";
    File file = new File("/home/anamika/testlog/"+tempName);
            System.out.println(file.getAbsolutePath());
            if(!file.exists()) {
                file.createNewFile();
             } 
            session.removeAttribute("writerfile");
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            session.setAttribute("writerfile",writer);
            session.removeAttribute("code");
    %>
</html>
<script>
      setTimeout(function(){window.close();},10); 
  </script>
