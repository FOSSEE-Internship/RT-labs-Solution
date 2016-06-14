/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anamika Modi
 */
public class download extends HttpServlet {

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
        HttpSession session=request.getSession(false);
        OutputStream outStream = response.getOutputStream();
       
         
        // if you want to use a relative path to context root:
        String relativePath = getServletContext().getRealPath("");
        System.out.println("relativePath = " + relativePath);
          String name =(String)session.getAttribute("book") ;
          
          String filePath = relativePath+"\\js\\books\\"+name+".txt";
        File downloadFile = new File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);
        // obtains ServletContext
        ServletContext context = getServletContext();
         
        // gets MIME type of the file
        String mimeType ="text/plain";
                /* context.getMimeType(filePath);
        System.out.println(mimeType);
        if (mimeType == null) {        
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);*/
         
        // modifies response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
         
        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
         
        // obtains response's output stream
       // OutputStream outStream = response.getOutputStream();
         
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
         
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        
         
        inStream.close();
        outStream.close(); 
      /*  response.setContentType("text/html;charset=UTF-8");
       // PrintWriter out = response.getWriter();
        
            /* TODO output your unpage here. You may use following sample code. */
           
           /* HttpSession session=request.getSession(false);
           
                database db=new database();
              
           String name =(String)session.getAttribute("book") ;
           //db.searchbook(name);
		
               OutputStream outStream = response.getOutputStream();
		              System.out.println("run");
		byte[] buffer = new byte[65536];
              
		
		
		
                
		// if you want to use a relative path to context root:
		String relativePath = getServletContext().getRealPath("");
		System.out.println("relativePath = " + relativePath);
               String filepath= relativePath+"\\js\\books\\name";
               File file=new File(filepath);
		
		// obtains ServletContext
		ServletContext context = getServletContext();
		
		String mimeType="text/plain";
                System.out.println("run1");
           // String fileType = fileName.substring(fileName.indexOf(".")+1,fileNam e.length());
		/*if (fileType.trim().equalsIgnoreCase("txt"))
{
response.setContentType( "text/plain" );
}
else if (fileType.trim().equalsIgnoreCase("doc"))
{
response.setContentType( "application/msword" );
}
else if (fileType.trim().equalsIgnoreCase("xls"))
{
response.setContentType( "application/vnd.ms-excel" );
}
else if (fileType.trim().equalsIgnoreCase("pdf"))
{
response.setContentType( "application/pdf" );
}
else if (fileType.trim().equalsIgnoreCase("ppt"))
{
response.setContentType( "application/ppt" );
}
else
{
response.setContentType( "application/octet-stream" );
}*/
/*response.setContentType( mimeType );
response.setHeader("Content-Disposition","attachment; filename=\""+name+"\"");
//response.setHeader("cache-control", "no-cache");
response.setHeader("cache-control", "must-revalidate");	
			// set to binary type if MIME mapping not found
			//mimeType = "application/octet-stream";
            //String myfile;
		
		//System.out.println("MIME type: " + mimeType);
		
		// modifies response
		//response.setContentType(mimeType);
		response.setContentLength((int)file.length());
		
		// forces download
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", name);
	//response.setHeader(headerKey, headerValue);
		
		// obtains response's output stream
		 System.out.println("runnnnnnn");
		byte[] byteBuffer = new byte[4096];
    BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
int length=0;
    // reads the file's bytes and writes them to the response stream
    while ((in != null) && ((length = in.read(byteBuffer)) != -1))
    {
        outStream.write(byteBuffer,0,length);
    }

outStream.close();
		
               
                        db.point((String)session.getAttribute("points"),(String)session.getAttribute("name"));
		//inStream.close();
		outStream.close();
            }*/
        
           
       
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
