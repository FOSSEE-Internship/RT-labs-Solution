/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
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
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Con{
    public static void main(String[] args) throws IOException {
        Con c=new Con();
    }
    
    InputStream in=null;
    OutputStream out=null;
    SerialPort serialPort=null;
    private int machineIdentificationCode;
    private HashMap<Integer,Integer> codeforoutput;
    private int baudRate;
    private HashMap<Integer,Integer> codeforinput;
    public Con() throws FileNotFoundException, IOException{
        super();
        File f=new File("/home/anamika/configcon.txt");
        InputStream inputStream= new FileInputStream(f);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	   
	    String line;
	    while ((line = reader.readLine()) != null) {
	        String s[]=line.split(":");
                if(s[0].equals("machine-id")){
                    machineIdentificationCode=Integer.parseInt(s[1].trim());
                    System.out.println(machineIdentificationCode);
                }
                else if(s[0].equals("codeforoutput")){
                    codeforoutput=new HashMap();
                    
                    String s1[]=s[1].split(";");
                    
                    for(int i=0;i<s1.length;i++){
                        s1[i]=s1[i].trim();
                        
                        String s2[]=s1[i].split("-");
                        
                        codeforoutput.put(Integer.parseInt(s2[0].trim()), Integer.parseInt(s2[1].trim()));
                        
                    }
                    for(int i=0;i<codeforoutput.size();i++){
                        System.out.println(codeforoutput.get(i));
                    }
                }
                else if(s[0].equals("codeforinput")){
                    codeforinput=new HashMap();
                    String s1[]=s[1].split(";");
                    for(int i=0;i<s1.length;i++){
                        s1[i]=s1[i].trim();
                        String s2[]=s1[i].split("-");
                        codeforinput.put(Integer.parseInt(s2[0].trim()), Integer.parseInt(s2[1].trim()));  
                    }
                    for(int i=0;i<codeforinput.size();i++){
                        System.out.println(codeforinput.get(i));
                    }
                }
                else if(s[0].equals("baudRate")){
                    baudRate=Integer.parseInt(s[1].trim());
                }
                
	    }
    }
    public int IdentifyMachine() throws IOException{
        out.write(machineIdentificationCode);
        
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
    
    public ArrayList<Double> read() throws IOException{
       ArrayList<Double> arr=new ArrayList<Double>();
       
        
           for(int i=0;i<codeforoutput.size();i++){
               out.write(codeforoutput.get(i));
                try{
                	final byte[] buffer = new byte[2];
                	  int total = 0;
                	  int read = 0;
                	  while (total <=1 && (read = in.read(buffer, total, 2 - total)) >= 0) {
                	    total += read;
                	    out.flush();
                	  }
                        arr.add(Integer.parseInt(String.valueOf(buffer[0]))+0.1*(Integer.parseInt(String.valueOf(buffer[1]))));
                }
                catch(IOException e){
                	e.printStackTrace();
                }  
           }
        return arr;
    }
    
public int connect ( String portName ) throws PortInUseException, UnsupportedCommOperationException, IOException 
    { 
    	   
        CommPortIdentifier portIdentifier=null;
        try {
            portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        } catch (NoSuchPortException ex) {
            return 0;
        }
    Double a=0.0;
    
    	
       if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("Error: Port is currently in use"+portIdentifier.getCurrentOwner());
            
            return 0;
        }
        else
        {
    CommPort commPort;
   
            
            commPort = portIdentifier.open(this.getClass().getName(),2000);  
            if ( commPort instanceof SerialPort )
            {
               serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(baudRate,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                 in = serialPort.getInputStream();
                out = serialPort.getOutputStream();
               
            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }     
      return 1;
    }
public void set(ArrayList<Integer> input){
        try {
            for(int i=0;i<codeforinput.size();i++){
            out.write(codeforinput.get(i));
            out.write(input.get(i));
            }
           
        } catch (IOException ex) {
            Logger.getLogger(Con.class.getName()).log(Level.SEVERE, null, ex);
        } 
}
public void disconnect() throws IOException{
     in.close();
     out.flush();
     out.close();
     serialPort.close();
     System.out.println("disconnect");
}
}
