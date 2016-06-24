
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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
private ScheduledExecutorService scheduler;
List<Object> l=new ArrayList<Object>();
  @Override
  /*
  This method is started when server is closed .
  This method will close all the open ports 
  */
  public void contextDestroyed(ServletContextEvent e) {
    // Set<Thread> threadSet = Thread.getAllStackTraces().keySet();

    scheduler.shutdownNow();
      System.out.println(scheduler.isShutdown());
    
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
    scheduler = Executors.newSingleThreadScheduledExecutor();
     scheduler.scheduleAtFixedRate(new sch(e),2, 1500, TimeUnit.SECONDS);
       scheduler.scheduleWithFixedDelay(new ccg(e), 1507, 1500, TimeUnit.SECONDS);
      
           
  }

      class sch implements Runnable{
      ServletContextEvent e=null;
        
        sch(ServletContextEvent e){
            this.e=e;
        }
        @Override
        public void run() {
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
              } catch (UnsupportedCommOperationException ex) {
                  Logger.getLogger(MyServletContextListener.class.getName()).log(Level.SEVERE, null, ex);
              } catch (PortInUseException ex) {
                  return ;
              }
              hashMap.put(Integer.parseInt(s[1]), sp);
              
             cntxt.setAttribute("hashMap", hashMap);
              System.out.println("here"+hashMap);
          }
          
      } catch (IOException ex) {
          Logger.getLogger(MyServletContextListener.class.getName()).log(Level.SEVERE, null, ex);
      }
           System.out.println(portName);
           try {
              this.finalize();
          } catch (Throwable ex) {
              Logger.getLogger(MyServletContextListener.class.getName()).log(Level.SEVERE, null, ex);
          }
            
        }
    }
class ccg implements Runnable{
      ServletContextEvent e=null;
        
        ccg(ServletContextEvent e){
            this.e=e;
        }
        @Override
        public void run() {
              System.out.println("end");
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
 
          try {
              this.finalize();
          } catch (Throwable ex) {
              Logger.getLogger(MyServletContextListener.class.getName()).log(Level.SEVERE, null, ex);
          }
            
        }
    }
}

