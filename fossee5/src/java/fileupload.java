/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *  This servlet is for uploading the 
 * scilab code user wants to use for his/her experiment.
 * 
 * 
 * @author Anamika Modi
 */
@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)
public class fileupload extends HttpServlet {
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
            InputStream inputStream = null;	// input stream of the upload file
            // obtains the upload file part in this multipart request
            Part filePart = request.getPart("file");
            
           if (filePart != null) {
                // prints out some information for debugging
                System.out.println(filePart.getName());
                System.out.println(filePart.getSize());
                System.out.println(filePart.getContentType());

                // obtains input stream of the upload file
                inputStream = filePart.getInputStream();
            }
           
        String contentDisp = filePart.getHeader("content-disposition");
        /*
        gets the name of the file uploaded
        */
        System.out.println(contentDisp.split(";")[2].split("=")[1]);
        /*
        to read line by line from the uploaded file
        */
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	    StringBuilder s = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        s.append(line+"~"); 
	    }
            /*
            the uploaded file text is set as an attribute for the session object
            called "code"
            */
            request.getSession(false).setAttribute("code",s.toString());
	    reader.close();
            getServletContext().getRequestDispatcher("/sbhs.jsp").forward(request, response);
        
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
