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
import vihtt.category.categoryDAO;
import vihtt.category.categoryDTO;
import vihtt.item.ItemDAO;
import vihtt.item.ItemDTO;
import vihtt.utils.Constant;

/**
 *
 * @author Thanh Vi
 */
@WebServlet(name = "ShoppingServlet", urlPatterns = {"/ShoppingServlet"})
public class ShoppingServlet extends HttpServlet {
         private final String SHOPPING_PAGE = "shopping.jsp";
         private static final Logger LOGGER = LogManager.getLogger(ShoppingServlet.class);
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
                  String url = SHOPPING_PAGE;
                  int pageNumber = 0;
                  String categoryID = request.getParameter("Category");
                  String pageNumberSTR = request.getParameter("txtPageNumber");
                  if(pageNumberSTR != null && !(pageNumberSTR.isEmpty())){
                           pageNumber = Integer.parseInt(pageNumberSTR);
                  }
                  HttpSession session = request.getSession();
                  boolean role = false;
                  if(session.getAttribute("ROLE_OF_USER")!=null){
                           role = (boolean)session.getAttribute("ROLE_OF_USER");
                  }
                  try {
                           categoryDAO categoryDAO = new categoryDAO();
                           categoryDAO.getAllCategory();
                           List<categoryDTO> listCategory = categoryDAO.getListCategory();
                           if (listCategory != null) {
                                    request.setAttribute("LIST_CATEGORY", listCategory);
                                    if(categoryID == null){
                                             categoryID = listCategory.get(0).getCategoryID();
                                    }
                           }
                           ItemDAO dao = new ItemDAO();
                           int sizeOfList;
                           if(role){
                                    sizeOfList = dao.getSizeOfListItem(categoryID);
                           }else{
                                    sizeOfList = dao.getSizeOfListItem(categoryID, true);
                           }
                           int totalPage = sizeOfList/Constant.PAGESIZE;
                           int remainderList = sizeOfList%Constant.PAGESIZE;
                           if(remainderList >0){
                                    totalPage += 1;
                           }
                           int offset = pageNumber * Constant.PAGESIZE;
                           int fetch = 0;
                           if(pageNumber<(totalPage-1)){
                                    fetch = Constant.PAGESIZE; 
                          }
                           if(pageNumber==(totalPage-1)){
                                    if(remainderList==0){
                                            fetch = Constant.PAGESIZE;
                                    }else{
                                             fetch = remainderList;
                                    }
                           }
                           if(role){
                                    dao.getItemByCategory(categoryID,offset, fetch);
                           }else{
                                    dao.getItemByCategory(categoryID, true, offset, fetch);
                           }
                           List<ItemDTO> listItem = dao.getItemList(); 
                           if (listItem != null) {
                                    request.setAttribute("ITEM", listItem);
                                    request.setAttribute("DISPLAY_CATEGORY", categoryID);
                                    request.setAttribute("PAGE_NUMBER", pageNumber);
                                    request.setAttribute("TOTAL_PAGE", totalPage);
                           }
                  }catch(SQLException e)  {
                           LOGGER.error("ShoppingServlet_SQLException", e);
                  }catch(NamingException ex)  {
                           LOGGER.error("ShoppingServlet_NamingException", ex);
                  }finally{
                           RequestDispatcher rd = request.getRequestDispatcher(url);
                           rd.forward(request, response);
                           out.close();
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
