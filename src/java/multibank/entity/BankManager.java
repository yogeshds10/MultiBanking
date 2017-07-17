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
public class BankManager extends User {

    private ArrayList<Bank> banks;
    public BankManager() {
        banks = new ArrayList<Bank>();
    }

    public ArrayList<Bank> getBanks() {
        if(banks.isEmpty()){
            try {
                Connection c = MyConnection.getConnection();
                PreparedStatement ps = c.prepareStatement("select b.idbank, b.name, b.short_code, b.address, b.sec_code, b.phone, b.email, b.web, b.approved from bank b, bank_manager bm where b.idbank = bm.idbank and bm.iduser = ?");
                ps.setInt(1, this.getIduser());
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Bank b = new Bank();
                    b.setAddress(rs.getString("address"));
                    b.setApproved(rs.getBoolean("approved"));
                    b.setBankManager(this);
                    b.setEmail(rs.getString("email"));
                    b.setIdBank(rs.getInt("idbank"));
                    b.setName(rs.getString("name"));
                    b.setPhone(rs.getString("phone"));
                    b.setSecCode(rs.getString("sec_code"));
                    b.setShortCode(rs.getString("short_code"));
                    b.setWeb(rs.getString("web"));
                    banks.add(b);
                }
            } catch (SQLException ex) {
                Logger.getLogger(BankManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BankManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return banks;
    }

    public void setBanks(ArrayList<Bank> banks) {
        this.banks = banks;
    }

    public static BankManager getUserById(int idUser) {
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("select * from user where iduser = ?");
            ps.setInt(1, idUser);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BankManager bm = new BankManager();
                bm.setAddress(rs.getString("address"));
                bm.setDob(rs.getString("dob"));
                bm.setEmail(rs.getString("email"));
                bm.setGender(rs.getBoolean("gender"));
                bm.setIduser(rs.getInt("iduser"));
                bm.setMobile(rs.getString("mobile"));
                bm.setName(rs.getString("name"));
                bm.setPasswd(rs.getString("passwd"));
                bm.setUname(rs.getString("uname"));
                return bm;
            } else {
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
}
