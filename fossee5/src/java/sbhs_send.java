/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Comm.Con;
import Scilab.CallScilab;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.scilab.modules.javasci.Scilab;

/**
 *
 * @author root
 */
public class sbhs_send extends HttpServlet {

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
       response.setContentType("text/event-stream;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {       
            String portName=null;
        try
        {
        	try{
                 String target1 = "/home/anamika/test.sh";
                 Runtime rt = Runtime.getRuntime();
                 Process proc1 = rt.exec(target1);
                 proc1.waitFor();
                 String output = new String();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(proc1.getInputStream()));
                 String line = "";                       
                 while ((line = reader.readLine())!= null) {
                         output+=line;
                 }
                String[] port=output.split(" ");
                portName=port[port.length-1];
                  //  System.out.println(portName);
         } catch (IOException | InterruptedException t) {
                 t.printStackTrace();
         }
           // String setpoint = request.getParameter("mypostvar");
            //String fan = request.getParameter("mypostvar1");
            //Integer d1=Integer.parseInt(setpoint);
            //Integer d2=Integer.parseInt(fan);
            Con con= new Con();
            int check=con.connect(portName);
            if(check==1){
            double c=con.readTemp();
            Scilab sci = (Scilab) request.getSession().getAttribute("sci");
            CallScilab callsci=new CallScilab();
            //callsci.scilab(d1, d2,c,sci);
            con.disconnect();
             PrintWriter writer = response.getWriter();
            writer.write("data: "+c+"\n\n");
            }
           
           // Thread.sleep(1000);
          }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
            
            
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
