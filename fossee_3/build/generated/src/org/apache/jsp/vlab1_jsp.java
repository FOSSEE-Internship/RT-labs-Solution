package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.lang.String;
import java.lang.String;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.File;

public final class vlab1_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <script src=\"http://code.jquery.com/jquery-latest.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <title>V_LAB</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("          <link rel=\"stylesheet\" href=\"http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css\">\n");
      out.write("  <script src=\"http://code.jquery.com/jquery-1.10.2.js\"></script>\n");
      out.write("  <script src=\"http://code.jquery.com/ui/1.11.4/jquery-ui.js\"></script>\n");
      out.write("  \n");
      out.write("  \n");
      out.write("  <style type=\"text/css\">\n");
      out.write("      \n");
      out.write("    .ui-slider .ui-slider-handle {\n");
      out.write("    height: 15px;\n");
      out.write("    width: 5px;\n");
      out.write("    padding-left: 10px;\n");
      out.write("    \n");
      out.write("}\n");
      out.write("    .ui-slider-horizontal {\n");
      out.write("    height: 8px;\n");
      out.write("    width: 200px;\n");
      out.write("}\n");
      out.write("  </style>\n");
      out.write("  ");

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
      
      out.write("\n");
      out.write("  <script>\n");
      out.write("   function start(){\n");
      out.write("        var eventSource = new EventSource(\"sbhs1\");\n");
      out.write("          info=[];\n");
      out.write("          for( var i=0;i< ");
      out.print(no_of_inputs);
      out.write(";i++){\n");
      out.write("              info[i]=$('#textBox_'+i).val();\n");
      out.write("                \n");
      out.write("          }\n");
      out.write("           var data = '[' + info + ']';\n");
      out.write("          console.log(info);\n");
      out.write("          $.ajax({\n");
      out.write("                url: \"sbhs1\",\n");
      out.write("                type: 'GET',   \n");
      out.write("                data: {\n");
      out.write("                   info:data\n");
      out.write("                },\n");
      out.write("                \n");
      out.write("                success: function (data) {\n");
      out.write("        if(data){\n");
      out.write("            if(data.split(\":\")[0]!==0.0){\n");
      out.write("                for(var i=0;i< ");
      out.print(no_of_outputs);
      out.write(";i++)\n");
      out.write("                  document.getElementById('heading_'+i).innerHTML=data.split(\":\")[i];\n");
      out.write("               }\n");
      out.write("               }\n");
      out.write("                }\n");
      out.write("                \n");
      out.write("            });    \n");
      out.write("      \n");
      out.write("       \n");
      out.write("    }\n");
      out.write("    \n");
      out.write("    \n");
      out.write("  function textBox(selections){\n");
      out.write("  colArray=[];\n");
      out.write("     ");
 for (int i=0; i<arrin.length; i++) { 
      out.write("\n");
      out.write("colArray[");
      out.print( i );
      out.write("] = \"");
      out.print( arrin[i] );
      out.write("\";\n");
 } 
      out.write(" ;\n");
      out.write("    selections = selections*1; // Convert to int\n");
      out.write("    if( selections !== selections ) throw 'Invalid argument'; // Check NaN\n");
      out.write("    var container = document.getElementById('yay'); //Cache container.\n");
      out.write("\n");
      out.write("    for(var i = 0; i <selections; i++){\n");
      out.write("        var tb = document.createElement('input');\n");
      out.write("        var div = document.createElement('div');\n");
      out.write("        tb.type = 'text';\n");
      out.write("        tb.id = 'textBox_' + i; // Set id based on \"i\" value\n");
      out.write("        div.id='slider_'+i;\n");
      out.write("         container.innerHTML+='<p>';\n");
      out.write("         container.innerHTML+=colArray[i]+': ';\n");
      out.write("        container.appendChild(tb); \n");
      out.write("         container.innerHTML+='</p>';\n");
      out.write("       // container.appendChild(div);\n");
      out.write("        \n");
      out.write("      \n");
      out.write("    }\n");
      out.write("} \n");
      out.write("\n");
      out.write("\n");
      out.write("    \n");
      out.write("  function headings(selections){\n");
      out.write("      coloutArray=[];\n");
      out.write("     ");
 for (int i=0; i<arrout.length; i++) { 
      out.write("\n");
      out.write("coloutArray[");
      out.print( i );
      out.write("] = \"");
      out.print( arrout[i] );
      out.write("\";\n");
      out.write("\n");
 } 
      out.write(";\n");
      out.write("    selections = selections*1; // Convert to int\n");
      out.write("    \n");
      out.write("    if( selections !== selections ) throw 'Invalid argument'; // Check NaN\n");
      out.write("    var container = document.getElementById('output'); //Cache container.\n");
      out.write("\n");
      out.write("    for(var i = 0; i <selections; i++){\n");
      out.write("        var h = document.createElement('h2');\n");
      out.write("        h.id = 'heading_' + i; // Set id based on \"i\" value\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        \n");
      out.write("        container.innerHTML+=coloutArray[i]+': ';\n");
      out.write("        container.appendChild(h);\n");
      out.write("        \n");
      out.write("      \n");
      out.write("    }\n");
      out.write("}\n");
      out.write("function slider(selections) { \n");
      out.write("     selections = selections*1; \n");
      out.write("   for(var i = 0; i <selections; i++){  \n");
      out.write("    $( \"#slider_\"+i).slider({\n");
      out.write("       \n");
      out.write("     orientation: \"horizontal\",\n");
      out.write("    max: 100,\n");
      out.write("    step: 1,\n");
      out.write("    change: function(event, ui) {\n");
      out.write("      $('#textBox_'+i).attr('value', ui.value);\n");
      out.write("    },\n");
      out.write("    min: 0\n");
      out.write("    });\n");
      out.write("   }\n");
      out.write("    \n");
      out.write("  };\n");
      out.write("  function change(e){\n");
      out.write("      if (e.target !== e.currentTarget) {\n");
      out.write("        var clickedItem = e.target.id;\n");
      out.write("        alert(\"Hello \" + clickedItem);\n");
      out.write("    }\n");
      out.write("   \n");
      out.write("    \n");
      out.write("  };\n");
      out.write("  $(document).ready(function(){ \n");
      out.write("               \n");
      out.write("               slider(");
      out.print(no_of_inputs);
      out.write(");\n");
      out.write("              change(");
      out.print(no_of_inputs);
      out.write(");\n");
      out.write("          \n");
      out.write("                  \n");
      out.write("              });\n");
      out.write("</script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("         <h1>VIRTUAL LABS </h1>\n");
      out.write("        <form method=\"post\">\n");
      out.write("         <div id=\"yay\">\n");
      out.write("         \n");
      out.write("             \n");
      out.write("             <script>textBox(");
      out.print(no_of_inputs);
      out.write(");</script>\n");
      out.write("                     \n");
      out.write("         </div>\n");
      out.write("    </form>\n");
      out.write("             <div id='output'>\n");
      out.write("                 <script>headings(");
      out.print(no_of_outputs);
      out.write(");</script>\n");
      out.write("             </div>\n");
      out.write("       \n");
      out.write("         \n");
      out.write("\n");
      out.write("<script>\n");
      out.write("  \n");
      out.write("  </script>\n");
      out.write("         <script type=\"text/javascript\">\n");
      out.write("            \n");
      out.write("            var container = document.getElementById('yay');\n");
      out.write("            container.addEventListener(\"slideChange\",change,false);\n");
      out.write("             \n");
      out.write("       \n");
      out.write("        \n");
      out.write("        </script>\n");
      out.write("        \n");
      out.write("        <script>\n");
      out.write("            setInterval(start,6000);\n");
      out.write("        </script>\n");
      out.write("        \n");
      out.write("        \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
