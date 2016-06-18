import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * 
 * @author Anamika Modi
 */
public class Con{
    ArrayList<Double> arr;
    public Con(){
        super();
        this.arr = new ArrayList<Double>();
    }
 void connect ( String portName,int heat,int fan ) throws Exception
    { 
    	   
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
    
    
    	
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("Error: Port is currently in use");
        }
        else
        {
            CommPort commPort;
            commPort = portIdentifier.open(this.getClass().getName(),2000);
            
            if ( commPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();
                
                out.write(253);
                out.write(fan);
                out.write(254);
                out.write(heat);
                int count=0;
                while(count<3){
                Thread.sleep(10000);
               // out.flush();
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
                	Double a= Integer.parseInt(String.valueOf(buffer[0]))+0.1*(Integer.parseInt(String.valueOf(buffer[1])));
                        arr.add(a);
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
}