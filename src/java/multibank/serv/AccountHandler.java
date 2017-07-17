/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multibank.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import multibank.entity.Customer;

/**
 *
 * @author client
 */
@WebServlet(name = "AccountHandler", urlPatterns = {"/AccountHandler"})
public class AccountHandler extends HttpServlet {

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
        Customer c = new Customer();
        if (request.getParameter("iduser") == null) {
            c.setAddress(request.getParameter("address"));
            c.setDob(request.getParameter("dob"));
            c.setEmail(request.getParameter("email"));
            c.setGender(Boolean.parseBoolean(request.getParameter("gender")));
            c.setMobile(request.getParameter("mobile"));
            c.setName(request.getParameter("name"));
            c.setType(request.getParameter("type"));
            c.setApproved(true);
            if (request.getSession().getAttribute("utype").toString().equals("bnk")) {
                c.setIdbranch(Integer.parseInt(request.getParameter("idbranch")));
            } else {
                c.setIdbranch(Integer.parseInt(request.getSession().getAttribute("idbranch").toString()));
            }
            if (c.addCustomer()) {
                request.setAttribute("msg", "Customer Registraton Successful.");
                request.getRequestDispatcher("home.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "Unable to Register. Try again later...!");
                request.getRequestDispatcher("addacount.jsp").forward(request, response);
            }
        } else {
            c.setIduser(Integer.parseInt(request.getParameter("iduser")));
            c.setApproved(true);
            c.setType(request.getParameter("type"));
            if (request.getSession().getAttribute("utype").toString().equals("bnk")) {
                c.setIdbranch(Integer.parseInt(request.getParameter("idbranch")));
            } else {
                c.setIdbranch(Integer.parseInt(request.getSession().getAttribute("idbranch").toString()));
            }
            if (c.addCustomer()) {
                request.setAttribute("msg", "Customer Registraton Successful.");
                request.getRequestDispatcher("home.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "Unable to Register. Try again later...!");
                request.getRequestDispatcher("addaccount.jsp").forward(request, response);
            }
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
