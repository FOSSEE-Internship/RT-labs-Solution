/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anamika
 */
public class sbhs1 extends HttpServlet {
static Double a;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
        
        try {
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>VLABS</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.print(a);
            String s=request.getParameter("heat");
            String s1=request.getParameter("fan");
            String portName=null;
        try
        {
        	 try {
        		// String target1 ="sudo -i rm -f /var/lock/LCK*";
                 String target2 = "/home/anamika/test.sh";
                 
                 Runtime rt = Runtime.getRuntime();
                 //Process proc1 = rt.exec(target1);
                 Process proc2 = rt.exec(target2);
                 //proc1.waitFor();
                 proc2.waitFor();
                 String output = new String();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(proc2.getInputStream()));
                 String line = "";                       
                 while ((line = reader.readLine())!= null) {
                         output+=line;
                 }
                String[] port=output.split(" ");
                portName=port[port.length-1];
               
                
         } catch (Throwable t) {
                 t.printStackTrace();
         }
                 Con c=new Con();
            (c).connect(portName,Integer.parseInt(s),Integer.parseInt(s1));
            out.print("<p>Temp in interval of 10 secs is :</p>");
          // request.setAttribute("name", c.arr);
            for(int i=0;i<c.arr.size();i++){
                out.print("<p>"+(i+1)+": "+ c.arr.get(i)+"</p>");
                //out.println("\n");
            }
            
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
            
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
     
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        //request.getRequestDispatcher("/fosse_1/vlab1.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

