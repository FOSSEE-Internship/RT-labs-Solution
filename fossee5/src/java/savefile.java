

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet is for saving the uploaded or pasted code 
 * in the editor to the server.
 * Currently the directory is - /home/anamika/scilabcodes/
 * the name of the file is given by the user.
 * This servlet is called via an ajax call , where 
 * mypostvar1 : name of the file
 * mypostvar : code
 * @attribute code(session)- scilab code
 * @attribute filename(session) - name of the file which has the code
 * @author Anamika Modi
 */
public class savefile extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            File f = new File("/home/anamika/scilabcodes/"+request.getParameter("mypostvar1"));
            
            request.getSession().setAttribute("filename",request.getParameter("mypostvar1"));
            if(!f.exists()) {
                f.createNewFile();
             } 
            /*
            here the code is written to the file created.
            */
           FileWriter fw = new FileWriter(f.getAbsoluteFile());
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                    String code=request.getParameter("mypostvar");
                    bw.flush();
                    bw.append(code);
                    /*
                    the code attribute of the session is set to the code with an added 
                    delimiter "~" .This delimiter is to avoid string parsing errors in 
                    javascript , which could occur when the code is sent without delimiters to 
                    the editor in the experiment window.
                    */
                String splitcode[]= code.split("\n");
                code="";
                for(int i=0;i<splitcode.length;i++)
                    code+=splitcode[i]+"~";
                request.getSession(false).setAttribute("code",code);
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
