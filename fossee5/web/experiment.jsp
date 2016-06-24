<%-- 
    Document   : experiment
    Created on : 20 Jun, 2016, 8:11:10 PM
    Author     : sankalp
--%>

<%@page import="database.database"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="bean.user"%>
<%@page import ="java.sql.Connection"%>
<%@page import =" java.sql.DriverManager"%>
<%@page import =" java.sql.PreparedStatement"%>
<%@page import =" java.sql.ResultSet"%>
<%@page import =" java.sql.SQLException"%>
<%@page import =" java.sql.Statement"%>
<%@page import =" java.util.logging.Level"%>
<%@page import =" java.util.logging.Logger"%>
<%@page import =" java.lang.String"%>
<%@page import =" java.util.ArrayList"%>
<%@page import =" java.util.List"%> 



<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Single Board Heater System &middot; Virtual Labs</title>
    <link rel="shortcut icon" href="http://vlabs.iitb.ac.in/sbhs/static/img/favicon.ico">
    <link rel="stylesheet" href="http://vlabs.iitb.ac.in/sbhs/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://vlabs.iitb.ac.in/sbhs/static/css/bootstrap-responsive.min.css">
    <script src="http://vlabs.iitb.ac.in/sbhs/static/js/jquery-latest.min.js"></script>
    <script src="http://vlabs.iitb.ac.in/sbhs/static/js/bootstrap.min.js"></script>
    
    
    <style>
    html { margin: 0; height: 100%; }
    body { padding-top: 70px; position: relative; margin: 0; min-height: 88%; }
    </style>
</head>
<body>
    
    <div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a href="/sbhs/" class="brand">Single Board Heater System Lab</a>

               <ul class="nav pull-right">
                     <% 
                         
                  user us =(user)((request.getSession()).getAttribute("user"));
                  String s= us.getusername();
                %>
                
               
        
                
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Welcome <%=s%> <b class="caret"></b></a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                        <li><a href="#passwordModal" role="button" data-toggle="modal">Change Password</a></li>
                        <li><a href="#machineModal" role="button" data-toggle="modal">Request machine change</a></li>
                        <li><a href="logout.jsp">Logout</a></li>
                    </ul>
                </li>
                
            
               
                
                
            </ul>
        </div>
    </div>
    <div class="navbar-inner">
        <div class="container">
            <ul class="nav pull-right">
                <li><a href="/sbhs/info">SBHS InfoCentre</a></li>
                <li><a href="http://sbhs.os-hardware.in/downloads" target="_blank">Downloads</a></li>
                <li><a href="/sbhs/theory">Theory</a></li>
                <li><a href="/sbhs/procedure">Procedure</a></li>
                <li><a href="">Experiments</a></li>
                <li><a href="/sbhs/quiz ">Quiz</a></li>
                <li><a href="/sbhs/feedback">Feedback / Contact Us</a></li> 
                <li><a href="/sbhs/about">About Us</a></li>                
            </ul>
        </div>
    </div>    
</div>

    

    

<div class="container">
    <div class="row">
        
        <div class="span12">
            <h2>Experiments</h2>            
                <br>
		<br>
		<p><h4>
                	<strong>List of experiments:</strong>
                </h4>
                    <p>
                        <ol>
                            <li>Identification of Transfer Function of a Single Board Heater System through Step Response

Experiment</li>
                            <li>Identification of Transfer Function of a Single Board Heater System through Ramp Response

Experiment</li>
                            <li>Frequency Response Analysis of a Single Board Heater System by the Application of Sine Wave</li>
                            <li>Controlling Single Board Heater System using PID controller</li>
                            <li>Two Degrees of Freedom (2­DOF) Controller</li>
                            <li>PRBS Modeling and Implementation of Pole Placement Controller</li>
                            <li>Implementing Internal Model Controller for First Order System on a Single Board Heater System</li>
                            <li>Design and Implementation of Self Tuning PI and PID Controllers on Single Board Heater System</li>
                            <li>Model Predictive Control in Single Board Heater System using SCILAB</li> 
                        </ol>
                    </p></p>
        </div>
    </div>
    
   
</div>                
<div>
    <form >  
     
        <table  width="20%" bgcolor="00A6FF" align="center"><br><br>
            <td><input type="radio" id="open" name ="open" value="Open">Open Loop
        <input type="radio" id="close" name ="close" value="Close Loop">Close Loop<br>
        <br>        
        
                              <button onclick="Proceed()">Proceed</button>


        </td>
        </table>

        </form>
</div>
    <br><br><br>

<%
        
            database db;
            db = new database();
            db.getdatetime(us);
            db.getepoch(us);
            Integer i=db.enterslot(us);
           
        %>
    <script>
      
   function Proceed()
   {
      // alert('here');
   var proceed=<%=i%>;
   //alert(proceed);
   if(proceed == 1)
    window.open('initres.jsp');
   else
    window.open('initres.jsp');
       
}
    </script>
         
         
         
    <div class="wrapper" style="position: absolute; bottom: 0; right: 0; width: 100%;">
    <div class="container">
        <div class="navbar">
        <ul class="nav pull-right">
            <li><a href="#"><b>External links</b></a></li>
            <li><a href="http://www.iitb.ac.in/" target="_blank">IIT Bombay</a></li>
            <li><a href="http://www.vlab.co.in/" target="_blank">Virtual Labs</a></li>
            <li><a href="http://www.spoken-tutorial.org/" target="_blank">Spoken Tutorials</a></li>
            <li><a href="http://www.co-learn.in/" target="_blank">Co-learn</a></li>
            <li><a href="http://www.nex-robotics.com/" target="_blank">Nex Robotics</a></li>
            <li><a href="http://sbhs.os-hardware.in/forum" target="_blank">SBHS Forum</a></li>
            <li><a href="http://www.cdeep.iitb.ac.in/" target="_blank">CDEEP</a></li>
        </ul>
        </div>
        <small style="float: right; font-size: 10px; margin: -8px 10px 2px 0;">Server time: <span id="timer">16th Jun 2016 05:19:44 PM</span>. Copyright &copy; 2014 <a href="www.iitb.ac.in" target="_blank">www.iitb.ac.in</a>. Designed and hosted by <a href="http://www.cdeep.iitb.ac.in/" target="_blank">Automation Lab, CDEEP, IIT Bombay</a></small>
    </div>
</div>

<script>
(function(){
    var date_string = "2016-06-16 17:19:44";
    var a = date_string.split(/[^0-9]/);
    for(i=0;i<6;i++){a[i]=parseInt(a[i])}
    window.date = new Date(a[0],a[1]-1,a[2],a[3],a[4],a[5]);
    window.monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
    window.ordinalize = function (i) {
        var j = i % 10;
        if (j == 1 && i != 11) return i + "st";
        if (j == 2 && i != 12) return i + "nd";
        if (j == 3 && i != 13) return i + "rd";
        return i + "th";
    }
    window.num_padding = function(i) {
        return i < 10 ? "0" + i : i;
    }

    var timer = function() {
        window.date.setSeconds(window.date.getSeconds() + 1);
        str = window.ordinalize(window.date.getDate()) + " " + window.monthNames[window.date.getMonth()] + " " + window.date.getFullYear() + " " + window.date.toLocaleTimeString().toUpperCase();
        document.getElementById("timer").innerHTML = str;
    }

    setInterval(timer, 1000);
})();
</script>

    
    <script>
    $(".alert").alert();
    </script>
</body>
</html>