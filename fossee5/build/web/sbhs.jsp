<%-- 
    Document   : sbhs
    Created on : 4 Jun, 2016, 11:04:43 AM
    Author     : root
--%>

<%@page import="java.io.File"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="Comm.Con"%>
<%@page import="org.scilab.modules.javasci.Scilab"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<head>
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
        <title>SCILAB</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
  <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <style type="text/css">
      #slider {
    margin: 10px;
}
    #slider1 {
    margin: 10px;
}
    .ui-slider .ui-slider-handle {
    height: 15px;
    width: 5px;
    padding-left: 5px;
}
    .ui-slider-horizontal {
    height: 8px;
    width: 200px;
}
  </style>
  <script>
      window.onbeforeunload = function() {
          if (window.localStorage) {
        // flag the page as being unloading
        window.localStorage['myUnloadEventFlag']=new Date().getTime();
    }
        var wnd=window.open("close.jsp","MsgWindow", "width=100,height=100");
       // setTimeout(function(){wnd.close();},500);
        return true;
};
window.onload = function() {
         if (window.localStorage) {
        var t0 = Number(window.localStorage['myUnloadEventFlag']);
        if (isNaN(t0)) t0=0;
        var t1=new Date().getTime();
        var duration=t1-t0;
        if (duration>100*1000) {
            alert('heya');
            var wnd=window.open("close.jsp","MsgWindow", "width=100,height=100");
       // setTimeout(function(){wnd.close();},500);
        }
}
};
   function start(){
    
        var eventSource = new EventSource("sbhs_send");
          var setpoint= $("#setpoint").val();
          var fan = $("#fan").val();
        $.ajax({
                url: "sbhs_send",
                type: 'GET',   
                dataType: 'text',
                data: {
                    mypostvar: setpoint,
                    mypostvar1: fan
 
            },
                
                success: function (data) {
        if(data.split("::")[1]!==undefined){
            if(data.split("::")[2]==="done"){
                window.location.href="reload.jsp";
                document.getElementById('temp').innerHTML="experiment finished";
                
            }
            else if(data.split("::")[2]==="error"){
               
                document.getElementById('temp').innerHTML=data.split("::")[1];
            }
            else
                   document.getElementById('temp').innerHTML=data.split("::")[1];
                }
                }
            });   
    }
   
            
</script>
  
  
    </head>
    <body>
         <h1>FEEDBACK (SBHS)</h1>
         <form method="post" >
           Setpoint:<input id ="setpoint" readonly="true"/><div id="slider"></div>
           Fan:<input id="fan" readonly="true"/><div id="slider1"></div>
           Iteration rate :<select id="itr" >
               <option disabled="true" selected value>---</option>
               <option value="0.50">0.50 secs</option>
               <option value="1.00">1.00 secs</option>
               <option value="1.5">1.500 secs</option>
               <option value="2.0">2.000 secs</option>
               <option value="2.5">2.500 secs</option>
               <option value="3.0">3.000 secs</option>
               <option value="3.5">3.500 secs</option>
           </select>
         </form>
         <button id="start">START</button>
         <button id="stop"onclick="stop()">STOP</button>
          <button id="pause">PAUSE</button>
          
        <h2 id="temp" ></h2>
        <h2 id="heat"></h2>
         

<script>
  $(function() {
    $( "#slider" ).slider({
     orientation: "horizontal",
    max: 100,
    step: 1,
    change: function(event, ui) {
      $('#setpoint').attr('value', ui.value);
    },
    min: 0
    });
  });
  $(function() {
    $( "#slider1" ).slider({
     orientation: "horizontal",
    max: 100,
     change: function(event, ui) {
      $('#fan').attr('value', ui.value);
    },
    min: 0
    });
    
  });
  
  </script>
         <script type="text/javascript">
            
           $(document).ready(function(){ 
               
              $( "#slider" ).on( "slidechange", function( event,ui) {
                  document.getElementById("setpoint").innerHTML= ui.value;
              });
           $( "#slider1" ).on( "slidechange", function( event,ui) {
                  document.getElementById("fan").innerHTML= ui.value;
               });
             
        });
        
        </script>
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
            writer.write(String.format("%3s %15s %8s %8s %14s \r\n","NO.","TIMESTAMP","HEAT","FAN","TEMPERATURE"));
            
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
        %>
        <script>
            var myID=0;
        $("#start").on("click",function(){ 
               
            if($("#itr").val()*1000!==0){
            myID=setInterval(start,($("#itr").val()*1000));
        }
        });
    $("#pause").on("click",function(){ 
               clearInterval(myID);
        });
         $("#stop").on("click",function(){ 
             clearInterval(myID);
                var wnd=window.open("close.jsp","connectWindow", "width=100,height=100,menubar=no");
                //setTimeout(function(){wnd.close();},100);
        });
        </script>
    </body>
</html>


