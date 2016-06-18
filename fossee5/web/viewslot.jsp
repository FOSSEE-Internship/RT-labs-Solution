

<%@page import="java.util.Date"%>
<%@page import="bean.user"%>
<%@page import="database.database"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>


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
    table, td 
    {
    border: 1px solid black;}
    </style>
</head>
<body>
    
    <div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a href="/sbhs/" class="brand">Single Board Heater System Lab</a>

            <ul class="nav pull-right">
                
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Welcome, sankalp <b class="caret"></b></a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                        <li><a href="#passwordModal" role="button" data-toggle="modal">Change Password</a></li>
                        <li><a href="#machineModal" role="button" data-toggle="modal">Request machine change</a></li>
                        <li><a href="/sbhs/logout">Logout</a></li>
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
                <li><a href="/sbhs/experiments">Experiments</a></li>
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
    <ul class="nav nav-pills">
        <li><button id="home-nav"><a href="/sbhs/home">Home</a></button></li>
        <li><button id="book-slot-nav"><a href="/sbhs/slot/new">Book slot</a></button></li>
        <li><button id="view-slot-nav"><a href="/sbhs/slot">View/Delete slot</a></button></li>
        <li><button id="download-log-nav"><a href="/sbhs/experiment/logs">Download log files</a></button></li>
        <li><button id="video-nav"><a href="/sbhs/show_video">Show video</a></li>
        </ul>
</div>

        <script>
      document.getElementById("view-slot-nav").classList.add("active");
        </script>
        
      
        <div class="span12">
        <h3>Your booked slots</h3>
<table id="myTable"  class="table table-condensed">
  <tr>
    <td>Slot time </td>
    <td>Slot date </td>
  </tr>
</table>
      <!-- <button onclick="myFunction()">Try it</button>
-->

        
        <!--<td><a class="btn btn-disabled" disabled="disabled">Delete</a></td>
  -->
        </div>
                     
   
  <%

    List<String> stime= new ArrayList<String>(); 
     
 //      for(int i=0;i<stime.size();i++){
    //     System.out.println(stime.get(i));
     
    user us=(user)(request.getSession().getAttribute("user"));
            ///*us.setacc_id(session);*/
            database db;
            db = new database();
            stime=db.userdetails(us);
            int i=0;
            int len=stime.size();
            
            out.println("<table   class='table table-condensed'>");
            for(i=0;i<len-1;i+=2){
                out.print("<tr><td>"+stime.get(i)+"</td><td>"+stime.get(i+1)+"</td><td>DELETE</td></tr>");
                
                Date d=new Date();
                
             //   if(stime.get(i+1)>d){
               //     out.println("<button disbled='true'/>");
                 
                //}
               // else
               // {
                  //  out.println("<button />");
                //}
                out.println("<p></p>");
            }
         out.println("</table>");
  
  %>
            
             <script>
          {
    var btn = document.createElement("BUTTON");
    var t = document.createTextNode("DELETE");
    btn.appendChild(t);
    document.body.appendChild(btn);
}
  </script>
        <script>
   /*
    
    window.onload = function() {
    var table = document.getElementById("myTable");
    
    var len=<%=len%> ;
    
    for(var i=0;i<len;i++ ){
        <%String s=stime.get(--i);
        System.out.print(s);
        %>
        var xy=<%=s%>;
        var row = table.insertRow(i);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    cell1.innerHTML = xy[i];
}
    //cell2.innerHTML = ""; 
    
            }
          //  us.setdate(x);
*/
      </SCRIPT>
     
 




 
                    
        
    </div>
</div>

    <br><br><br>
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
        <small style="float: right; font-size: 10px; margin: -8px 10px 2px 0;">Server time: <span id="timer">9th Jun 2016 12:47:16 PM</span>. Copyright &copy; 2014 <a href="www.iitb.ac.in" target="_blank">www.iitb.ac.in</a>. Designed and hosted by <a href="http://www.cdeep.iitb.ac.in/" target="_blank">Automation Lab, CDEEP, IIT Bombay</a></small>
    </div>
</div>

<script>
(function(){
    var date_string = "2016-06-09 12:47:16";
    var a = date_string.split(/[^0-9]/);
    for(i=0;i<6;i++){a[i]=parseInt(a[i]);}
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

    
<div class="modal hide fade" id="passwordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3>Change Password</h3>
    </div>
    <div class="modal-body">
        <p>Please click following button to send password change link to your email address.</p>
    </div>
    <div class="modal-footer">
        <a data-dismiss="modal" aria-hidden="true" class="btn">Close</a>
        <a href="/sbhs/password" class="btn btn-primary">Send password change link</a>
    </div>
</div>


<div class="modal hide fade" id="machineModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3>Machine change</h3>
    </div>
    <div class="modal-body">
        <p>Please send an email to rupakrokade@iitb.ac.in stating the reasons why machine change is required.</p>
    </div>
    <div class="modal-footer">
        <a data-dismiss="modal" aria-hidden="true" class="btn">Close</a>
    </div>
</div>

    <script>
    $(".alert").alert();
    </script>
</body>
</html>