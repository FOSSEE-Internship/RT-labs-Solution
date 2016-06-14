/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scilab;

import java.io.File;
import java.io.FileNotFoundException;
import org.scilab.modules.javasci.JavasciException;
import org.scilab.modules.javasci.Scilab;
import org.scilab.modules.types.ScilabDouble;
import org.scilab.modules.types.ScilabInteger;
import org.scilab.modules.types.ScilabSparse;


/**
 *
 * @author root
 */
public class CallScilab {
    public String[] scilab(int setpoint,int fan,double temp ,Scilab sci) throws JavasciException.InitializationException, JavasciException, FileNotFoundException{
		String[] b=new String[3];
		try{
		sci.open(new File("/home/anamika/a.sci"));
                }
               catch(JavasciException.AlreadyRunningException e){
               }
               finally{
		ScilabInteger scilab_setpoint= new ScilabInteger(setpoint);
		ScilabInteger scilab_fan= new ScilabInteger(fan);
                ScilabDouble scilab_temp= new ScilabDouble(temp);
               // sci.exec("disp('hulla re')");
		sci.put("setpoint",scilab_setpoint);
		sci.put("fan",scilab_fan);
                sci.put("temp",scilab_temp);
                try{
		sci.execException("[x y]=sbhs(setpoint,fan,temp)");
                }
                catch (org.scilab.modules.javasci.JavasciException e) {
                System.err.println("An exception occurred: " + e.getLocalizedMessage());
                b[1]=String.valueOf(-1);
                b[0]=String.valueOf(-1);
                b[2]=e.getLocalizedMessage();
                return b;
}
		ScilabInteger c=(ScilabInteger) sci.get("y");
                ScilabInteger d=(ScilabInteger) sci.get("x");
                   b[0]=String.valueOf(c.getIntElement(0,0));
                   b[1]=String.valueOf(d.getIntElement(0,0));
                   b[2]=null;
                }
               
         return b;       
    }
}
