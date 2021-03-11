/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import vihtt.cart.cartObject;
import vihtt.item.ItemDAO;
import vihtt.item.ItemDTO;
import vihtt.item.ItemInCart;

/**
 *
 * @author Thanh Vi
 */
@WebServlet(name = "ViewCartServlet", urlPatterns = {"/ViewCartServlet"})
public class ViewCartServlet extends HttpServlet {
         private final String VIEW_PAGE="viewCart.jsp";
         private static final Logger LOGGER = LogManager.getLogger(ViewCartServlet.class);
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
                  String urlRewriting = VIEW_PAGE;
                  HttpSession session = request.getSession(false);
                  if(session.getAttribute("NAME")==null){
                           response.sendRedirect("404.html");
                           out.close();
                  }else{
                           if((boolean)session.getAttribute("ROLE_OF_USER")){
                                    response.sendRedirect("404.html");
                                    out.close();
                           }else{
                                    try {
                                             cartObject cart = (cartObject) session.getAttribute("CUST_CART");
                                             if (cart == null) {
                                                      cart = new cartObject();
                                             }
                                             Map<String, Integer> item = cart.getItems();
                                             if (item == null) {
                                                      item = new HashMap<>();
                                             }
                                             ItemDAO dao = new ItemDAO();
                                             List<ItemInCart> listItem = new ArrayList<>();
                                             int totalQuantity= 0;
                                             float totalPrice = 0;
                                             for (String itemID : item.keySet()) {
                                                      ItemDTO dto = dao.searchItemByID(itemID);
                                                      if (dto != null) {
                                                               ItemInCart ItemInCart  = new ItemInCart(dto.getItemID(), dto.getItemName(), dto.getCategoryID(), dto.getPrice(),
                                                                                          item.get(itemID), dto.getQuantity(), dto.getImage());
                                                               listItem.add(ItemInCart);
                                                               totalQuantity += item.get(itemID);
                                                               totalPrice += dto.getPrice()*item.get(itemID);
                                                      }
                                             }
                                             request.setAttribute("LIST_ITEM_IN_CART", listItem);
                                             request.setAttribute("TOTAL_QUANTITY", totalQuantity);
                                             request.setAttribute("TOTAL_PRICE", totalPrice);

                                    } catch (SQLException e) {
                                             LOGGER.error("ViewCartServlet_SQLException", e);
                                    } catch (NamingException e) {
                                             LOGGER.error("ViewCartServlet_NamingException", e);
                                    } finally {
                                             RequestDispatcher rd = request.getRequestDispatcher(urlRewriting);
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
