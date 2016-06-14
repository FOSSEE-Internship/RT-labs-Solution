<html>
<head>
 
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta charset="UTF-8">
    
  <title>Experiment</title>
    <link rel="shortcut icon" href="http://vlabs.iitb.ac.in/sbhs/static/img/favicon.ico">
    <link rel="stylesheet" href="http://vlabs.iitb.ac.in/sbhs/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="front.css">
    <link rel="stylesheet" href="http://vlabs.iitb.ac.in/sbhs/static/css/bootstrap-responsive.min.css">
    <script src="http://vlabs.iitb.ac.in/sbhs/static/js/jquery-latest.min.js"></script>
    <script src="http://vlabs.iitb.ac.in/sbhs/static/js/bootstrap.min.js"></script>
    
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
<form action="wop.html">
  <input type="text-box" id="heat" name="heat" placeholder="Enter Heat">
  <br>
  <input type="text-box"  id="fan"  name="fan" placeholder="Enter Fan">
<!--  <input type="submit" value="Submit">
-->
</form> 
    
</div>
    <div id="one">
         <h6>Write your scilab code here</h6> 
   <!-- <textarea  id="section" rows="1" cols="1" placeholder="Enter Heat"> -->
<div id="editor"></div>
    
<script src="front.js" type="text/javascript" charset="utf-8"></script>
<script>
    var editor = ace.edit("editor");
    editor.setTheme("ace/theme/monokai");
    editor.getSession().setMode("ace/mode/javascript");
</script>
    

       
   
    
    <div id="form2">
    <form action="wop.html">
       
  <input type="text-box"  id="file" name="file" placeholder="Make it easy..">
 
  <input type="submit" value="Upload"   >
</form>     
    </div>
         <div>
         <h4 id="error"  >errors</h4>
         
    <textarea  id="section2">
    
    </textarea>
         </div>
         
         
         
         </div>  
   
    
    
    <div id="part" >
         
         
        <div id="two" class="myBox">
        
          
               
  <form action="wop.html">
  History: <input type="text"placeholder="file1" >
     <input type="text" placeholder="file2"> 
     <input type="text" placeholder="file3"> 
     <input type="text" placeholder="file4" >
     <input type="text" placeholder="file5" >
  <input type="text" name="file" placeholder="file6" >
 
  <input type="submit" value="Download"   >
  </form> 
        </div>
        
        <br>
        <br>
        <div></div>
       
        
        <div id="plot">
          <h6>plot your graph here...</h6> 
       <canvas id="myCanvas" width="300" height="150"
style="border:1px solid #737373;">
</canvas>
        </div>  
    
    </div>  
</body>
</html>
