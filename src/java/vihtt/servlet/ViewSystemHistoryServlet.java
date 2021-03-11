/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import vihtt.system.history.systemHistoryDAO;
import vihtt.system.history.systemHistoryToView;

/**
 *
 * @author Thanh Vi
 */
@WebServlet(name = "ViewSystemHistoryServlet", urlPatterns = {"/ViewSystemHistoryServlet"})
public class ViewSystemHistoryServlet extends HttpServlet {
         private final String ERROR_PAGE = "error.html";
         private final String VIEW_SYSTEM_HISTORY_PAGE = "viewSystemHistory.jsp";
         private static final Logger LOGGER = LogManager.getLogger(ViewSystemHistoryServlet.class);
         /**
          * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
          * methods.
          *
          * @param request servlet request
          * @param response servlet response
          * @throws ServletException if a servlet-specific error occurs
          * @throws IOException if an I/O error occurs
          */
         protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
                  String url = ERROR_PAGE;
                  PrintWriter out = response.getWriter();
                  HttpSession session = request.getSession();
                  if(session.getAttribute("NAME")==null){
                           response.sendRedirect("404.html");
                           out.close();
                  }else{
                           if(!(boolean)session.getAttribute("ROLE_OF_USER")){
                                    response.sendRedirect("404.html");
                                    out.close();
                           }else{
                                    try{
                                             systemHistoryDAO dao = new systemHistoryDAO();
                                             dao.getSystemHistory();
                                             List<systemHistoryToView> listSystemHistory = dao.getSystemHistoryList();
                                             if(listSystemHistory != null){
                                                      
                                                      request.setAttribute("SYSTEM_HISTORY", listSystemHistory);
                                             }
                                             url = VIEW_SYSTEM_HISTORY_PAGE;
                                    }catch(SQLException e)  {
                                            LOGGER.error("ViewSystemHistoryServlet_SQLException", e);
                                             url = ERROR_PAGE;
                                   }catch(NamingException e)  {
                                             LOGGER.error("ViewSystemHistoryServlet_NamingException", e);
                                              url = ERROR_PAGE;
                                  }finally{
                                            RequestDispatcher rd = request.getRequestDispatcher(url);
                                            rd.forward(request, response);
                                            out.close();
                                   }
                           }
                  }
         }

         // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
         /**
          * Handles the HTTP <code>GET</code> method.
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
          * Handles the HTTP <code>POST</code> method.
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
