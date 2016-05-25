package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

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
      out.write("  <style type=\"text/css\">\n");
      out.write("      #slider {\n");
      out.write("    margin: 10px;\n");
      out.write("}\n");
      out.write("    #slider1 {\n");
      out.write("    margin: 10px;\n");
      out.write("}\n");
      out.write("    .ui-slider .ui-slider-handle {\n");
      out.write("    height: 15px;\n");
      out.write("    width: 5px;\n");
      out.write("    padding-left: 5px;\n");
      out.write("}\n");
      out.write("    .ui-slider-horizontal {\n");
      out.write("    height: 8px;\n");
      out.write("    width: 200px;\n");
      out.write("}\n");
      out.write("  </style>\n");
      out.write("  <script>\n");
      out.write("   function start(){\n");
      out.write("        var eventSource = new EventSource(\"sbhs1\");\n");
      out.write("          var heat= $(\"#heat\").val();\n");
      out.write("          var fan = $(\"#fan\").val();\n");
      out.write("          $.ajax({\n");
      out.write("                url: \"sbhs1\",\n");
      out.write("                type: 'GET',   \n");
      out.write("                dataType: 'text',\n");
      out.write("                data: {\n");
      out.write("                    mypostvar: fan,\n");
      out.write("                    mypostvar1: heat\n");
      out.write("                },\n");
      out.write("                \n");
      out.write("                success: function (data) {\n");
      out.write("        if(data){\n");
      out.write("            if(data.split(\":\")[1]!==0.0)\n");
      out.write("                   document.getElementById('msg').innerHTML=data.split(\":\")[1];\n");
      out.write("               }\n");
      out.write("                }\n");
      out.write("                \n");
      out.write("            });    \n");
      out.write("      \n");
      out.write("       \n");
      out.write("    }\n");
      out.write("   \n");
      out.write("            \n");
      out.write("</script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("         <h1>VIRTUAL LABS </h1>\n");
      out.write("         <form method=\"post\">\n");
      out.write("             HEAT:<input name=\"heat\" id =\"heat\"><div id=\"slider\"></div>\n");
      out.write("             \n");
      out.write("              FAN:  <input name=\"fan\" id=\"fan\"> \n");
      out.write("                <div id=\"slider1\"></div>\n");
      out.write("         </form>\n");
      out.write("         \n");
      out.write("        <h2 id=\"msg\" border=\"2\"></h2>\n");
      out.write("         \n");
      out.write("\n");
      out.write("<script>\n");
      out.write("  $(function() {\n");
      out.write("    $( \"#slider\" ).slider({\n");
      out.write("     orientation: \"horizontal\",\n");
      out.write("    max: 100,\n");
      out.write("    step: 1,\n");
      out.write("    change: function(event, ui) {\n");
      out.write("      $('#heat').attr('value', ui.value);\n");
      out.write("    },\n");
      out.write("    min: 0\n");
      out.write("    });\n");
      out.write("  });\n");
      out.write("  $(function() {\n");
      out.write("    $( \"#slider1\" ).slider({\n");
      out.write("     orientation: \"horizontal\",\n");
      out.write("    max: 100,\n");
      out.write("     change: function(event, ui) {\n");
      out.write("      $('#fan').attr('value', ui.value);\n");
      out.write("    },\n");
      out.write("    min: 0\n");
      out.write("    });\n");
      out.write("    \n");
      out.write("  });\n");
      out.write("  \n");
      out.write("  </script>\n");
      out.write("         <script type=\"text/javascript\">\n");
      out.write("            \n");
      out.write("           $(document).ready(function(){ \n");
      out.write("               \n");
      out.write("              $( \"#slider\" ).on( \"slidechange\", function( event,ui) {\n");
      out.write("                  document.getElementById(\"heat\").innerHTML= ui.value;\n");
      out.write("                  \n");
      out.write("              });\n");
      out.write("              // document.getElementById(\"msg\").innerHTML=document.getElementById(\"msg\").value;\n");
      out.write("           $( \"#slider1\" ).on( \"slidechange\", function( event,ui) {\n");
      out.write("               \n");
      out.write("                  document.getElementById(\"fan\").innerHTML= ui.value;\n");
      out.write("                  \n");
      out.write("              });\n");
      out.write("             \n");
      out.write("        });\n");
      out.write("        \n");
      out.write("        </script>\n");
      out.write("        \n");
      out.write("        <script>\n");
      out.write("            setInterval(start,2500);\n");
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
