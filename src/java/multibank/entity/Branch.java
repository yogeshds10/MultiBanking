/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multibank.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import multibank.util.MyConnection;

/**
 *
 * @author client
 */
public class Branch {

    private int idbranch;
    private int idbank;
    private String name;
    private String address;
    private String phone;
    private String email;
    private Bank bank;

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public int getIdbranch() {
        return idbranch;
    }

    public void setIdbranch(int idbranch) {
        this.idbranch = idbranch;
    }

    public int getIdbank() {
        return idbank;
    }

    public void setIdbank(int idbank) {
        this.idbank = idbank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Branch getBranchById(int idbranch) {
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("select * from branch where idbranch = ?");
            ps.setInt(1, idbranch);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Branch b = new Branch();
                b.setAddress(rs.getString("address"));
                b.setBank(Bank.getBankById(rs.getInt("idbank")));
                b.setEmail(rs.getString("email"));
                b.setIdbank(rs.getInt("idbank"));
                b.setIdbranch(rs.getInt("idbranch"));
                b.setName(rs.getString("name"));
                b.setPhone(rs.getString("phone"));
                return b;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Branch.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Branch.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean addBranch() {
        boolean bool = false;
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("insert into branch(idbank, name, address, phone, email) values(?,?,?,?,?)");
            ps.setInt(1, this.idbank);
            ps.setString(2, this.name);
            ps.setString(3, this.address);
            ps.setString(4, this.phone);
            ps.setString(5, this.email);
            ps.executeUpdate();
            bool = true;
        } catch (SQLException ex) {
            Logger.getLogger(Branch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Branch.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return bool;
        }
    }

    public User getManager() {
        User u = null;
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("select iduser from staff where ismanager = ? and idbranch = ?");
            ps.setBoolean(1, true);
            ps.setInt(2, this.idbranch);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = User.getUserById(rs.getInt("iduser"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Branch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Branch.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return u;
        }
    }

    public ArrayList<Staff> getStaffList() {
        ArrayList<Staff> staffs = new ArrayList<Staff>();
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT s.idstaff, u.iduser, s.ismanager, u.name, u.address, u.email, u.mobile FROM staff s, `user` u where s.iduser = u.iduser and s.idbranch = ?");
            ps.setInt(1, this.idbranch);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Staff s = new Staff();
                s.setIdbranch(this.idbranch);
                s.setIdstaff(rs.getInt("idstaff"));
                s.setIduser(rs.getInt("iduser"));
                s.setAddress(rs.getString("address"));
                s.setEmail(rs.getString("email"));
                s.setMobile(rs.getString("mobile"));
                s.setName(rs.getString("name"));
                s.setManager(rs.getBoolean("ismanager"));
                staffs.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Branch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Branch.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return staffs;
        }
    }

    public static Branch getBranchByUserId(int iduser) {
        Branch b = null;
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("select idbranch from staff where iduser = ?");
            ps.setInt(1, iduser);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                b = Branch.getBranchById(rs.getInt("idbranch"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Branch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Branch.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return b;
        }
    }
}
