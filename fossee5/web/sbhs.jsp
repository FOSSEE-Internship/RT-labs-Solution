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
  <script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://www.highcharts.com/samples/static/highslide-full.min.js"></script>
<script src="https://www.highcharts.com/samples/static/highslide.config.js" charset="utf-8"></script>
 <link rel="shortcut icon" href="http://vlabs.iitb.ac.in/sbhs/static/img/favicon.ico">
    <link rel="stylesheet" href="http://vlabs.iitb.ac.in/sbhs/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="front.css">
    <link rel="stylesheet" href="http://vlabs.iitb.ac.in/sbhs/static/css/bootstrap-responsive.min.css">
     
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
  <script src="front.js" type="text/javascript" charset="utf-8"></script>
<script>
   
</script>
  <script>
       var myID=0;
       var chart1;
       var chart2;
       var text="";
      $(window).on('beforeunload', function(){
          if (window.localStorage) {
        // flag the page as being unloading
        window.localStorage['myUnloadEventFlag']=new Date().getTime();
    }
      
});
window.onload = function() {
         if (window.localStorage) {
        var t0 = Number(window.localStorage['myUnloadEventFlag']);
        if (isNaN(t0)) t0=0;
        var t1=new Date().getTime();
        var duration=t1-t0;
        if (duration>10*1000) {
            var wnd=window.open("close.jsp","MsgWindow", "width=100,height=100");
            $(window).off('beforeunload');
            if (window.localStorage) {
        // flag the page as being unloading
        window.localStorage['myUnloadEventFlag']=new Date().getTime();
    }
            window.location.href="initres.jsp";
       // setTimeout(function(){wnd.close();},500);
        }
}

};

$(document).ready(function() {
    var editor = ace.edit("editor");
    editor.setTheme("ace/theme/monokai");
    editor.getSession().setMode("ace/mode/javascript");
    colArray=[];
      <%
          String s[]={"null","null"};
          if(session.getAttribute("code")!=null)
         s=((String)session.getAttribute("code")).split("~");
         for(int i=0;i<s.length;i++){
             System.out.println(s[i]);
         }
         %> 
                  
      <% for (int i=0; i<s.length; i++) { %>
colArray[<%= i %>] = "<%= s[i] %>"; 
editor.setValue(editor.getValue()+colArray[<%=i%>]+'\n',1); 


<% } %>
    
      
 
     $("#save").on("click",function(){ 
               text=editor.getValue();
               $.ajax({
                url: "savefile",
                type: 'GET',   
                dataType: 'text',
                data: {
                    mypostvar: text
            },
                
                success: function (data) {
                    alert('yay');
                    window.location.href="sbhs.jsp"; 
                }
                });
      });
      
        chart1 = new Highcharts.Chart({
        chart: {
            renderTo: 'charttemp',
            defaultSeriesType: 'spline',
            events: {
               load:function() {
    chart1 = this; // `this` is the reference to the chart
   setTimeout(start,1000);
}
            }
        },
        title: {
            text: 'Temp vs Time'
        },
        xAxis: {
            //categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
             type: 'datetime',
            tickPixelInterval: 150,
            title: {
                text: 'Time',
                margin: 40
            },
            maxZoom: 20 * 1000
        },
        yAxis: {
            minPadding: 0.2,
            maxPadding: 0.2,
            title: {
                text: 'Temp',
                margin: 40
            }
        },
        exporting: {
            buttons: {
                contextButton: {
                    	menuItems: [{
                            textKey: 'downloadPNG',
                            onclick: function () {
                                this.exportChart();
                            }
                        }, {
                            textKey: 'downloadJPEG',
                            onclick: function () {
                                this.exportChart({
                                    type: 'image/jpeg'
                                });
                            }
                        }]
                }
            }
        },
        series: [{
            
           data: []
        }]
    });  
    chart2 = new Highcharts.Chart({
        chart: {
            renderTo: 'chartheat',
            defaultSeriesType: 'spline',
            events: {
               load:function() {
    chart2 = this; // `this` is the reference to the chart
   setTimeout(start,1000);
}
            }
        },
        credits: {
            enabled: false
        },

        title: {
            text: 'Heat vs Time'
        },
        xAxis: {
            //categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
             type: 'datetime',
            tickPixelInterval: 100,
            title: {
                text: 'Time',
                margin: 40
            },
            maxZoom: 20 * 100
        },
        yAxis: {
            minPadding: 0.2,
            maxPadding: 0.2,
            title: {
                text: 'Heat',
                margin: 40
            }
        },exporting: {
            buttons: {
                contextButton: {
                    	menuItems: [{
                            textKey: 'downloadPNG',
                            onclick: function () {
                                this.exportChart();
                            }
                        }, {
                            textKey: 'downloadJPEG',
                            onclick: function () {
                                this.exportChart({
                                    type: 'image/jpeg'
                                });
                            }
                        }]
                }
            }
        },
        series: [{
          color: '#ff0000', 
           data: []
        }]
    });
});
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
                $(window).off('beforeunload');
                
                var wnd=window.open("close.jsp","MsgWindow", "width=100,height=100");
                //window.location.href="reload.jsp";
                document.getElementById('temp').innerHTML="experiment finished";
                clearInterval(myID);
                
                
            }
            else if(data.split("::")[2]==="error"){
               
                document.getElementById('section2').innerHTML=data.split("::")[1];
            }
            else{
                   document.getElementById('temp').innerHTML=data.split("::")[1].split("&")[0];
                  var series = chart1.series[0],
                   shift = series.data.length > 50; // shift if the series is 
                                                 // longer than 20              
                point=data.split("::")[1].split("&")[0];
                point1=data.split("::")[1].split("&")[1];
                point2=data.split("::")[1].split("&")[2];
              chart1.series[0].addPoint([(new Date()).getTime(),parseFloat(point)],true,shift);
              chart2.series[0].addPoint([(new Date()).getTime(),parseFloat(point1)],true,shift);
                }
            }
                },
                cache: false
            });   
              
    }
    

