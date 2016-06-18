<%--
    Document   : logout
    Created on : 1 Jun, 2016, 5:57:28 PM
    Author     : Anamika Modi
--%>
<%--
This is the main experiment window.
Involving all the redirection from the page.
--%>
<%@page import="bean.user"%>
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


<head>
<link rel="stylesheet" href="http://dodsoftware.com/sotests/fancy/jquery.fancybox-1.3.4.css" type="text/css" media="screen" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
<script type="text/javascript" src="http://dodsoftware.com/sotests/fancy/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript" src="http://dodsoftware.com/sotests/fancy/jquery.easing-1.3.pack.js"></script>
<script type="text/javascript" src="http://dodsoftware.com/sotests/fancy/jquery.mousewheel-3.0.4.pack.js"></script>
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
    <!--
    This inline css is for setting
    the sliders.
    -->
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
       var myID=0;
       /*
        * chart1 for temp vs time
        * chart2 for heat vs time
        */
       var chart1;
       var chart2;
       var text="";

function bindDOMEvents() {
/*
* unload works on both closing tab and on refreshing tab.
*/
$(window).on('beforeunload', function() {
    var data=sessionStorage.getItem('myUnloadEventFlag');
    if(data==='true'){
        /*
         * this popup is for closing all the resources .
         */
           var wnd=window.open("finish.jsp","MsgWindow", "width=100,height=100");
           /*
            * resetting the sessionStorage object .This sessionStorage object
            * is used for differing between closing , refreshing
            * and redirection of pages.
            */
            window.sessionStorage['myUnloadEventFlag']='false';
            return 'bye bye!';
        }
    });

// Attach the event keypress to exclude the F5 refresh
$(document).keydown(function(e)
{
    /*
     * sessionStorage is set to false on
     * pressing F5 , so that resources remain
     * intact.
     */
   var key=e.which || e.keyCode;
   if(key===116)
    window.sessionStorage['myUnloadEventFlag']='false';
});
 $("input[type=submit]").bind("click", function()
{
  /*
   * sessionStorage is set to false on redirection after submit
   * button , so that resources are intact.
   */
  window.sessionStorage['myUnloadEventFlag']='false';
});

}

