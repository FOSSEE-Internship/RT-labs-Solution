<%-- 
    Document   : initres
    Created on : 9 Jun, 2016, 6:37:59 PM
    Author     : Anamika Modi
--%>

<%--
For intialising all the resources that we are 
giving the user.

@attribute (for session)
sci - Scilab object
con - Connection object
check - status of connection
user - all the user information
writerfile - Printwriter object for current experiment's log file

--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="gnu.io.SerialPort"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="bean.user"%>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <%
            /*
            If user's session is invalidated 
            Then don't allow him any access, redirection to home page.
            */
            if(request.getSession()==null){
                response.sendRedirect("sbhshome.jsp");
            }
            else{
                session=request.getSession(true);
                user u=((user)(request.getSession().getAttribute("user")));
                //System.out.println(u.getusername());
                //System.out.println(session.getId());
                /*
                Scilab instance is created .
                An instance of class Scilab in javasci library.
                */
                Scilab sci=new Scilab();
                //System.out.println(session.getId());
                System.out.println("session created");
                session.setAttribute("sci",sci);   
                /*
                Retrieving the object from server .
                This HashMap is a mapping of connections of ports with the respective MID's.
                Here key is the the MID and value is the serial port object.
                */
                HashMap<Integer,SerialPort> hashMap=(HashMap)getServletContext().getAttribute("hashMap");
                Iterator it = hashMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    System.out.println(pair.getKey() + " = " + pair.getValue());
                    //SerialPort s=(SerialPort)(pair.getValue());
                }
                /*
                Get the Serial Port according to the mid.
                */
                SerialPort sp=(SerialPort)(hashMap.get(Integer.parseInt(((user)(session.getAttribute("user"))).getm_id())));
                if(sp==null)
                   System.out.println("null sp");
                /*
                *   Make a connection using Con class
                *   in Comm package. It will give access
                *   to input and output streams of the port.
                */
                Con c=new Con();
                session.setAttribute("con",c);
                /*
                * status of connection is stored in
                * check . 
                *  1- successful connection 
                *  0- unsuccessful
                */
                int check=c.connect(sp);
                System.out.println(check);
                session.setAttribute("check", check);
                System.out.println("connected");
                /*
                * date formatting for creating the name of the log
                * file of the experiment .
                * Log file name structure= yyyy-MM-dd-HH:mm:ss.txt
                * stored in dir - /home/anamika/testlog
                */
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
                /*
                *   printWriter object for editing the log file
                *   attached to the session .
                */
                PrintWriter writer = new PrintWriter(file, "UTF-8");
                session.setAttribute("writerfile",writer);
                /*
                * The needful in terms of resources is done.
                * Hence,redirected to experiment page.
                */
                response.sendRedirect("sbhs.jsp");
            }
        %>
    </head>
    <body>
        <h1>Initiating the resources please wait</h1>
    </body>
    <script>
        window.sessionStorage['myUnloadEventFlag']='false';
    </script>
</html>
