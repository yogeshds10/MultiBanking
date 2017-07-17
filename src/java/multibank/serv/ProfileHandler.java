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
import multibank.entity.User;

/**
 *
 * @author client
 */
@WebServlet(name = "ProfileHandler", urlPatterns = {"/ProfileHandler"})
public class ProfileHandler extends HttpServlet {

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
        User u = new User();
        u.setAddress(request.getParameter("address"));
        u.setDob(request.getParameter("dob"));
        u.setEmail(request.getParameter("email"));
        u.setGender(Boolean.parseBoolean(request.getParameter("gender")));
        u.setMobile(request.getParameter("mobile"));
        u.setName(request.getParameter("name"));
        u.setUname(request.getParameter("uname"));
        u.setPasswd(((User)request.getSession().getAttribute("cuser")).getPasswd());
        u.setIduser(((User)request.getSession().getAttribute("cuser")).getIduser());
        if(u.updateUser()){
            request.getSession().setAttribute("cuser", u);
            request.setAttribute("msg", "Profile Updated Successfully...!");
        }else{
            request.setAttribute("msg", "Unable to update profile... Try Again Later..!");
        }
        request.getRequestDispatcher("profile.jsp").forward(request, response);
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
