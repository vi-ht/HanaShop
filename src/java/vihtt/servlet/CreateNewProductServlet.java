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
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import vihtt.category.categoryDAO;
import vihtt.category.categoryDTO;
import vihtt.item.ItemDAO;
import vihtt.item.ItemDTO;
import vihtt.utils.UploadImage;

/**
 *
 * @author Thanh Vi
 */
@WebServlet(name = "CreateNewProductServlet", urlPatterns = {"/CreateNewProductServlet"})
@MultipartConfig()
public class CreateNewProductServlet extends HttpServlet {
         private final String SUCCESS_PAGE = "createSuccess.html";
         private final String CREATE_PAGE = "createNewProduct.jsp";
         private final String ERROR_PAGE = "error.html";
         private static final Logger LOGGER = LogManager.getLogger(CreateNewProductServlet.class);
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
                  String fileName = request.getParameter("txtFileName");
                  String ID = request.getParameter("txtProductID");
                  String name = request.getParameter("txtProductName");
                  String description = request.getParameter("txtDescription");
                  String quantitySTR = request.getParameter("txtQuantity");
                  String priceSTR = request.getParameter("txtPrice");
                  String categoryID = request.getParameter("cbxCategory");
                  if(ID==null){
                           response.sendRedirect("404.html");
                           out.close();
                  }else{
                           LocalDate createDate = java.time.LocalDate.now();
                           Part image = request.getPart("imgFile");
                           String realPath = request.getServletContext().getRealPath("/") + "Image";
                           float price = Float.parseFloat(priceSTR);
                           int quantity = Integer.parseInt(quantitySTR);       
                           if (fileName == null) {
                                    if (image.getSize() > 0) {
                                    File productImage = UploadImage.uploadFile(image, realPath);
                                    fileName = productImage.getName();
                                    }
                                    if (fileName == null) {
                                             fileName = "";
                                    }
                           }
                           request.setAttribute("IMAGE", fileName);
                           try {
                                    categoryDAO categoryDAO = new categoryDAO();
                                    categoryDAO.getAllCategory();
                                    List<categoryDTO> listCategory = categoryDAO.getListCategory();
                                    if (listCategory != null) {
                                             request.setAttribute("LIST_CATEGORY", listCategory);
                                    }
                                             ItemDAO dao = new ItemDAO();
                                             ItemDTO dto = new ItemDTO(ID, name, description, categoryID, price, quantity, fileName, createDate, true);
                                             boolean result = dao.ceateNewProduct(dto);
                                             if (result) {
                                                      url = SUCCESS_PAGE;
                                             }
                           } catch (SQLException e) {
                                    String errMess = e.getMessage();
                                    LOGGER.error("CreateNewProductServlet_SQLException", e);
                                    if (errMess.contains("duplicate")) {
                                             request.setAttribute("ERRORS", ID + " is existed!");
                                             url = CREATE_PAGE;
                                    }
                           } catch (NamingException e) {
                                    LOGGER.error("CreateNewProductServlet_NamingException", e);
                           }finally {
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
