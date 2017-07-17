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

/**
 *
 * @author client
 */
@WebServlet(name = "WithdrawHandler", urlPatterns = {"/WithdrawHandler"})
public class WithdrawHandler extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            response.setContentType("text/html;charset=UTF-8");
            Connection c = MyConnection.getConnection();
            PreparedStatement ps = c.prepareStatement("select total from transaction where idtransaction = (select max(idtransaction) from transaction where idaccount = ?)");
            ps.setInt(1, Integer.parseInt(request.getParameter("idaccount")));
            ResultSet rs = ps.executeQuery();
            float total = 0;
            if (rs.next()) {
                total = rs.getFloat("total");
            } else {
                total = 0;
            }
            if (total >= Float.parseFloat(request.getParameter("amount"))) {
                total -= Float.parseFloat(request.getParameter("amount"));
                ps = c.prepareStatement("insert into transaction(idaccount, debit, credit, total) values(?,?,?,?)");
                ps.setInt(1, Integer.parseInt(request.getParameter("idaccount")));
                ps.setFloat(2, Float.parseFloat(request.getParameter("amount")));
                ps.setFloat(3, 0);
                ps.setFloat(4, total);
                ps.executeUpdate();
                request.setAttribute("msg", "Amount Debited Successfully.");
                request.getRequestDispatcher("home.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "Insufficient Funds. Try again.");
                request.getRequestDispatcher("withdraw.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepositHandler.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msg", "Unable to Debit Amount. Try Again Later");
            request.getRequestDispatcher("withdraw.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DepositHandler.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msg", "Unable to Debit Amount. Try Again Later");
            request.getRequestDispatcher("withdraw.jsp").forward(request, response);
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
