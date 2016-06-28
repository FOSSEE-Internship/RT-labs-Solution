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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author anamika
 */
public class sbhs1 extends HttpServlet {

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
            throws ServletException, IOException, InterruptedException, Exception {
       response.setContentType("text/event-stream;charset=UTF-8");
       PrintWriter out = response.getWriter();
        
       
           
            /* TODO output your page here. You may use following sample code. */
        
           
             
            String portName=null;
        
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
                     //System.out.println(portName);
               
                
         } catch (Throwable t) {
                 t.printStackTrace();
         }
               
          
             
           String info = request.getParameter("info");
           if(info!=null){
               Con c=new Con();
            c.connect(portName);
           String arr=info.substring(1,info.length()-1);
           String split[]=arr.split(",");
                 ArrayList<Integer> a=new ArrayList<Integer>();
                 for(int i=0;i<split.length;i++){
                     a.add(Integer.parseInt(split[i]));
                 }
                 c.set(a);
                   PrintWriter writer = response.getWriter();
                  ArrayList<Double> output=c.read();
                  String op="";
                  for(int i=0;i<output.size();i++){
                      op+=output.get(i)+":";
                  }
            writer.write(op+"\n\n");
            c.disconnect();
                   writer.close();
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
       
      
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(sbhs1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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
       
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(sbhs1.class.getName()).log(Level.SEVERE, null, ex);
        }
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

