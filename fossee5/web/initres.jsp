<%-- 
    Document   : initres
    Created on : 9 Jun, 2016, 6:37:59 PM
    Author     : root
--%>

<%@page import="org.scilab.modules.javasci.Scilab"%>
<%@page import="Comm.Con"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.io.File"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.IOException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script>
            if (window.localStorage) {
        // flag the page as being unloading
        window.localStorage['myUnloadEventFlag']=new Date().getTime();
    }
            </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <%
            Scilab sci=new Scilab();
            session=request.getSession(true);
           System.out.println("session created");
            session.setAttribute("sci",sci);   
            Con c=new Con();
            session.setAttribute("con",c);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
	    String tempName= dateFormat.format(date);
            String nameFile[]=tempName.split(" ");
            tempName=nameFile[0]+"-"+nameFile[1]+".txt";
            int count=0;
            session.setAttribute("counter", count);
            File file = new File("/home/anamika/testlog/"+tempName);
            System.out.println(file.getAbsolutePath());
            if(!file.exists()) {
                file.createNewFile();
             } 
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            session.setAttribute("writerfile",writer);
            //writer.write(String.format("%3s %15s %8s %8s %14s \r\n","NO.","TIMESTAMP","HEAT","FAN","TEMPERATURE"));
            
            String portName=null;
            try{
                
                 String target1 = "/home/anamika/test.sh";
                 Runtime rt = Runtime.getRuntime();
                 Process proc1 = rt.exec(target1);
                 proc1.waitFor();
                 String output = new String();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(proc1.getInputStream()));
                 String line = "";                       
                 while ((line = reader.readLine())!= null) {
                         output+=line;
                 }
                String[] port=output.split(" ");
                portName=port[port.length-1];
                  //  System.out.println(portName);
         } catch (IOException | InterruptedException t) {
                 t.printStackTrace();
         }
            int check=c.connect(portName);
            
            session.setAttribute("check", check);
            System.out.println("connected");
            response.sendRedirect("sbhs.jsp");
        %>
    </head>
    <body>
        <h1>Initiating the resources please wait</h1>
        
    </body>
</html>
