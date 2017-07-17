/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multibank.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import multibank.util.MyConnection;
import org.json.simple.JSONObject;

/**
 *
 * @author client
 */
@WebServlet(name = "GetAccount", urlPatterns = {"/GetAccount"})
public class GetAccount extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject account = new JSONObject();
        PrintWriter out = response.getWriter();
        try {
            response.setContentType("text/html;charset=UTF-8");
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT a.idaccount, a.idbranch, br.name, a.iduser, u.name, b.idbank, b.name, a.`type`, a.approved FROM account a, `user` u, bank b, branch br where a.idbranch = br.idbranch and br.idbank = b.idbank and a.iduser = u.iduser and a.idaccount = ?");
            ps.setInt(1, Integer.parseInt(request.getParameter("idaccount")));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                account.put("idaccount", rs.getInt(1));
                account.put("idbranch", rs.getInt(2));
                account.put("brname", rs.getString(3));
                account.put("iduser", rs.getInt(4));
                account.put("uname", rs.getString(5));
                account.put("idbank", rs.getInt(6));
                account.put("bname", rs.getString(7));
                account.put("type", rs.getString(8));
                account.put("approved", rs.getBoolean(9));
                if (request.getSession().getAttribute("utype").toString().equals("bnk")) {
                    if (account.get("idbank").toString().equals(request.getSession().getAttribute("idbank").toString())) {
                        account.put("error", 0);
                    } else {
                        account.put("error", 1);
                        account.put("msg", "Not Authorized to access the account.");
                    }
                } else {
                    if (account.get("idbranch").toString().equals(request.getSession().getAttribute("idbranch").toString())) {
                        account.put("error", 0);
                    } else {
                        account.put("error", 1);
                        account.put("msg", "Not Authorized to access the account.");
                    }
                }
                if (Boolean.parseBoolean(account.get("approved").toString())) {
                    account.put("error", 0);
                } else {
                    account.put("error", 1);
                    account.put("msg", "Account is yet to be activated.");
                }
            } else {
                account.put("error", 1);
                account.put("msg", "Invalid Account No.");
            }
            if (account.get("error").toString().equals("0")) {
                ps = c.prepareStatement("select total from transaction where idtransaction = (select max(idtransaction) from transaction where idaccount = ?)");
                ps.setInt(1, Integer.parseInt(request.getParameter("idaccount")));
                rs = ps.executeQuery();
                if (rs.next()) {
                    account.put("aamount", rs.getFloat(1));
                } else {
                    account.put("aamount", 0);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetAccount.class.getName()).log(Level.SEVERE, null, ex);
            account.put("error", 1);
            account.put("msg", "Invalid Account No.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetAccount.class.getName()).log(Level.SEVERE, null, ex);
            account.put("error", 1);
            account.put("msg", "Invalid Account No.");
        } finally {
            out.print(account);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
