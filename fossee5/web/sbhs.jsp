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
       var myID=0;
       var chart1;
       var chart2;
       var count=0;
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
               
                document.getElementById('temp').innerHTML=data.split("::")[1];
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
        <div id="charttemp" style="width: 550px;float:left ;height: 400px; margin: 0 auto"></div>
        <div id="chartheat" style="width: 550px;float:left ; height: 400px; margin: 0 auto"></div>
    </body>
</html>


