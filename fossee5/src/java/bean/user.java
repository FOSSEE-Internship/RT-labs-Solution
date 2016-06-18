/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;


public class user implements Serializable {
    
String mobile,state,username,name,dob,slot, email,password, date, time,acc_id,m_id;
public user()
{
    
}
    /**
     * @return the email
     */
  public String getusername() {
        return username;
    }
     public void setusername(String username) {
        this.username = username;
    }
     
     
   public String getName() {
        return name;
    }
   
       public void setName(String name) {
        this.name = name;
    }
       
       
    public String getEmail() {
        return email;
    }
    
       
    public void setEmail(String email) {
        this.email = email;
    }
             
       
    public void setmobile(String mobile) {
        this.mobile = mobile;
    } 
   
     public String getmobile() {
        return mobile;
    }
     
     
    public String getstate() {
        return state;
    }
    public void setstate(String state) {
        this.state = state;
    }
    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * @param name the name to set
     */
       /**
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */

     public void setDob(String dob) {
        this.dob = dob;
    }     
    
    public String getdate() {
        return date;
    }
    public void setdate(String date) {
        this.date = date;
    }
    
    public String getslot() {
        return slot;
    }
    
    public void setslot(String slot) {
        this.slot= slot;
    }
    
    public String getacc_id() {
        return acc_id;
    }
    
    public void setacc_id(String acc_id) {
        this.acc_id= acc_id;
    }
    
   public String getm_id(){
       return m_id;
   }
 public void setm_id(String m_id) {
        this.m_id=m_id;
    }

    /**
     * @param dob the dob to set
     */
  
    
    
    
}