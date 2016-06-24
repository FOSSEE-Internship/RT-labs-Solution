<%-- 
    Document   : sbhshome
    Created on : 15 Jun, 2016, 7:29:03 PM
    Author     : root
--%>

<%@page import="bean.user"%>
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
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js"></script>    
    <style>
    html { margin: 0; height: 100%; }
    body { padding-top: 0px; position: relative; margin: 0; min-height: 88%; }
    .ui-dialog-title {
    height: 5px;
    width: 5px;
    padding-left: 5px;
    
} 
.ui-dialog-titlebar {
  background-color: #FFFFFF;
  background-image: none;
  color: #000;
 float: right;
  border: 0;
  padding: 0;
}
.ui-dialog-titlebar-close {
    top: 0;
    right: 0;
    margin: 0;
    z-index: 999;
}
 
    </style>
</head>
<body>
    <%
    String s=((user)(request.getSession().getAttribute("user"))).getusername();
    %>
    <div class="navbar navbar-inverse navbar-static-top" >
        <div></div>
    <div class="navbar-inner">
        <div class="container">
            <a href="/sbhs/" class="brand">Single Board Heater System Lab</a>

            <ul class="nav pull-right">
                
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
                <li><a id='exp' href="experiment.jsp">Experiment</a></li>
                <li><a href="/sbhs/quiz ">Quiz</a></li>
                <li><a href="/sbhs/feedback">Feedback / Contact Us</a></li> 
                <li><a href="/sbhs/about">About Us</a></li>                
            </ul>
        </div>
    </div>    
</div>
<div id="dialog">
    <iframe id="myIframe" width="100%" height="100%" src=""></iframe>
</div>
<button id="dialogBtn">Open Dialog</button>   
    <script>
   $("#dialog").dialog({
    autoOpen: false,
    modal: true,
    height: 800,
    width :1100,
    open: function(ev, ui){
             $('#myIframe').attr('src','initres.jsp');
             
    $('#dialog').css('overflow', 'hidden'); //this line does the actual hiding
  
          }
});

$('#dialogBtn').click(function(){
    $('#dialog').dialog('open');
});

     </script>

     
<div class="container">
    <div class="row">
        <div class="span12">
  <ul class="nav nav-pills">
   <br>

   <li> <button type="button" class="btn btn-default" id="home-nav"><a href="/sbhs/home">Home</a></button></li>
         <li><button type="button" class="btn btn-default" id="book-slot-nav"><a href="slot.jsp">Book slot</a></button></li>
            <li> <button type="button" class="btn btn-default" id="view-slot-nav"><a href="/sbhs/slot">View/Delete slot</a></button></li>
         <li> <button type="button" class="btn btn-default" id="download-log-nav"><a href="/sbhs/experiment/logs">Download log files</a></button></li>
         <li><button type="button" class="btn btn-default" id="video-nav"><a href="/sbhs/show_video">Show video</a></button>
         <li> </li>
  </ul>
</div>

        <script>
        document.getElementById("home-nav").classList.add("active");
        </script>
        <div class="span12">
            <h2>Welcome to Single Board Heater System Lab</h2>
            <br>
            You can:
            <ul>
            
                <li>book your slot by going to the "Book slot" section above</li>
                <li>download the client code for experiments using the download link above</li>
                <li>download your previous experiment logs by going to "Download log files" section above</li>
                <li>see the experiment video by going to "Show video" section above</li>
            
            </ul>
        </div>
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
        <small style="float: right; font-size: 10px; margin: -8px 10px 2px 0;">Server time: <span id="timer">3rd Jun 2016 04:04:48 PM</span>. Copyright &copy; 2014 <a href="www.iitb.ac.in" target="_blank">www.iitb.ac.in</a>. Designed and hosted by <a href="http://www.cdeep.iitb.ac.in/" target="_blank">Automation Lab, CDEEP, IIT Bombay</a></small>
    </div>
</div>

<script>
(function(){
    var date_string = "2016-06-04 15:30:48";
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
