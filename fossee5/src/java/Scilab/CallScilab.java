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
    public int[] scilab(int setpoint,int fan,double temp ,Scilab sci) throws JavasciException.InitializationException, JavasciException, FileNotFoundException{
		int[] b=new int[2];
		try{
		sci.open(new File("/home/anamika/prop.sci"));
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
		//sci.exec("[x y]=sbhs(setpoint,fan,temp)");
                //sci.exec("ans=[x y]");
                
                sci.exec("y=sbhs(setpoint,fan,temp)");
		ScilabInteger c=(ScilabInteger) sci.get("y");
                   b[0]=scilab_fan.getIntElement(0,0);
                   b[1]=c.getIntElement(0, 0);
                   
                }
         return b;       
    }
}
