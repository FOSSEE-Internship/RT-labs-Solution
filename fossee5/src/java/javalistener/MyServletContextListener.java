/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javalistener;

import Comm.sbhsconnect;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author root
 */
public class MyServletContextListener implements ServletContextListener {

  @Override
  public void contextDestroyed(ServletContextEvent e) {
    //Notification that the servlet context is about to be shut down.
      ServletContext cntxt = e.getServletContext();
      HashMap<Integer,SerialPort> hashMap=(HashMap)cntxt.getAttribute("hashMap");
      Iterator it = hashMap.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry pair = (Map.Entry)it.next();
        System.out.println(pair.getKey() + " = " + pair.getValue());
        SerialPort s=(SerialPort)(pair.getValue());
        s.close();
        System.out.println("port closed");
        it.remove(); // avoids a ConcurrentModificationException
    }
    cntxt.removeAttribute("hashMap");
      System.out.println("end");
  }

  @Override
  public void contextInitialized(ServletContextEvent e) {
    // do all the tasks that you need to perform just after the server starts
ServletContext cntxt = e.getServletContext();
    //Notification that the web application initialization process is starting
      System.out.println("started");
      File initialFile = new File("/home/anamika/mid1.txt");
    InputStream inputStream = null;
      try {
          inputStream = new FileInputStream(initialFile);
      } catch (FileNotFoundException ex) {
          Logger.getLogger(MyServletContextListener.class.getName()).log(Level.SEVERE, null, ex);
      }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	    
	    String line;
            String portName=null;
            HashMap<Integer,SerialPort> hashMap = new HashMap<>();
             String[] s=null;
             sbhsconnect con=new sbhsconnect();
      try {
          while ((line = reader.readLine()) != null) {
             s=line.split("-");
             SerialPort sp = null;
              try {
                  sp=con.connect(s[0]);
                  hashMap.put(Integer.parseInt(s[1]), sp);
                  
              } catch (PortInUseException ex) {
                  Logger.getLogger(MyServletContextListener.class.getName()).log(Level.SEVERE, null, ex);
              } catch (UnsupportedCommOperationException ex) {
                  Logger.getLogger(MyServletContextListener.class.getName()).log(Level.SEVERE, null, ex);
              }
             cntxt.setAttribute("hashMap", hashMap);
              System.out.println("here"+hashMap);
          }
          
      } catch (IOException ex) {
          Logger.getLogger(MyServletContextListener.class.getName()).log(Level.SEVERE, null, ex);
      }
           System.out.println(portName);
           
  }

    class sch implements Runnable{
      ServletContextEvent e=null;
        @Override
        public void run() {
            
            
        }
    }

}

