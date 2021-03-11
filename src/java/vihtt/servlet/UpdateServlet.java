/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import vihtt.category.categoryDAO;
import vihtt.category.categoryDTO;
import vihtt.item.ItemDAO;
import vihtt.item.ItemDTO;
import vihtt.system.history.systemHistoryDAO;
import vihtt.system.history.systemHistoryDTO;
import vihtt.utils.UploadImage;

/**
 *
 * @author Thanh Vi
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
@MultipartConfig()
public class UpdateServlet extends HttpServlet {
         private final String SHOPPING_PAGE = "ShoppingServlet";
         private final String UPDATE_PAGE = "GetInfoToUpdateServlet";
         private final String ERROR_PAGE = "error.html";
         private static final Logger LOGGER = LogManager.getLogger(UpdateServlet.class);
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
                  HttpSession session = request.getSession();
                  String email = (String)session.getAttribute("EMAIL");
                  String ID = request.getParameter("txtItemID");
                  if(ID==null){
                           response.sendRedirect("404.html");
                           out.close();
                  }else{
                           String name = request.getParameter("txtProductName");
                           String description = request.getParameter("txtDescription");
                           String quantitySTR = request.getParameter("txtQuantity");
                           String priceSTR = request.getParameter("txtPrice");
                           String categoryID = request.getParameter("cbxCategory");
                           String statusSTR = request.getParameter("cbxStatus");
                           boolean status = false;
                           LocalDate createDate = java.time.LocalDate.now();
                           LocalDateTime dateTime = java.time.LocalDateTime.now();
                           Part image = request.getPart("imgFile");
                           String fileName = request.getParameter("txtImage");
                           String realPath = request.getServletContext().getRealPath("/") + "Image";
                           float price = Float.parseFloat(priceSTR);
                           int quantity = Integer.parseInt(quantitySTR);
                           if(statusSTR.equals("Active")){
                                    status = true;
                           }
                           File productImage = null;
                           if (image.getSize() > 0) {
                                    productImage = UploadImage.uploadFile(image, realPath);
                                    fileName = productImage.getName();
                           }
                           if (fileName == null) {
                                    fileName = "";
                           }
                           request.setAttribute("IMAGE", fileName);
                           try {
                                    List<categoryDTO> listCategory = new ArrayList<>();
                                    categoryDAO categoryDAO = new categoryDAO();
                                    categoryDAO.getAllCategory();
                                    listCategory = categoryDAO.getListCategory();
                                    if (listCategory != null) {
                                             request.setAttribute("LIST_CATEGORY", listCategory);
                                    }
                                             ItemDAO dao = new ItemDAO();
                                             if(quantity == 0){
                                                      status = false;
                                             }
                                             ItemDTO dto = new ItemDTO(ID, name, description, categoryID, price, quantity, fileName, createDate, status);
                                             ItemDTO dtoCheckDuplicated = dao.searchItemByID(ID);
                                             if(name.equals(dtoCheckDuplicated.getItemName()) && description.equals(dtoCheckDuplicated.getDiscription())
                                                     && price == dtoCheckDuplicated.getPrice() && categoryID.equals(dtoCheckDuplicated.getCategoryID()) 
                                                     && quantity == dtoCheckDuplicated.getQuantity() && fileName.equals(dtoCheckDuplicated.getImage()) && status == dtoCheckDuplicated.isStatus()){
                                                      request.setAttribute("NO_MODIFY_UPDATE", true);
                                                      url = UPDATE_PAGE;
                                                      request.setAttribute("QUESTION_DETAILS", dtoCheckDuplicated);
                                             }else{

                                                      boolean result = dao.updateItem(dto);
                                                      if (result) {
                                                               String categoryName = categoryDAO.getCategoryName(categoryID);
                                                               String contentDetails = dto.toString()+", category name = "+categoryName;
                                                               systemHistoryDTO systemHistoryDTO = new systemHistoryDTO(ID, email, "Update", contentDetails , dateTime, fileName);
                                                               systemHistoryDAO systemHistoryDAO = new systemHistoryDAO();
                                                               if(systemHistoryDAO.insertSystemHistory(systemHistoryDTO)){
                                                                        url = SHOPPING_PAGE;
                                                                        request.setAttribute("UPDATE_SUCCESS", true);
                                                               }
                                                      }
                                             }
                           } catch (SQLException e) {
                                    LOGGER.error("UpdateServlet_SQL",e);
                                    url = ERROR_PAGE;
                           } catch (NamingException e) {
                                    LOGGER.error("UpdateServlet_Name",e);
                                    url = ERROR_PAGE;
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
