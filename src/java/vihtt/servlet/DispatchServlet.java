/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thanh Vi
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
@MultipartConfig()
public class DispatchServlet extends HttpServlet {
private  final  String LOGIN_CONTROLLER = "LoginServlet";
private  final  String LOGIN_PAGE = "login.jsp";
private  final  String SEARCH_CONTROLLER = "SearchServlet";
private  final  String VIEW_DETAILS_CONTROLLER = "ViewDetailsServlet"; 
private  final  String CREATE_NEW_PRODUCT_CONTROLLER = "CreateNewProductServlet"; 
private  final String UPDATE_PAGE = "UpdateServlet";
private final String GET_INFO_TO_UPDATE_CONTROLLER = "GetInfoToUpdateServlet";
private final String SHOPPING_CONTROLLER = "ShoppingServlet";
private final String CONFIRM_DELETE_PAGE = "confirmDelete.jsp";
private final String DELETE_CONTROLLER = "DeleteServlet";
private final String ADD_TO_CART_CONTROLLER = "AddToCartServlet";
private final String REMOVE_ITEM_CONTROLLER = "RemoveItemServlet";
private final String VIEW_CART_CONTROLLER = "ViewCartServlet";
private final String ADD_QUANTITY_CONTROLLER = "AddQuantityServlet";
private final String SUBTRACT_QUANTITY_CONTROLLER = "SubtractQuantityServlet";
private final String CHECKOUT_CONTROLLER = "CheckoutServlet";
private final String VIEW_HISTORY_DETAILS_CONTROLLER = "ViewHistoryDetailsServlet";
private final String SEARCH_HISTORY_CONTROLLER = "SearchHistoryServlet";
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
                  HttpSession session = request.getSession();
                  String url = LOGIN_PAGE;
                  String button = request.getParameter("btnAction");
                  try {
                          if(button == null){
                          }else if(button.equals("Login")){
                                   url = LOGIN_CONTROLLER;
                          }
                          else if(button.equals("Search")){
                                   url = SEARCH_CONTROLLER;
                          }
                          else if(button.equals("View Details")){
                                   url = VIEW_DETAILS_CONTROLLER;
                          }
                          else if(button.equals("Create New Product")){
                                   url = CREATE_NEW_PRODUCT_CONTROLLER;
                          }
                          else if(button.equals("Update Product")){
                                   url = UPDATE_PAGE;
                          }
                          else if(button.equals("Update")){
                                   url = GET_INFO_TO_UPDATE_CONTROLLER;
                          }
                          else if(button.equals("Shopping")){
                                   url = SHOPPING_CONTROLLER;
                          }
                           else if(button.equals("Delete")){
                                    url = url = DELETE_CONTROLLER; 
                          }
                           else if(button.equals("Add To Cart")){
                                    url = ADD_TO_CART_CONTROLLER;
                          }
                          else if(button.equals("Remove")){
                                   url = REMOVE_ITEM_CONTROLLER;
                          }
                          else if(button.equals("addQuantity")){
                                   url = ADD_QUANTITY_CONTROLLER;
                          }
                          else if(button.equals("subtractQuantity")){
                                   url = SUBTRACT_QUANTITY_CONTROLLER;
                          }
                          else if(button.equals("Check out")){
                                   url = CHECKOUT_CONTROLLER;
                          }
                          else if(button.equals("History Details")){
                                   url = VIEW_HISTORY_DETAILS_CONTROLLER;
                          }
                          else if(button.equals("Search History")){
                                   url = SEARCH_HISTORY_CONTROLLER;
                          }
                          
                  } finally{
                          RequestDispatcher RD = request.getRequestDispatcher(url);
                          RD.forward(request, response);
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
