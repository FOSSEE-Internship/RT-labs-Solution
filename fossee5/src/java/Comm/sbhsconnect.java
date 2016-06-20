
package Comm;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;

/**
 * This class is for making initial connections with the board 
 * when the server is turned on.
 * 
 * @author Anamika Modi
 */
public class sbhsconnect {
    /*
    here the connection is made via portName , where
    a connection is setup by using open method of the portIdentifier
    Retuens a Serial Port 
    */
    public SerialPort connect ( String portName ) throws PortInUseException, UnsupportedCommOperationException, IOException 
    { 
         SerialPort serialPort=null;
         CommPort commPort=null;
         CommPortIdentifier portIdentifier=null;
        try {
            portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        } catch (NoSuchPortException ex) {
            return null;
        }
    Double a=0.0;
    
    	
       if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("Error: Port is currently in use"+portIdentifier.getCurrentOwner());
           
            return null;
        }
        else
       {
            commPort = portIdentifier.open(this.getClass().getName(),2000);  
            if ( commPort instanceof SerialPort )
            {
               serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                
               
            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }     
      return serialPort;
    }
}
