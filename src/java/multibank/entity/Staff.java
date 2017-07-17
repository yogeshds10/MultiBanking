/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multibank.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import multibank.util.MyConnection;

/**
 *
 * @author client
 */
public class Staff extends User {

    private int idbranch;
    private int idstaff;
    private boolean manager;

    public int getIdstaff() {
        return idstaff;
    }

    public void setIdstaff(int idstaff) {
        this.idstaff = idstaff;
    }

    public int getIdbranch() {
        return idbranch;
    }

    public void setIdbranch(int idbranch) {
        this.idbranch = idbranch;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public boolean addStaff() {
        boolean bool = false;
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("insert into staff(idbranch, iduser, ismanager) values(?,?,?)");
            ps.setInt(1, this.idbranch);
            ps.setInt(2, this.getIduser());
            ps.setBoolean(3, this.manager);
            ps.executeUpdate();
            bool = true;
        } catch (SQLException ex) {
            Logger.getLogger(Staff.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Staff.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return bool;
        }

    }
}
