<%-- 
    Document   : vlab1
    Created on : 23 May, 2016, 2:33:43 PM
    Author     : root
--%>


<%@page import="java.lang.String"%>
<%@page import="java.lang.String"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.File"%>
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
      
    .ui-slider .ui-slider-handle {
    height: 15px;
    width: 5px;
    padding-left: 10px;
    
}
    .ui-slider-horizontal {
    height: 8px;
    width: 200px;
}
  </style>
  <%
       File f=new File("/home/anamika/test.txt");
        InputStream inputStream= new FileInputStream(f);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	   int no_of_inputs=0;
            int no_of_outputs=0;
            String arrin[]=null;
             String arrout[]=null;
	    String line;
	    while ((line = reader.readLine()) != null) {
	        String s[]=line.split(":");
                if(s[0].equals("no_of_inputs")){
                    no_of_inputs=Integer.parseInt(s[1]);
                }
                else if(s[0].equals("no_of_outputs")){
                    no_of_outputs=Integer.parseInt(s[1]);
                }
                else if(s[0].equals("name_of_input")){
                     arrin=new String[no_of_inputs];
                    for(int i=0;i<no_of_inputs;i++){
                        arrin[i]=s[1].split(";")[i];
                    }
                }
                else if(s[0].equals("name_of_output")){
                     arrout=new String[no_of_outputs];
                    for(int i=0;i<no_of_outputs;i++){
                        arrout[i]=s[1].split(";")[i];
                        System.out.print(arrout[i]);
                    }
                }
            }
      %>
  <script>
   function start(){
        var eventSource = new EventSource("sbhs1");
          info=[];
          for( var i=0;i< <%=no_of_inputs%>;i++){
              info[i]=$('#textBox_'+i).val();
                
          }
           var data = '[' + info + ']';
          console.log(info);
          $.ajax({
                url: "sbhs1",
                type: 'GET',   
                data: {
                   info:data
                },
                
                success: function (data) {
        if(data){
            if(data.split(":")[0]!==0.0){
                for(var i=0;i< <%=no_of_outputs%>;i++)
                  document.getElementById('heading_'+i).innerHTML=data.split(":")[i];
               }
               }
                }
                
            });    
      
       
    }
    
    
  function textBox(selections){
  colArray=[];
     <% for (int i=0; i<arrin.length; i++) { %>
colArray[<%= i %>] = "<%= arrin[i] %>";
<% } %> ;
    selections = selections*1; // Convert to int
    if( selections !== selections ) throw 'Invalid argument'; // Check NaN
    var container = document.getElementById('yay'); //Cache container.

    for(var i = 0; i <selections; i++){
        var tb = document.createElement('input');
        var div = document.createElement('div');
        tb.type = 'text';
        tb.id = 'textBox_' + i; // Set id based on "i" value
        div.id='slider_'+i;
         container.innerHTML+='<p>';
         container.innerHTML+=colArray[i]+': ';
        container.appendChild(tb); 
         container.innerHTML+='</p>';
       // container.appendChild(div);
        
      
    }
} 


    
  function headings(selections){
      coloutArray=[];
     <% for (int i=0; i<arrout.length; i++) { %>
coloutArray[<%= i %>] = "<%= arrout[i] %>";

<% } %>;
    selections = selections*1; // Convert to int
    
    if( selections !== selections ) throw 'Invalid argument'; // Check NaN
    var container = document.getElementById('output'); //Cache container.

    for(var i = 0; i <selections; i++){
        var h = document.createElement('h2');
        h.id = 'heading_' + i; // Set id based on "i" value
        
        
        
        container.innerHTML+=coloutArray[i]+': ';
        container.appendChild(h);
        
      
    }
}
function slider(selections) { 
     selections = selections*1; 
   for(var i = 0; i <selections; i++){  
    $( "#slider_"+i).slider({
       
     orientation: "horizontal",
    max: 100,
    step: 1,
    change: function(event, ui) {
      $('#textBox_'+i).attr('value', ui.value);
    },
    min: 0
    });
   }
    
  };
  function change(e){
      if (e.target !== e.currentTarget) {
        var clickedItem = e.target.id;
        alert("Hello " + clickedItem);
    }
   
    
  };
  $(document).ready(function(){ 
               
               slider(<%=no_of_inputs%>);
              change(<%=no_of_inputs%>);
          
                  
              });
</script>
    </head>
    <body>
         <h1>VIRTUAL LABS </h1>
        <form method="post">
         <div id="yay">
         
             
             <script>textBox(<%=no_of_inputs%>);</script>
                     
         </div>
    </form>
             <div id='output'>
                 <script>headings(<%=no_of_outputs%>);</script>
             </div>
       
         

<script>
  
  </script>
         <script type="text/javascript">
            
            var container = document.getElementById('yay');
            container.addEventListener("slideChange",change,false);
             
       
        
        </script>
        
        <script>
            setInterval(start,6000);
        </script>
        
        
    </body>
</html>
