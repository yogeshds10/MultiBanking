/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multibank.serv;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import multibank.entity.Bank;
import multibank.entity.Customer;
import multibank.entity.User;

/**
 *
 * @author client
 */
@WebServlet(name = "RegistrationHandler", urlPatterns = {"/RegistrationHandler"})
public class RegistrationHandler extends HttpServlet {

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
        if (request.getParameter("id").equals("1")) {
            Bank b = new Bank();
            b.setAddress(request.getParameter("address"));
            b.setName(request.getParameter("name"));
            b.setSecCode(request.getParameter("seccode"));
            b.setShortCode(request.getParameter("shortcode"));
            b.setPhone(request.getParameter("phone"));
            b.setEmail(request.getParameter("email"));
            b.setWeb(request.getParameter("web"));
            if (b.addBank()) {
                request.setAttribute("msg", "Bank Registration Completed succesfully..!!");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "Unable to Register Bank now, Try after some time...");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else if (request.getParameter("id").equals("2")) {
            User u = new User();
            u.setAddress(request.getParameter("address"));
            u.setDob(request.getParameter("dob"));
            u.setEmail(request.getParameter("email"));
            u.setGender(Boolean.parseBoolean(request.getParameter("gender").toString()));
            u.setMobile(request.getParameter("mobile"));
            u.setName(request.getParameter("name"));
            u.setUname(request.getParameter("uname"));
            u.setPasswd(request.getParameter("passwd"));
            if (u.addUser()) {
                request.setAttribute("msg", "User Registration Completed succesfully..!! Login to proceed...");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "Unable to Register User now, Try after some time...");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else if (request.getParameter("id").equals("3")) {
            Customer c = new Customer();
            c.setAddress(request.getParameter("address"));
            c.setDob(request.getParameter("dob"));
            c.setEmail(request.getParameter("email"));
            c.setGender(Boolean.parseBoolean(request.getParameter("gender")));
            c.setIdbranch(Integer.parseInt(request.getParameter("idbranch")));
            c.setMobile(request.getParameter("mobile"));
            c.setName(request.getParameter("name"));
            c.setPasswd(request.getParameter("passwd"));
            c.setType(request.getParameter("type"));
            c.setUname(request.getParameter("uname"));
            c.setApproved(false);
            if (c.addCustomer()) {
                request.setAttribute("msg", "Customer Registraton Successful.. Approval Pending...!!");
            } else {
                request.setAttribute("msg", "Unable to Register. Try again later...!");
            }
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
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
