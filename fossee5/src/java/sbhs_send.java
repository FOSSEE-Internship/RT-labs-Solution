/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Comm.Con;
import Scilab.CallScilab;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.scilab.modules.javasci.JavasciException;
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
        HttpSession session=request.getSession();
        PrintWriter writer = response.getWriter();

    try (PrintWriter out = response.getWriter()) {       
            String portName=null;
        try
        {
        	
            String setpoint = request.getParameter("mypostvar");
            String fan = request.getParameter("mypostvar1");
            if(setpoint!=null&&fan!=null){
            Integer d1=Integer.parseInt(setpoint);
            Integer d2=Integer.parseInt(fan);
             Con con=null;int check=0;  
       try{
            con = (Con) request.getSession().getAttribute("con");
            check=(Integer)request.getSession().getAttribute("check");
       }catch(NullPointerException e){
           System.out.println("session null");
       }
            double c=0.0;
            if(check==1){
                try{
                      c =con.readTemp();
                }catch(IOException e){
                    request.getSession().invalidate();
           response.sendRedirect("/fossee5/reload.jsp");
           return;
                }
                
           
            Scilab sci = (Scilab) request.getSession().getAttribute("sci");
            CallScilab callsci=new CallScilab();
            String b[]=callsci.scilab(d1, d2,c,sci);
             if(b[1].equals("-1")&&b[1].equals("-1")){
                 ((Con) session.getAttribute("con")).disconnect();
               ((Scilab) session.getAttribute("sci")).close();
               ((PrintWriter)session.getAttribute("writerfile")).close();
                session.removeAttribute("con");
                session.removeAttribute("sci");
                session.removeAttribute("check");
                session.removeAttribute("writerfile");
                session.removeAttribute("counter");
              session.invalidate();
               writer.write("data::"+b[2]+"::error");
               return;
            }
            if(Integer.parseInt(b[1])>100){
                b[1]="100";
            }
            else if(Integer.parseInt(b[1])<0){
                b[1]="0";
            }
           
            con.set(Integer.parseInt(b[1]),Integer.parseInt(b[0]));
            writer.write("data:: "+"Temp "+"- "+c+"\n\n"+"Heat"+"- "+b[1]+"\n\n");
            PrintWriter writerfile=(PrintWriter)(request.getSession().getAttribute("writerfile"));
            int count=(int)request.getSession().getAttribute("counter");
            count++;
            request.getSession().setAttribute("counter",count);
            //writerfile.println(String.format("%4s %15s %8s %8s %8s\r\n",(count+"."), System.currentTimeMillis(),b[1],b[0],c));
            writerfile.append(String.valueOf(count));
            writerfile.append(" ");
            writerfile.append(b[1]);
            writerfile.append(" ");
            writerfile.append(b[0]);
            writerfile.append(" ");
            writerfile.append(String.valueOf(c));
            writerfile.append(" ");
            writerfile.append(String.valueOf(System.currentTimeMillis()));
            writerfile.append("\n");

            System.out.println(System.currentTimeMillis()-session.getCreationTime());
            if(System.currentTimeMillis()-session.getCreationTime()>20000){
               writer.write("::"+"done");
            }
            
          }
      }
            else{
                System.out.println("null");
            }
   }
        catch ( NumberFormatException | IOException | JavasciException e )
        {
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
