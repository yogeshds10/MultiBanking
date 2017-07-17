/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multibank.serv;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import multibank.entity.Bank;
import multibank.entity.Branch;
import multibank.entity.User;
import multibank.util.MyConnection;

/**
 *
 * @author client
 */
@WebServlet(name = "LoginHandler", urlPatterns = {"/LoginHandler"})
public class LoginHandler extends HttpServlet {

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
        Connection connection;
        PreparedStatement ps;
        ResultSet rs;
        try {
            connection = MyConnection.getConnection();
            ps = connection.prepareStatement("select iduser, passwd from user where uname = ?");
            ps.setString(1, request.getParameter("uname"));
            rs = ps.executeQuery();
            if (rs.next() && rs.getString("passwd").equals(request.getParameter("passwd"))) {
                User u = User.getUserById(rs.getInt("iduser"));
                request.getSession().setAttribute("cuser", u);
                if (u.isBankManager()) {
                    request.getSession().setAttribute("idbank", Bank.getBankByUserId(u.getIduser()).getIdBank());
                    request.getSession().setAttribute("utype", "bnk");
                } else if (u.isBranchManager()) {
                    request.getSession().setAttribute("idbranch", Branch.getBranchByUserId(u.getIduser()).getIdbranch());
                    request.getSession().setAttribute("utype", "brc");
                } else if (u.isStaff()) {
                    request.getSession().setAttribute("idbranch", Branch.getBranchByUserId(u.getIduser()).getIdbranch());
                    request.getSession().setAttribute("utype", "stf");
                } else if (u.isCustomer()) {
                    request.getSession().setAttribute("utype", "cus");
                } else {
                    request.getSession().setAttribute("utype", "user");
                }
                request.getRequestDispatcher("home.jsp").forward(request, response);
            } else if (request.getParameter("uname").equalsIgnoreCase("admin") && request.getParameter("passwd").equals("admin")) {
                request.getSession().setAttribute("name", request.getParameter("uname"));
                request.getSession().setAttribute("utype", "admin");
                request.getSession().setAttribute("id", 0);
                response.sendRedirect("home.jsp");
            } else {
                request.setAttribute("msg", "   Invalid Username/Password. Try Again...!!   ");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginHandler.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("error.jsp");
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
