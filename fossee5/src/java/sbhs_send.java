
import Comm.Con;
import Scilab.CallScilab;
import bean.user;
import database.database;
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
 * This servlet is responsible for interacting with the hardware and scilab.
 * mypostvar - setpoint value (From page sbhs.jsp)
 * mypotsvar1 - fan value 
 *  con - Connection object 
 *  sci-Scilab object
 *  check - connection status
 *  filename - filename
 * @author Anamika Modi
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
        try
        {	
            String setpoint = request.getParameter("mypostvar");
            String fan = request.getParameter("mypostvar1");
            if(setpoint!=null&&fan!=null){
            Integer d1=Integer.parseInt(setpoint);
            Integer d2=Integer.parseInt(fan);
            System.out.println(d1+" "+d2);
            Con con=null;
            int check=0;  
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
                        System.out.println(c);
                    }catch(IOException e){
                        request.getSession().invalidate();
                        response.sendRedirect("/fossee5/reload.jsp");
           return;
                }
             /*
              This scilab object is passed to a class called CallScilab(in scilab package).
              This class will help us communicate with scilab aand get results from scilab which are then sent
                to sbhs.
              */
           
            Scilab sci = (Scilab) request.getSession().getAttribute("sci");
            String filename=(String)request.getSession().getAttribute("filename");
            CallScilab callsci=new CallScilab();
                System.out.println(filename);
            String b[]=callsci.scilab(d1, d2,c,sci,filename);
            /*
            here the array returned contains output of the function executed in
            Scilab via the code given by the user.
            Also it contains a special msg if an error occurs.
            */
             if(b[1].equals("-1")&&b[0].equals("-1")){
               writer.write("data::"+b[2]+"::error");
               return;
            }
             /*
             This is specific to sbhs .
             For controlling the value of heat between (0-100)
             */
            if(Integer.parseInt(b[1])>100){
                b[1]="100";
            }
            else if(Integer.parseInt(b[1])<0){
                b[1]="0";
            }
           /*
            here the values returned by the user are sent to sbhs.
            */
             con.set(Integer.parseInt(b[1]),Integer.parseInt(b[0]));
           // writer.write("data:: "+"Temp "+"- "+c+"\n\n"+"Heat"+"- "+b[1]+"\n\n");
            /*
            this is where the data that is "data" for the return success call from ajax , is set.
            cuurently it has - data::temp&heat&fan
            */
           writer.write("data::"+c+"&"+b[1]+"&"+b[0]);
           /*
           the next section is for appending these results to the log file
           */
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
            database db=new database();
            long epoch=db.getepoch((user)(request.getSession().getAttribute("user")));
            System.out.println(System.currentTimeMillis()-epoch);
            /*
            if the slot time is finished , send done.
            */
            if(System.currentTimeMillis()-session.getCreationTime()>200000){
               writer.write("::"+"done");
            }
            
          }
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
