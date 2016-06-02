
import java.io.File;
import java.io.FileNotFoundException;
import org.scilab.modules.javasci.JavasciException;
import org.scilab.modules.javasci.Scilab;
import org.scilab.modules.types.ScilabDouble;
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
public class test1 {
     public String scilab(int d1) throws JavasciException.InitializationException, JavasciException, FileNotFoundException{
        
                Scilab sci = new Scilab();
                String b = null;
               try{
                sci.exec("timer()");
                sci.open(new File("/home/anamika/ex1.sce"));
               }
               catch(JavasciException.AlreadyRunningException e){
                   
               }
               finally{
               
                   
		ScilabInteger s= new ScilabInteger(d1);
		sci.put("a",s);
                try{
                sci.execException("y=matrices(a)");
                    
                    //sci.execException("y=memory(a)");
                    
                }
                 catch(JavasciException.ScilabErrorException e1){
                    sci.close();
                    return e1.getMessage();
                    
                }
                    System.out.println(1);
                 
               
		//ScilabInteger c=(ScilabInteger)(sci.get("y"));
                
                ScilabDouble c=(ScilabDouble)(sci.get("y"));
                //System.out.println(2);
		//b=c.getIntElement(0, 0)+" ";
                b=c.getRealElement(0,0)+" ";
                sci.exec("c=timer()");
                ScilabDouble d=(ScilabDouble)(sci.get("c"));
                b+=d.getRealElement(0, 0);
               }
		//sci.close();
         return b;       
    }
}
