
package Scilab;

import java.io.File;
import java.io.FileNotFoundException;
import org.scilab.modules.javasci.JavasciException;
import org.scilab.modules.javasci.Scilab;
import org.scilab.modules.types.ScilabDouble;
import org.scilab.modules.types.ScilabInteger;
import org.scilab.modules.types.ScilabSparse;


/**
 * This class is where the user written scilab code
 * is executed the results returned. We have used 
 * javasci library here. Javasci library makes use 
 * of the fact that Scilab is interpolable .
 * So all the calls to the functions that take place in scilab can be called via 
 * java . Basically , its like a scilab engine.
 * 
 * @author Anamika Modi
 */
public class CallScilab {
    public String[] scilab(int setpoint,int fan,double temp ,Scilab sci,String filename) throws JavasciException.InitializationException, JavasciException, FileNotFoundException{
		/*
                Here is the String array , which will contain results to be returned
                */
                String[] b=new String[3];
		try{
                System.out.println(filename+"Scilab");
                /*
                * This object Scilab sci , will be used to communicate to scilab.
                * open(File name) - basically launches the script in scilab
                */
		sci.open(new File("/home/anamika/scilabcodes/"+filename));
                }
               catch(JavasciException.AlreadyRunningException e){
               }
               finally{
                    /*
                    Here since the nature of the input is known 
                    Scilab Integer is used for setpoint and fan 
                    And ScilabDouble for temp.
                    */
		ScilabInteger scilab_setpoint= new ScilabInteger(setpoint);
		ScilabInteger scilab_fan= new ScilabInteger(fan);
                ScilabDouble scilab_temp= new ScilabDouble(temp);
               // sci.exec("disp('hulla re')");
                /*
                Thsi is done so that the scilab variables declared here,
                become accesible to scilab .
                */
		sci.put("setpoint",scilab_setpoint);
		sci.put("fan",scilab_fan);
                sci.put("temp",scilab_temp);
                try{
                    /*
                    This is where the function is called.
                    [x y] is output of the function.
                    The Scilab output is ScilabIntegers .
                    */
		sci.execException("[x y]=sbhs(setpoint,fan,temp)");
                }
                catch (org.scilab.modules.javasci.JavasciException e) {
                    /*
                    if error in scilab code occurs , heat and fan are set to -1 and an error message is returned.
                    */
                System.err.println("An exception occurred: " + e.getLocalizedMessage());
                b[1]=String.valueOf(-1);
                b[0]=String.valueOf(-1);
                b[2]=e.getLocalizedMessage();
                return b;
}
                /*
                Without errors, The returned object has fan heat  and  a msg
                */
		ScilabInteger c=(ScilabInteger) sci.get("y");
                ScilabInteger d=(ScilabInteger) sci.get("x");
                   b[0]=String.valueOf(c.getIntElement(0,0));
                   b[1]=String.valueOf(d.getIntElement(0,0));
                   b[2]=null;
                  // sci.close();
                }
               
         return b;       
    }
}
