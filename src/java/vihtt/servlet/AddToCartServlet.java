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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import vihtt.cart.cartObject;

/**
 *
 * @author Thanh Vi
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {
private final String SHOPPING_PAGE = "ShoppingServlet";
private static final Logger LOGGER = LogManager.getLogger(AddToCartServlet.class);
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
                  String urlRewriting = SHOPPING_PAGE;
                  String ID = request.getParameter("txtItemID");
                  String quantitySTR = request.getParameter("txtOderQuantity");
                  if(ID == null){
                           response.sendRedirect("404.html");
                           out.close();
                  }else{
                           try {
                                    int quantity = Integer.parseInt(quantitySTR);
                                    HttpSession session = request.getSession();
                                    cartObject cart = (cartObject) session.getAttribute("CUST_CART");
                                    if (cart == null) {
                                             cart = new cartObject();
                                    }
                                    cart.addItemToCard(ID,quantity);
                                    session.setAttribute("CUST_CART", cart);
                                    request.setAttribute("ADD_SUCCESS", true);
                           } catch (NumberFormatException e) {
                                    LOGGER.error("AddToCartServlet_NumberFormatException ", e);
                           } finally {
                                    RequestDispatcher rd = request.getRequestDispatcher(urlRewriting);
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
