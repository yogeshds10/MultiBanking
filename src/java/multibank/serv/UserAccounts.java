/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multibank.serv;

import java.io.IOException;
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
import multibank.entity.User;
import multibank.util.MyConnection;

/**
 *
 * @author client
 */
@WebServlet(name = "UserAccounts", urlPatterns = {"/UserAccounts"})
public class UserAccounts extends HttpServlet {

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
        try {
            response.setContentType("text/html;charset=UTF-8");
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("select idaccount, br.idbranch, br.name, a.`type`, a.approved from account a, branch br where br.idbranch = a.idbranch and a.iduser = ?");
            ps.setInt(1, ((User)request.getSession().getAttribute("cuser")).getIduser());
            ResultSet rs = ps.executeQuery();
            request.setAttribute("results", rs);
            request.getRequestDispatcher("custaccounts.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserAccounts.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msg", "No Accounts Found!!");
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserAccounts.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msg", "No Accounts Found!!");
            request.getRequestDispatcher("home.jsp").forward(request, response);
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
