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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import vihtt.category.categoryDAO;
import vihtt.item.ItemDAO;
import vihtt.item.ItemDTO;

/**
 *
 * @author Thanh Vi
 */
@WebServlet(name = "ViewDetailsServlet", urlPatterns = {"/ViewDetailsServlet"})
public class ViewDetailsServlet extends HttpServlet {
private final String VIEW_DETAILS_PAGE="itemDetails.jsp";
private static final Logger LOGGER = LogManager.getLogger(ViewDetailsServlet.class);
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
                  String ItemID = request.getParameter("txtItemID");
                  String url = VIEW_DETAILS_PAGE;
                  PrintWriter out = response.getWriter();
                  if(ItemID==null){
                           response.sendRedirect("404.html");
                           out.close();
                  }else{
                           try  {
                                    ItemDAO itemDAO = new ItemDAO();
                                    ItemDTO itemDTO = itemDAO.searchItemByID(ItemID);
                                    itemDAO.getRecomendProduct(ItemID);
                                    List<ItemDTO> recommendProductList = itemDAO.getItemList();
                                    categoryDAO categoryDAO = new categoryDAO();
                                    String categoryID = categoryDAO.getCategoryName(itemDTO.getCategoryID());
                                    if(categoryID != null){
                                             request.setAttribute("ITEM_DETAILS", itemDTO);
                                             request.setAttribute("CATEGORY_ID", categoryID);
                                             request.setAttribute("RECOMMEND_PRODUCT", recommendProductList);
                                    }
                           }catch(SQLException e)  {
                                    LOGGER.error("ViewDetailsServlet_SQLException", e);
                           }catch(NamingException ex)  {
                                    LOGGER.error("ViewDetailsServlet_NamingException", ex);
                           }finally{
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
