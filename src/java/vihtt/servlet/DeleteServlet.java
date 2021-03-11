/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import vihtt.item.ItemDAO;
import vihtt.system.history.systemHistoryDAO;
import vihtt.system.history.systemHistoryDTO;

/**
 *
 * @author Thanh Vi
 */
@WebServlet(name = "DeleteServlet", urlPatterns = {"/DeleteServlet"})
@MultipartConfig()
public class DeleteServlet extends HttpServlet {
         private final String SHOPPING_PAGE = "ShoppingServlet";
         private final String ERROR_PAGE = "error.html";
         private static final Logger LOGGER = LogManager.getLogger(DeleteServlet.class);
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
                  response.setContentType("text/html;charset=UTF-8");
                  PrintWriter out = response.getWriter();
                  String url = ERROR_PAGE;           
                  String ID = request.getParameter("txtItemID");
                  if(ID==null){
                           response.sendRedirect("404.html");
                           out.close();
                  }else{
                           HttpSession session = request.getSession();
                           String email = (String)session.getAttribute("EMAIL");
                           LocalDateTime dateTime = java.time.LocalDateTime.now();
                           String image = request.getParameter("txtImage");
                           try {
                                  ItemDAO dao = new ItemDAO();
                                             boolean result = dao.deleteItem(ID);
                                             if (result) {
                                                      systemHistoryDTO systemHistoryDTO = new systemHistoryDTO(ID, email, "Delete", "" , dateTime,image);
                                                      systemHistoryDAO systemHistoryDAO = new systemHistoryDAO();
                                                      if(systemHistoryDAO.insertSystemHistory(systemHistoryDTO)){
                                                               url = SHOPPING_PAGE;
                                                      request.setAttribute("DELETE_SUCCESS", true);
                                                      }     
                                             }
                           } catch (SQLException e) {
                                    LOGGER.error("DeleteServlet_SQLException",e);
                           } catch (NamingException e) {
                                    LOGGER.error("DeleteSServlet_NameException" ,e);
                           } finally {
                                    RequestDispatcher rd = request.getRequestDispatcher(url);
                                    rd.forward(request, response);
                                    out.close();
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
