
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.scilab.modules.javasci.JavasciException;
import org.scilab.modules.javasci.Scilab;
import org.scilab.modules.types.ScilabInteger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class test {
    public int scilab(int d1,int d2) throws JavasciException.InitializationException, JavasciException, FileNotFoundException{
    Scilab sci = new Scilab();
		//sci.open();
		int b=0;
		try{
		sci.open(new File("/home/anamika/ex1.sce"));
                }
               catch(JavasciException.AlreadyRunningException e){
               }
               finally{
		ScilabInteger s= new ScilabInteger(d1);
		ScilabInteger s1= new ScilabInteger(d2);
		sci.put("a",s);
		sci.put("b",s1);
		sci.exec("y=add(a,b)");
		ScilabInteger c=(ScilabInteger)(sci.get("y"));
		b=c.getIntElement(0, 0);
		sci.close();
                }
         return b;       
    }
}
