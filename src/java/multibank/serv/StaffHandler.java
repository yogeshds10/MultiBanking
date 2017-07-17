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
import multibank.entity.Branch;
import multibank.entity.Staff;
import multibank.entity.User;

/**
 *
 * @author client
 */
@WebServlet(name = "StaffHandler", urlPatterns = {"/StaffHandler"})
public class StaffHandler extends HttpServlet {

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
        if (User.getUserById(Integer.parseInt(request.getParameter("iduser"))).isBankManager()) {
            request.setAttribute("msg", "Unable to Add staff to branch, User is a Bank Manager and can't be a staff...!!");
            request.getRequestDispatcher("addstaff.jsp").forward(request, response);
        } else if (request.getParameter("ismanager").equals("true") && Branch.getBranchById(Integer.parseInt(request.getParameter("idbranch"))).getManager() != null) {
            request.setAttribute("msg", "Manager already exist for selected branch...!");
            request.getRequestDispatcher("addstaff.jsp").forward(request, response);
        } else {
            Staff s = new Staff();
            s.setIdbranch(Integer.parseInt(request.getParameter("idbranch")));
            s.setIduser(Integer.parseInt(request.getParameter("iduser")));
            s.setManager(Boolean.parseBoolean(request.getParameter("ismanager")));
            if (s.addStaff()) {
                request.setAttribute("msg", "Staff Added Successfully...!!");
                if (request.getParameter("own").equals("1")) {
                    request.getRequestDispatcher("mybranch.jsp").forward(request, response);
                } else if (request.getParameter("own").equals("2")) {
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("viewbranch.jsp?idbank=" + Branch.getBranchById(Integer.parseInt(request.getParameter("idbranch"))).getIdbank()).forward(request, response);
                }
            } else {
                request.setAttribute("msg", "Unable to Add staff to branch, Try Again Later...!!");
                request.getRequestDispatcher("addstaff.jsp").forward(request, response);
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
