/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;


import bean.user;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.String;
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
    public int ind = 0;

       
        int t=0;
        
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
           System.out.print("hello1230"+no);
            
          String s6=String.valueOf((no+40)%40);
          pr = conn.prepareStatement("update record set m_id=? where username='"+us.getusername()+"';");
         pr.setString(1, s6);
       pr.executeUpdate();
          
            System.out.println("t is"+t);
            System.out.println("conn");
        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
    }

    
    public int isCorrectPassword(user ur) {
        connect();

        try {
            st = conn.createStatement();
        } catch (SQLException ex) {
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
      
    public void adddatetime(user us) {
        connect();

        try {
            String s1 =  us.getslot(); 
            String s2 =  us.getdate(); 
         //   String s3 =  us.getacc_id();
            String s3 = us.getacc_id();
            
            System.out.println("account ="+s3);
                     
            
            {
            
                String s=""+8;
                   
           

            pr = conn.prepareStatement("insert into slot(m_id,slot_time,slot_date,acc_id)values (?,?,?,?)");
            pr.setString(1,s);
            pr.setString(2,s1);
         
            pr.setString(3,s2);
            pr.setString(4,s3);
                      
            pr.executeUpdate();
            System.out.println("inserted");
        }
        } catch (SQLException ex) {
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
     public int returnmid(user ur) {
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
                        
            while (rs.next())
            {
                  
                
                t=rs.getInt("m_id");
                System.out.println("machine id is "+t);
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
        try {
            st = conn.createStatement();
        } catch (SQLException ex) {
     
          ex.printStackTrace();
        }
       

 
        try {

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

}