</script>
  
  
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
    
            <div class="span12">
            <a href="wop.html" class="brand">Single Board Heater System Lab</a>
                        <ul class="nav pull-right">
                
                <li><a href="">Refresh</a></li>
                
            </ul>
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

    <h1> Experiment </h1>
<div id="nav">
<form method="post">
  <input type="text-box" id="setpoint" name="setpoint" placeholder="Enter Setpoint">
  <div id="slider"></div>
  <input type="text-box"  id="fan"  name="fan" placeholder="Enter Fan">
  <div id="slider1"></div>
  Iteration Rate:<select id="itr">
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
<h2 id="temp"></h2>

    
</div>
    <div id="one">
        <p>Write your scilab code here          <button id="save">SAVE</button></p> 
         
<div id="editor"></div>
    

   
    <div id="form2">
        <form method="post" id="formfile"  action="fileupload"enctype="multipart/form-data">

  <input type="file"  id="file" name="file" placeholder="Make it easy..">
  <input type="submit" id= "upload" value="Upload" >
 
</form>     
    </div>
         <div>
         <h4 id="error">errors</h4>        
    <textarea  id="section2">
    </textarea>
         </div>  
  </div>  
   
    
    
    <div id="part" >
        
        <div id="charttemp" style="width: 400px;float:left ;height: 300px; margin: 0 auto"></div>
        <div id="chartheat" style="width: 400px;float:left ; height: 300px; margin: 0 auto"></div>
         
    
    </div>   

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
        
        <script>
           
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
                $("#start").prop('disabled',true);
                $("#pause").prop('disabled',true);
                var wnd=window.open("close.jsp","connectWindow", "width=100,height=100,menubar=no");
                //setTimeout(function(){wnd.close();},100);
                
                alert('ur session is dismissed');
        });
       
        </script>
        
    </body>
</html>


