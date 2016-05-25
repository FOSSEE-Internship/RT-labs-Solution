<%-- 
    Document   : vlab1
    Created on : 23 May, 2016, 2:33:43 PM
    Author     : root
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
        <title>V_LAB</title>
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
   function start(){
        var eventSource = new EventSource("sbhs1");
          var heat= $("#heat").val();
          var fan = $("#fan").val();
          $.ajax({
                url: "sbhs1",
                type: 'GET',   
                dataType: 'text',
                data: {
                    mypostvar: fan,
                    mypostvar1: heat
                },
                
                success: function (data) {
        if(data){
            if(data.split(":")[1]!==0.0)
                   document.getElementById('msg').innerHTML=data.split(":")[1];
               }
                }
                
            });    
      
       
    }
   
            
</script>
    </head>
    <body>
         <h1>VIRTUAL LABS </h1>
         <form method="post">
             HEAT:<input name="heat" id ="heat"><div id="slider"></div>
             
              FAN:  <input name="fan" id="fan"> 
                <div id="slider1"></div>
         </form>
         
        <h2 id="msg" border="2"></h2>
         

<script>
  $(function() {
    $( "#slider" ).slider({
     orientation: "horizontal",
    max: 100,
    step: 1,
    change: function(event, ui) {
      $('#heat').attr('value', ui.value);
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
                  document.getElementById("heat").innerHTML= ui.value;
                  
              });
              // document.getElementById("msg").innerHTML=document.getElementById("msg").value;
           $( "#slider1" ).on( "slidechange", function( event,ui) {
               
                  document.getElementById("fan").innerHTML= ui.value;
                  
              });
             
        });
        
        </script>
        
        <script>
            setInterval(start,2500);
        </script>
        
        
    </body>
</html>
