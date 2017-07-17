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
import multibank.entity.BankManager;
import multibank.entity.User;

/**
 *
 * @author client
 */
@WebServlet(name = "BankHandler", urlPatterns = {"/BankHandler"})
public class BankHandler extends HttpServlet {

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
        Bank b = Bank.getBankById(Integer.parseInt(request.getParameter("idbank")));
        if (b == null) {
            request.setAttribute("msg", "Invalid Bank. Choose Valid Bank...!");
            request.getRequestDispatcher("addbank.jsp").forward(request, response);
        } else {
            if (b.getBankManager() == null) {
                if (b.getSecCode().equals(request.getParameter("seccode"))) {
                    User u = (User) request.getSession().getAttribute("cuser");
                    if (u != null) {
                        BankManager bm = BankManager.getUserById(u.getIduser());
                        b.setBankManager(bm);
                        if (b.updateBank()) {
                            request.setAttribute("msg", "Manager Added to Bank");
                            request.getSession().setAttribute("utype", "bnk");
                        } else {
                            request.setAttribute("msg", "Unable to add manager, Try Again Later");
                        }
                        request.getRequestDispatcher("home.jsp").forward(request, response);
                    } else {
                        request.setAttribute("msg", "Invalid User to define as manager. Logout and Login to try again..");
                        request.getRequestDispatcher("addbank.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("msg", "Invalid Security Code. Try again..");
                    request.getRequestDispatcher("addbank.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("msg", "Manager Already Exist...!");
                request.getRequestDispatcher("addbank.jsp").forward(request, response);
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
