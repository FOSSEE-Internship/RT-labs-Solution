<%-- 
    Document   : vlab1
    Created on : 23 May, 2016, 2:33:43 PM
    Author     : Master
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
    </head>
    <body>
         <h1>VIRTUAL LABS </h1>
                <form method="post" action="sbhs1">
                    <p>"Enter the parameters for the experiment :</p>
                    <p>Heat:<input type="text" name="heat" value="" placeholder="0-100" ></p>
                    <p>Fan:<input type="text" name="fan" value="" placeholder="0-100"  ></p>
                    <p><input type="submit" name="commit" value="GO" id="button" ></p>
                </form>
         <h2 id="msg"></h2>
         <script type="text/javascript">
           $(document).ready(function(){ 
               $("#button").on("click",function(event){
                document.getElementById("button").style.display='none'; 
                document.getElementById("msg").innerHTML="wait for some time experiment is running .... ";
            });
        });
            
        </script>
        
        
    </body>
</html>