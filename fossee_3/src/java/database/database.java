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

/**
 *
 * @author Anamika Modi
 */
final public class database {

    String driver = "com.mysql.jdbc.Driver";
    String database = "jdbc:mysql://localhost:3306/fossee?zeroDateTimeBehavior=convertToNull";
    String user = "root";
    String pass = "fossee";
    Statement st = null;
    PreparedStatement pr = null;
    Connection conn = null;
    public int flag = 0;
    public int ind = 0;

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
            String s = us.getEmail();
            String s1 = us.getPassword();
            String s2 = us.getName();
            String s3 = us.getDob();
            

            pr = conn.prepareStatement("insert into user values (?,?,?,?)");
            pr.setString(1, s);
            pr.setString(3, s1);
            pr.setString(2, s2);
            pr.setString(4, s3);
            
            
            pr.executeUpdate();
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
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
        int s = -1;
        try {
            ResultSet rs = st.executeQuery("select * from user");
            while (rs.next()) {
                if (rs.getString("name").equals(ur.getName())) {

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

  
    

}
