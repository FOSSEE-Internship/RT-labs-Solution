/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.scilab.modules.javasci.JavasciException;
import org.scilab.modules.javasci.Scilab;
import org.scilab.modules.types.ScilabInteger;

/**
 *
 * @author root
 */
public class ser1 extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            try {
		Integer d1=Integer.parseInt(request.getParameter("no3"));
                System.out.println(d1);
		Scilab sci=(Scilab) request.getSession().getAttribute("scilab");
                if(sci==null){
                     sci = new Scilab();
                     sci.open(new File("/home/anamika/ex1.sce"));
                }
		
		System.out.println("YYAAAAYYY");
		ScilabInteger s= new ScilabInteger(d1);
		sci.put("a",s);
		
		sci.exec("y=memory(a)");
                
		ScilabInteger c=(ScilabInteger)(sci.get("y"));
                ScilabInteger d=(ScilabInteger)(sci.get("var"));
		sci.close();
		System.out.println(c.getElement(0,0)+" "+d.getElement(0,0));
		
	} catch (JavasciException.InitializationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JavasciException e) {
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
