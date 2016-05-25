import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class main
{
    public main()
    {
        super();
    }
    
    
    void connect ( String portName ) throws Exception
    { 
    	Scanner sc=new Scanner(System.in);
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
    
    
    	
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("Error: Port is currently in use");
        }
        else
        {
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
            
            if ( commPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();
                int heat=sc.nextInt();
                int fan=sc.nextInt();
                out.write(253);
                out.write(heat);
                out.write(254);
                out.write(fan);
                int count=0;
                while(count<10){
                Thread.sleep(10000);
                out.flush();
                out.write(255);
                
                try{
                	final byte[] buffer = new byte[2];
                	  int total = 0;
                	  int read = 0;
                	  while (total <=1 && (read = in.read(buffer, total, 2 - total)) >= 0) {
                	    total += read;
                	    out.flush();
                	  }
                	  count++;
                	  System.out.println(Integer.parseInt(String.valueOf(buffer[0]))+0.1*(Integer.parseInt(String.valueOf(buffer[1]))));
                }
                catch(IOException e){
                	e.printStackTrace();
                }
                }
               
                in.close();
                out.close();
                serialPort.close();
            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }     
    }
    
    public static void main ( String[] args )
    {
    	String portName=null;
        try
        {
        	 try {
        		// String target1 ="sudo -i rm -f /var/lock/LCK*";
                 String target2 = "/home/anamika/test.sh";
                 
                 Runtime rt = Runtime.getRuntime();
                 //Process proc1 = rt.exec(target1);
                 Process proc2 = rt.exec(target2);
                 //proc1.waitFor();
                 proc2.waitFor();
                 String output = new String();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(proc2.getInputStream()));
                 String line = "";                       
                 while ((line = reader.readLine())!= null) {
                         output+=line;
                 }
                String[] port=output.split(" ");
                portName=port[port.length-1];
               
                
         } catch (Throwable t) {
                 t.printStackTrace();
         }
            (new main()).connect(portName);
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}