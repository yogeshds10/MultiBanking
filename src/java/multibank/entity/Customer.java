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
public class Customer extends User {

    private int idaccount;
    private int idbranch;
    private String type;
    private Boolean approved;

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public int getIdaccount() {
        return idaccount;
    }

    public void setIdaccount(int idaccount) {
        this.idaccount = idaccount;
    }

    public int getIdbranch() {
        return idbranch;
    }

    public void setIdbranch(int idbranch) {
        this.idbranch = idbranch;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean addCustomer() {
        boolean bool = false;
        try {
            if (this.getIduser() == 0) {
                Connection c = MyConnection.getConnection();
                c.setAutoCommit(false);
                PreparedStatement ps = c.prepareStatement("insert into user(name, dob, gender, address, email, mobile, uname, passwd) values(?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, this.getName());
                ps.setString(2, this.getDob());
                ps.setBoolean(3, this.isGender());
                ps.setString(4, this.getAddress());
                ps.setString(5, this.getEmail());
                ps.setString(6, this.getMobile());
                if (this.getUname() == null) {
                    this.setUname("");
                }
                if (this.getPasswd() == null) {
                    this.setPasswd("password");
                }
                ps.setString(7, this.getUname());
                ps.setString(8, this.getPasswd());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    if (this.getUname() == "") {
                        ps = c.prepareStatement("update user set uname = ? where iduser = ?");
                        ps.setString(1, "cust" + rs.getInt(1));
                        ps.setInt(2, rs.getInt(1));
                        ps.executeUpdate();
                    }
                    ps = c.prepareStatement("insert into account(idbranch, iduser, `type`, approved) values(?,?,?,?)");
                    ps.setInt(1, this.idbranch);
                    ps.setInt(2, rs.getInt(1));
                    ps.setString(3, this.type);
                    ps.setBoolean(4, this.approved);
                    ps.executeUpdate();;
                }
                c.commit();
                bool = true;
            } else {
                Connection c = MyConnection.getConnection();
                PreparedStatement ps = c.prepareStatement("insert into account(idbranch, iduser, `type`, approved) values(?,?,?,?)");
                ps.setInt(1, this.idbranch);
                ps.setInt(2, this.getIduser());
                ps.setString(3, this.type);
                ps.setBoolean(4, this.approved);
                ps.executeUpdate();
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return bool;
        }
    }

    public static ResultSet getCustomerList(Bank b) {
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("select idaccount, a.idbranch, br.name, a.iduser, u.name, a.`type`, u.mobile, u.email, a.approved from account a, branch br, bank b, `user`u where a.iduser = u.iduser and br.idbranch = a.idbranch and br.idbank = b.idbank and b.idbank = ?");
            ps.setInt(1, b.getIdBank());
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static ResultSet getCustomerList(Branch b) {
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("select idaccount, a.iduser, u.name, a.`type`, u.mobile, u.email, a.approved from account a, branch br, `user`u where a.iduser = u.iduser and br.idbranch = a.idbranch and a.idbranch = ?");
            ps.setInt(1, b.getIdbranch());
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}