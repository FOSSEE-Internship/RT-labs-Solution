/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;


import bean.user;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.String;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author sankalp gupta  */
final public class database {

    String driver = "com.mysql.jdbc.Driver";
    String database = "jdbc:mysql://localhost:3306/sankalp?zeroDateTimeBehavior=convertToNull";
    String user = "root";
    String pass = "fossee";
    Statement st = null;
    PreparedStatement pr = null;
    Connection conn = null;
    public int flag = 0;
    public int flag2 = 0;

    public int ind = 0;

       
        int t=0;
        int e=0;
        
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(database, user, pass);

        } catch (ClassNotFoundException ex) {
            System.out.println("connection failed" + "class not found ");
        } catch (SQLException ex) {
            System.out.println("connection failed" + "root"+ex);
        }

    }

    public void disconnect() {

        if (conn == null) {
            System.err.println("connection never established");
        } else {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("disconnection failed");
            }
        }

    }

    public void addnewuser(user us) {
        connect();

        try {
            String s = us.getusername();
            String s1 = us.getName();
            String s2 = us.getEmail();
            String s3 = us.getmobile();
            String s4 = us.getstate();
            String s5 = us.getPassword();
            
        
            
            
            pr = conn.prepareStatement("insert into record(username,name,email,state,mobile,password)values (?,?,?,?,?,?)");
            pr.setString(1,s);
            pr.setString(2,s1);
            pr.setString(3,s2);
            pr.setString(4,s3);
            pr.setString(5,s4);
            pr.setString(6,s5);
            //pr.setString(7,s6);
            
            pr.executeUpdate();
            us.setacc_id(String.valueOf(getaccount(us)));
               connect();
            
            String x =us.getacc_id();
                        // int d=getaccount(us);
                       //  System.out.println("d is "+d);
            int no=Integer.parseInt(x);
            System.out.print("acc_id"+no);

            String s6=String.valueOf((no+40)%40);
            pr = conn.prepareStatement("update record set m_id=? where username='"+us.getusername()+"';");
            pr.setString(1, s6);
            pr.executeUpdate();

            System.out.println("t is"+t);
            System.out.println("conn");
            }
            catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
            }
            disconnect();
            }

    
    public int isCorrectPassword(user ur) {
            connect();

            try 
            {
            st = conn.createStatement();
            }
            catch (SQLException ex) {
          //  Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
          ex.printStackTrace();
        }
        int s = -1;
        try {
            //Out.println("master");
            ResultSet rs = st.executeQuery("select * from record");
            while (rs.next()) {
                if (rs.getString("username").equals(ur.getusername())) {

                    flag = 2;
                    System.out.println("enters here ");
                    if (rs.getString("password").equals(ur.getPassword())) {
                        
                        
                        flag = 1;
                         System.out.println("enters here "+flag);
                         s=1;
                  
                        break;
                    }

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
       disconnect();
        return s;
    }
    
    public int chekslot(user us) {
        connect();
            try {
            st = conn.createStatement();
             } catch (SQLException ex) {
              //  Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            }
            int c=-1;
            try {
            System.out.println("stucked here");
            {      
            ResultSet rs;
            rs = st.executeQuery("select * from slot where slot_time='"+us.getslot()+"' AND m_id='"+us.getm_id()+"' AND slot_date='"+us.getdate()+"'");
            if (rs.next() )  
            {
            flag2=1;
            System.out.println("checked");
            c=1;
            }
            else
            {
            //if(!rs.next() ) 
            //{
            //System.out.println("no data");
            //flag2=2;
            c=2; 
            //}
            //else 
            //{
            }//c=2;
            }
            }
            
            
                
            catch(SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
            }
            disconnect();
            return c;
            }
  
  
    
    public void adddatetime(user us) {
        connect();
          try {
            st = conn.createStatement();
        } catch (SQLException ex) {
     
          ex.printStackTrace();
        }

             try 
            {
            String s1 =  us.getslot(); 
            String s2 =  us.getdate(); 
            //   String s3 =  us.getacc_id();
            String s3 = us.getacc_id();
                
            
            ResultSet rs = st.executeQuery("select m_id from record where username='"+us.getusername()+"';");
                          //  rs.getString("name");
            while (rs.next())
            {
                e = rs.getInt("m_id");
            }
             String s=Integer.toString(e);
 
            
            
                    
            
            {
            pr = conn.prepareStatement("insert into slot(m_id,slot_time,slot_date,acc_id)values (?,?,?,?)");
            pr.setString(1,s);
            pr.setString(2,s1);
            
            pr.setString(3,s2);
            pr.setString(4,s3);
                      
            pr.executeUpdate();
            System.out.println("inserted");
        
            }
  
            } 
            catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
            }
            disconnect();
            }

      
    public void getdatetime(user us) {
        connect();
          try {
            st = conn.createStatement();
        } catch (SQLException ex) {
     
          ex.printStackTrace();
        }

             try 
            {
           
            //   String s3 =  us.getacc_id();
            String s3 = us.getacc_id();
                
            
            ResultSet rs = st.executeQuery("select slot_date,slot_time from slot where acc_id='"+us.getacc_id()+"';");
                          //  rs.getString("name");
            while (rs.next())
            {
              String d= rs.getString("slot_date");
              String t =rs.getString("slot_time");
               us.setdate(d);
               us.setslot(t);
                System.out.println("t"+t);
                System.out.println("d"+d);
            
            }
            
            
            
            
            }
            catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
            }
            disconnect();
            }

    
    public int getaccount(user ur) {
        connect();
     
        try {
            st = conn.createStatement();
        } catch (SQLException ex) {
     
          ex.printStackTrace();
        }
        
//        int s = -1;
        
        try {
            //Out.println("master");
                   
          ResultSet rs = st.executeQuery("select acc_id from record where username='"+ur.getusername()+"';");
                          //  rs.getString("name");
            while (rs.next())
            {
                  
                
                t=rs.getInt("acc_id");
                System.out.println("account id is "+t);
            }
            
        
                    
                    
        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
       disconnect();
        return t;
    }
     
    
    
     public int returnm_id(user ur) {
        connect();
     
        try {
            st = conn.createStatement();
        } catch (SQLException ex) {
     
          ex.printStackTrace();
        }
        
//        int s = -1;
        
        try {
            //Out.println("master");
                   
          ResultSet rs = st.executeQuery("select m_id from record where username='"+ur.getusername()+"';");
                          //  rs.getString("name");
            while (rs.next())
            {
                  
                
                t=rs.getInt("m_id");
                System.out.println("m id is "+t);
            }
            
        
                    
                    
        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
       disconnect();
        return t;
    }
     
    
      
    public List<String> userdetails(user ur) {
            connect();

            List<String> results = new ArrayList<String>();         
            try 
            {
            st = conn.createStatement();
            }
            catch (SQLException ex) {

            ex.printStackTrace();
            }



            try
            {

            System.out.println("check");
            ResultSet rs = st.executeQuery("select slot_time,slot_date from slot where acc_id='"+ur.getacc_id()+"';");
           //  rs.getString("name");
            while (rs.next())
            {


            System.out.println("check again ");
             results.add(rs.getString("slot_time"));


            results.add(rs.getString("slot_date")); 
            }

            //System.out.println(""+results);
            // for(int i=0;i<results.size();i++){
            // System.out.println(results.get(i));



            }
            catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);

            ex.printStackTrace();
            }

            disconnect();
            return results;
            }

 public int enterslot(user ur) {
            connect();

            try 
            {
            st = conn.createStatement();
            }
            catch (SQLException ex) {
            //  Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            }
            int s =0;
            try {
            long yourmilliseconds = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");    

            Date resultdate = new Date(yourmilliseconds);


            SimpleDateFormat sdf2 = new SimpleDateFormat("HH:00 -- HH:55"); 



               //Out.println("master");
            ResultSet rs ;
            rs= st.executeQuery("select * from slot where acc_id='"+ur.getacc_id()+"'");
            System.out.println("ACCNT ID"+ur.getacc_id());
            System.out.println("date ID"+ur.getdate());
            System.out.println("tim ID"+ur.getslot());
              
              
              
              
            System.out.println(sdf2.format(resultdate));  
            while (rs.next()) {
            if ((sdf.format(resultdate)).equals(ur.getdate())) {
  
                        
            flag = 2;
            System.out.println("enters here date matched");
            if ((sdf2.format(resultdate)).equals(ur.getslot())) {
                     
            flag = 1;
            System.out.println("enters here time matched"+flag);
            s=1;
                        
            break;
            }

            }
            }
       
            }
            catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
            } 
            disconnect();
            return s; 
            }

 public long getepoch(user ur) {
           connect();

            try 
            {
            st = conn.createStatement();
            }
            catch (SQLException ex) {
            //  Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            }
            int s =0;
            try {
            long yourmilliseconds = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");    

            Date resultdate = new Date(yourmilliseconds); 

 
            SimpleDateFormat sdf2 = new SimpleDateFormat("HH:00 -- HH:55"); 
        
 
            SimpleDateFormat sdf3 = new SimpleDateFormat("HH:55"); 
        
           
            SimpleDateFormat df = new SimpleDateFormat("YYYY-mm-dd HH:mm");
             


            //Out.println("master");
            ResultSet rs ;
            rs= st.executeQuery("select * from slot where acc_id='"+ur.getacc_id()+"'");
            System.out.println("ACCNT ID"+ur.getacc_id());
            System.out.println("date ID"+ur.getdate());
            System.out.println("tim ID"+ur.getslot());
              
              
              
              
            System.out.println(sdf2.format(resultdate));  
            while (rs.next()) {
            if ((sdf.format(resultdate)).equals(ur.getdate())) {
  
                        
            flag = 2;
            System.out.println("date matched");
            if ((sdf2.format(resultdate)).equals(ur.getslot())) {
                      
            flag = 1;
            System.out.println(" time matched"+flag);
            String str = (ur.getdate()+" "+ sdf3.format(resultdate));

            Date date = (Date)(df.parse(str));
            long epoch = date.getTime();
            System.out.println("epoch time is " +epoch); // 1055545912454
            break;
            }

            }
            }
       
            } 
            catch (SQLException ex) 
            {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (ParseException ex)
            {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
            }
            disconnect();
            return s; 
            }

}