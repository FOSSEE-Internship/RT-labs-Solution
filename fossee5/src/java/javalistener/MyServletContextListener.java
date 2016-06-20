
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
 * This is a class which is initiated when application is deployed .
 * 
 * @author Anamika Modi
 */
public class MyServletContextListener implements ServletContextListener {

  @Override
  /*
  This method is started when server is closed .
  This method will close all the open ports 
  */
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
/*
  This method runs by fetching a list of all the current machines connected to the server machine.
  The respective ports are binded with the mids .
  This file is currently - /home/anamika/mid1.txt
  This file needs to be updated if any unplugging of machines takes place
  or on System reboot.
  Now this method connects every port to the server and stores the Serial port object
  thus obtained in a HashMap.
  Key- MID and value- SerialPort object
  Ths hashmap object is available throughout the application
  */
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
                  
              } catch (PortInUseException | UnsupportedCommOperationException ex) {
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

