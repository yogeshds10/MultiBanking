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
public class Bank {

    private int idBank;
    private String name;
    private String shortCode;
    private String address;
    private String secCode;
    private String phone;
    private String email;
    private String web;
    private boolean approved;
    private BankManager bankManager;

    public Bank() {
        bankManager = null;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
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

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public BankManager getBankManager() {
        if (bankManager == null) {
            try {
                Connection c = MyConnection.getConnection();
                PreparedStatement ps = c.prepareStatement("select iduser from bank_manager where idbank = ?");
                ps.setInt(1, this.idBank);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    bankManager = BankManager.getUserById(rs.getInt("iduser"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bankManager;
    }

    public void setBankManager(BankManager bankManager) {
        this.bankManager = bankManager;
    }

    public int getIdBank() {
        return idBank;
    }

    public void setIdBank(int idBank) {
        this.idBank = idBank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean addBank() {
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("insert into bank(name, short_code, address, sec_code, phone, email, web) values(?,?,?,?,?,?,?)");
            ps.setString(1, this.name);
            ps.setString(2, this.shortCode);
            ps.setString(3, this.address);
            ps.setString(4, this.secCode);
            ps.setString(5, this.phone);
            ps.setString(6, this.email);
            ps.setString(7, this.web);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static ArrayList<Bank> getList() {
        ArrayList<Bank> banks = new ArrayList<Bank>();
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("select idbank, name, short_code, address, phone, email, web, approved from bank");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bank b = new Bank();
                b.setAddress(rs.getString("address"));
                b.setIdBank(rs.getInt("idbank"));
                b.setName(rs.getString("name"));
                b.setShortCode(rs.getString("short_code"));
                b.setPhone(rs.getString("phone"));
                b.setEmail(rs.getString("email"));
                b.setWeb(rs.getString("web"));
                b.setApproved(rs.getBoolean("approved"));
                banks.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return banks;
        }
    }

    public static Bank getBankById(int idBank) {
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM bank b left join bank_manager bm on b.idbank = bm.idbank where b.idbank = ?");
            ps.setInt(1, idBank);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Bank b = new Bank();
                b.setAddress(rs.getString("address"));
                b.setIdBank(rs.getInt("idbank"));
                b.setName(rs.getString("name"));
                b.setShortCode(rs.getString("short_code"));
                b.setSecCode(rs.getString("sec_code"));
                b.setPhone(rs.getString("phone"));
                b.setEmail(rs.getString("email"));
                b.setWeb(rs.getString("web"));
                b.setApproved(rs.getBoolean("approved"));
                if (rs.getInt(11) != 0) {
                    b.setBankManager(BankManager.getUserById(rs.getInt(11)));
                } else {
                    b.setBankManager(null);
                }
                return b;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean approveBank() {
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("update bank set approved = ? where idbank = ?");
            ps.setBoolean(1, true);
            ps.setInt(2, this.idBank);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateBank() {
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("update bank set name = ?, short_code = ?, address = ?, sec_code = ?, phone = ?, email = ?, web = ?, approved = ? where idbank = ?");
            ps.setString(1, this.name);
            ps.setString(2, this.shortCode);
            ps.setString(3, this.address);
            ps.setString(4, this.secCode);
            ps.setString(5, this.phone);
            ps.setString(6, this.email);
            ps.setString(7, this.web);
            ps.setBoolean(8, this.approved);
            ps.setInt(9, this.idBank);
            ps.executeUpdate();
            if (this.bankManager != null) {
                ps = c.prepareStatement("insert into bank_manager values (?, ?)");
                ps.setInt(1, this.idBank);
                ps.setInt(2, this.bankManager.getIduser());
                ps.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static ArrayList<Bank> getList(User user) {
        ArrayList<Bank> banks = new ArrayList<Bank>();
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("select * from bank b, bank_manager bm where b.idbank = bm.idbank and iduser = ?");
            ps.setInt(1, user.getIduser());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bank b = new Bank();
                b.setAddress(rs.getString("address"));
                b.setApproved(rs.getBoolean("approved"));
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
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return banks;
        }
    }

    public ArrayList<Branch> getBranchList() {
        ArrayList<Branch> branches = new ArrayList<Branch>();
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("select * from branch where idbank = ?");
            ps.setInt(1, this.idBank);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Branch b = new Branch();
                b.setAddress(rs.getString("address"));
                b.setEmail(rs.getString("email"));
                b.setIdbranch(rs.getInt("idbranch"));
                b.setName(rs.getString("name"));
                b.setPhone(rs.getString("phone"));
                b.setBank(this);
                branches.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return branches;
        }
    }

    public static Bank getBankByUserId(int iduser) {
        Bank b = null;
        try {
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("select idbank from bank_manager where iduser = ?");
            ps.setInt(1, iduser);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                b = Bank.getBankById(rs.getInt("idbank"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return b;
        }
    }
}