// Wire up the events as soon as the DOM tree is ready
$(document).ready(function() {
    bindDOMEvents();
     if(sessionStorage.getItem('myUnloadEventFlag')==='true')
     {
         /*
          * reintialise the resources.
          */
          window.location.href="initres.jsp";
     }
        // flag the page as being unloading
        window.sessionStorage['myUnloadEventFlag']='true';
    /*
     * @editor - editor window
     */
    var editor = ace.edit("editor");
    editor.setTheme("ace/theme/monokai");
    editor.getSession().setMode("ace/mode/javascript");
    colArray=[];
    /*
     * here the uploaded code is taken
     * from the session attribute code
     * which is set when the upload button is clicked
     * via the fileupload servlet.
     */
      <%
          String s[]={"write ur scilab code here"};
          if(session.getAttribute("code")!=null)
         s=((String)session.getAttribute("code")).split("~");
      %>
     /*
      * the editor window value is set.
      */
      <% for (int i=0; i<s.length; i++) { %>
colArray[<%= i %>] = "<%= s[i] %>";
editor.setValue(editor.getValue()+colArray[<%=i%>]+'\n',1);
<% } %>

    /*
     * Save button on the window when clicked,
     * ajax request is transferred to savefile servlet.
     * Here
     * @param mypostvar- has the code .
     * @param mypostvar1- has filename(which the user specifies)
     */
     $("#save").on("click",function(){
       text=editor.getValue();
       window.sessionStorage['myUnloadEventFlag']='false';
       var name =$("#filename").val();

               $.ajax({
                url: "savefile",
                type: 'GET',
                dataType: 'text',
                data: {
                    mypostvar: text,
                    mypostvar1 :name
            },

                success: function (data) {
                   // alert('yay');
                   /*
                    * on success the page is reloaded.
                    */
                    window.location.href="sbhs.jsp";
                    window.sessionStorage['myUnloadEventFlag']='false';
                }
                });

      });
  /*
   * chart for temp vs time
   */

        chart1 = new Highcharts.Chart({
        chart: {
            renderTo: 'charttemp',
            defaultSeriesType: 'spline',
            events: {
               load:function() {
            chart1 = this; // `this` is the reference to the chart
            setTimeout(start,50000);
            }
          }
        },
        title: {
            text: 'Temp vs Time'
        },
        xAxis: {
           /*
            * time axis
            * set according to datetime
            */
             type: 'datetime',
            tickPixelInterval: 150,
            title: {
                text: 'Time',
                margin: 40
            },
            maxZoom: 20 * 1000
        },
        yAxis: {
            /*
             * temp. axis
             * plots the double value of temp
             */
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
    /*
   * chart for heat vs time
   */
    chart2 = new Highcharts.Chart({
        chart: {
            renderTo: 'chartheat',
            defaultSeriesType: 'spline',
            events: {
               load:function() {
    chart2 = this; // `this` is the reference to the chart
   setTimeout(start,50000);
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
            /*
            * time axis
            * set according to datetime
            */
             type: 'datetime',
            tickPixelInterval: 100,
            title: {
                text: 'Time',
                margin: 40
            },
            maxZoom: 20 * 100
        },
        yAxis: {
            /*
             * heat axis
             * plots the  value of heat obtained from sbhs
             */
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
/*
 * This function is called at every iteration.
 * It sends a request to the servlet thru ajax , with the data .
 * Servlet here is sbhs_send.(in default package)
 * On success of the request sent ,
 * various events can happen-
 * 1. The experiment is finished.In which case the data that is returned
 * from the servlet has "done" appended to it .
 * 2.The scilab code has errors.In that case the servlet appends "error"
 * to the returned data.
 * 3.The experiment is running , where the servlet returns all the outputs by appending
 * it to the returned data.
 * Here the DELIMITER used in data is "::" and "&";
 * "::" - for data information
 * "&" - for input variables
 * @param setpoint - value from browser for setpoint
 * @param fan - value form browser for fan
 * @param data- the data that is received form servelt after the
 *              ajax request is successful.
 *
 *
 */
   function start(){

        var eventSource = new EventSource("sbhs_send");
        var setpoint= $("#setpoint").val();
        var fan = $("#fan").val();
        window.sessionStorage['myUnloadEventFlag']='false';

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
            /*
             * experiment is finished
             */
            if(data.split("::")[2]==="done"){
                /*
                 * this popup closes all the open resources, thus indicating
                 * the end of the session of the user (i.e. 55 mins here)
                 */
                var wnd=window.open("finish.jsp","MsgWindow", "width=100,height=100");
                //window.location.href="reload.jsp";
                document.getElementById('temp').innerHTML="experiment finished";
                /*
                 * to prevent further calling of the start method
                 */
                clearInterval(myID);
            }
            /*
             * scilab code has error
             */
            else if(data.split("::")[2]==="error"){
                /*
                 * for printing error in the section2
                 * which is the id for the error textarea on the
                 * experiment window.
                 */
                document.getElementById('section2').innerHTML=data.split("::")[1];
                /*
                 * to stop call to start method
                 */
                clearInterval(myID);
                /*
                 * this popup will close the current scilab instance
                 * and start a new instance.
                 */
                var wnd=window.open("close.jsp","MsgWindow", "width=100,height=100");
            }
            /*
             * experiment is running ...
             */
            else{
                /*
                 * display temp .
                 * structure for data-
                 * data::output&output&...::info.
                 */
                   document.getElementById('temp').innerHTML=data.split("::")[1].split("&")[0];
                   /*
                    * this is done to set unloadflag to true ,indicating presence on
                    * sbhs.jsp
                    */
                   window.sessionStorage['myUnloadEventFlag']='true';
                   var series = chart1.series[0],
                   shift = series.data.length > 50;
                   /*
                    * @param point - temp
                    * @param point1-heat
                    * @param point2 - fan
                    */
                    point=data.split("::")[1].split("&")[0];
                    point1=data.split("::")[1].split("&")[1];
                    point2=data.split("::")[1].split("&")[2];
                    /*
                     * adding point to the charts for live plotting.
                     * Chart used are HIGHCHARTS.
                     */
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
<h1> Experiment </h1>
<!--
This browser window is divided into 3 divs.
1.nav , containing the input and output area ana the buttons.
2.one , containing the editor window and the error text-area.
3.part , containing area for charts.currently there are 2 charts.
each of 400 X 600 px
-->
<div id="nav">
<form method="post">
    Setpoint:<input type="label" id="setpoint" name="setpoint" placeholder="Enter Setpoint">
  <div id="slider"></div>
  Fan:<input type="label"  id="fan"  name="fan" placeholder="Enter Fan">
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
 <!--
 This heading for displaying output.
 -->
<h2 id="temp"></h2>
<!--
 Start button is for initiating the function.
 -->
<button id="start">START</button>
<!--
 Stop button is for stopping the current experiment 
and starting a new experiment.
 -->
<button id="stop"onclick="stop()">STOP</button>
<!--
 Pause button is just stopping the current experiment for some time
 -->
<button id="pause">PAUSE</button>
</div>
<div id="one">
   <!--
 Save button
 -->
    <div id="popupdiv">
        <input id="filename" type="text" placeholder="write your file name here" ></input> <button id="save">Save</button></a>
    </div>
<div id="editor"></div>
    <div id="form2">
 <!--
 Upload button
 -->
  <form method="post" id="formfile"  action="fileupload" enctype="multipart/form-data">
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
 <!--
 chart area
 -->
 <div id="part" >
        <div id="charttemp" style="width: 400px;float:left ;height: 300px; margin: 0 auto"></div>
        <div id="chartheat" style="width: 400px;float:left ; height: 300px; margin: 0 auto"></div>
 </div>
 <!--
 this script is for initiating the 
 sliders and setting there range 
 @id slider - slider for setpoint
 @id slider1 - slider for fan
 -->
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
 <!--
 for updating the input text-box assigned to each slider
 slider - has text-box with id setpoint allotted
 slider1 -has text-box with id fan assigned to it.
 -->
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
 <!--
 Functionality of all the buttons
 -->
<script>
        $("#start").on("click",function(){
            /*
             * to prevent unloading on redirection
             */
            window.sessionStorage['myUnloadEventFlag']='false';
            /*
             * start the call of method start at the user-defined iteration rate
             */
            if($("#itr").val()*1000!==0){
            myID=setInterval(start,($("#itr").val()*1000));
            }
        });
        $("#pause").on("click",function(){
            /*
             * pausing the activity
             */
               clearInterval(myID);
        });
         $("#stop").on("click",function(){
             clearInterval(myID);
             /*
              * This pop-up will create a new log file indicating the 
              * start of a new experiment
              */
             var wnd=window.open("createnewlog.jsp","connectWindow", "width=100,height=100,menubar=no");
             window.sessionStorage['myUnloadEventFlag']='false';
             window.location.href="sbhs.jsp";
         });
</script>
</body>
</html>


