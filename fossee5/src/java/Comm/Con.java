/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comm;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Con{
    
    InputStream in=null;
    OutputStream out=null;
    
    
    public Con(){
        super();
    }
    public int readMID() throws IOException{
        out.write(252);
        
                int a=0;
                try{
                	final byte[] buffer = new byte[2];
                	  int total = 0;
                	  int read = 0;
                	  while (total <=1 && (read = in.read(buffer, total, 2 - total)) >= 0) {
                	    total += read;
                	    out.flush();
                	  }
                        a= Integer.parseInt(String.valueOf(buffer[0]))+10*(Integer.parseInt(String.valueOf(buffer[1])));
                }
                catch(IOException e){
                	e.printStackTrace();
                }    
        return a;
    }
    
    public double  readTemp() throws IOException{
        Double a=0.0;
        
           out.write(255);
        
                
                try{
                	final byte[] buffer = new byte[2];
                	  int total = 0;
                	  int read = 0;
                	  while (total <=1 && (read = in.read(buffer, total, 2 - total)) >= 0) {
                	    total += read;
                	    out.flush();
                	  }
                        a= Integer.parseInt(String.valueOf(buffer[0]))+0.1*(Integer.parseInt(String.valueOf(buffer[1])));
                }
                catch(IOException e){
                	e.printStackTrace();
                }    
        return a;
    }
    
public int connect ( SerialPort serialPort ) throws PortInUseException, UnsupportedCommOperationException, IOException 
    { 
        serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                 in = serialPort.getInputStream();
                out = serialPort.getOutputStream();
      return 1;
    }
public void set(int heat, int fan){
        try {
            out.write(253);
            out.write(fan);
           out.write(254);
           out.write(heat);
        } catch (IOException ex) {
            Logger.getLogger(Con.class.getName()).log(Level.SEVERE, null, ex);
        } 
}
public void disconnect() throws IOException{
     in.close();
     out.flush();
     out.close();
     
     System.out.println("disconnect");
}
}
