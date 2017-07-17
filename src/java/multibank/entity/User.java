/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multibank.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import multibank.util.MyConnection;

/**
 *
 * @author client
 */
public class User {

    private int iduser = 0;
    private String name;
    private String dob;
    private boolean gender;
    private String address;
    private String email;
    private String mobile;
    private String uname = null;
    private String passwd = null;

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPasswd() {
        return passwd;
    }

    public boolean addUser() {
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("insert into user(name, dob, gender, address, email, mobile, uname, passwd) values(?,?,?,?,?,?,?,?)");
            ps.setString(1, this.name);
            ps.setString(2, this.dob);
            ps.setBoolean(3, this.gender);
            ps.setString(4, this.address);
            ps.setString(5, this.email);
            ps.setString(6, this.mobile);
            ps.setString(7, this.uname);
            ps.setString(8, this.passwd);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateUser() {
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("update user set name = ?, dob = ?, gender = ?, address = ?, email = ?, uname = ?, passwd = ? where iduser = ?");
            ps.setString(1, this.name);
            ps.setString(2, this.dob);
            ps.setBoolean(3, this.gender);
            ps.setString(4, this.address);
            ps.setString(5, this.email);
            ps.setString(6, this.uname);
            ps.setString(7, this.passwd);
            ps.setInt(8, this.iduser);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public static User getUserById(int idUser){
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("select * from user where iduser = ?");
            ps.setInt(1, idUser);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                User u = new User();
                u.setAddress(rs.getString("address"));
                u.setDob(rs.getString("dob"));
                u.setEmail(rs.getString("email"));
                u.setGender(rs.getBoolean("gender"));
                u.setIduser(rs.getInt("iduser"));
                u.setMobile(rs.getString("mobile"));
                u.setName(rs.getString("name"));
                u.setPasswd(rs.getString("passwd"));
                u.setUname(rs.getString("uname"));
                return u;
            }else{
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean isBankManager() {
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("select idbank from bank_manager where iduser = ?");
            ps.setInt(1, this.iduser);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean isBranchManager() {
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("select idbranch from staff where ismanager = ? and iduser = ?");
            ps.setBoolean(1, true);
            ps.setInt(2, this.iduser);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean isStaff() {
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("select idbranch from staff where iduser = ?");
            ps.setBoolean(1, false);
            ps.setInt(2, this.iduser);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean isCustomer() {
        
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("select idaccount from account where iduser = ?");
            ps.setInt(1, this.iduser);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
