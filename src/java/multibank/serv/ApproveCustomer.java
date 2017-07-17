/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multibank.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import multibank.entity.Bank;
import multibank.util.MyConnection;

/**
 *
 * @author Yogi
 */
@WebServlet(name = "ApproveCustomer", urlPatterns = {"/ApproveCustomer"})
public class ApproveCustomer extends HttpServlet {

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
            PreparedStatement ps = MyConnection.getConnection().prepareStatement("update account set approved=? where idaccount=?");
            ps.setInt(1,1);
            ps.setInt(2, Integer.parseInt(request.getParameter("idaccount")));
            int x = ps.executeUpdate();
            if (x == 1) {
                request.setAttribute("msg", "Customer Approved Successfully...!!");
                request.getRequestDispatcher("viewaccounts.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "Unable to approve customer... Try again later...!");
                request.getRequestDispatcher("viewaccounts.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("msg", "Unable to approve customer... Try again later...!");
            request.getRequestDispatcher("viewaccounts.jsp").forward(request, response);
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
