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
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vihtt.cart.cartObject;
import vihtt.item.ItemDAO;
import vihtt.item.ItemDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import vihtt.order.orderDAO;
import vihtt.order.orderDTO;
import vihtt.orderdetail.orderDetailsDAO;

/**
 *
 * @author Thanh Vi
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {
         private final String ERROR_PAGE = "error.html";
         private final String SUCCESS_PAGE = "checkoutSuccess.jsp";
         private final String VIEW_CART_PAGE="ViewCartServlet";
         private static final Logger LOGGER = LogManager.getLogger(CheckoutServlet.class);
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
                  HttpSession session = request.getSession(false);
                  String custName = request.getParameter("txtCustName");
                  String custAddress = request.getParameter("txtCustAddress");
                  String custPhone = request.getParameter("txtCustPhone");
                  String totalPriceSTR = request.getParameter("txtTotalPrice");
                  String totalQuantitySTR = request.getParameter("txtTotalQuantity");
                  String email = (String)session.getAttribute("EMAIL");
                  if(custName==null){
                           response.sendRedirect("404.html");
                           out.close();
                  }else{
                           String url = ERROR_PAGE;
                           LocalDateTime orderDateTime = java.time.LocalDateTime.now();
                           boolean insertOrderDetails = false;
                           boolean test = false;
                           String orderID = RandomStringUtils.randomAlphanumeric(8);
                           try {
                                    float totalPrice = Float.parseFloat(totalPriceSTR);
                                    int totalQuantity = Integer.parseInt(totalQuantitySTR);
                                    cartObject cart = (cartObject)session.getAttribute("CUST_CART");
                                    orderDAO orderDAO = new orderDAO();
                                    orderDetailsDAO orderDetailsDAO = new orderDetailsDAO();
                                    ItemDAO itemDAO = new ItemDAO();
                                    boolean duplicated = orderDAO.checkDuplicatedOrderID(orderID);
                                    while(duplicated==true){
                                             orderID = RandomStringUtils.randomAlphanumeric(8);
                                             duplicated = orderDAO.checkDuplicatedOrderID(orderID);
                                    }
                                    if(cart != null){
                                             Map<String, Integer> items = cart.getItems();
                                             if(items != null){
                                                      for (String ID : items.keySet()) {
                                                               ItemDTO dto = itemDAO.searchItemByID(ID);
                                                               if (dto != null) {
                                                                        if (items.get(ID) <= dto.getQuantity()) {
                                                                                 test = false;
                                                                        } else if (dto.getQuantity() == 0) {
                                                                                 cart.removeItemFormCard(ID);
                                                                                 session.setAttribute("CUST_CART", cart);
                                                                                 request.setAttribute("SOLD_OUT_PRODUCT", dto.getItemName());
                                                                                 request.setAttribute("SOLD_OUT", true);

                                                                                 test = true;
                                                                                 break;
                                                                        } else {
                                                                                 cart.addQuantityToCard(ID, dto.getQuantity());
                                                                                 session.setAttribute("CUST_CART", cart);
                                                                                 request.setAttribute("SOLD_OUT_PRODUCT", dto.getItemName());
                                                                                 request.setAttribute("REMAINING_NUMBER", dto.getQuantity());
                                                                                 request.setAttribute("FAIL", true);
                                                                                 test = true;
                                                                                 break;
                                                                        }
                                                               }
                                                      }
                                                      if(!test){
                                                               orderDTO orderDTO = new orderDTO(orderID, totalPrice, totalQuantity, custName, custAddress, custPhone, orderDateTime, email, true);
                                                               boolean result = orderDAO.insertOrder(orderDTO);
                                                               if (result) {
                                                                        for (String ID : items.keySet()) {
                                                                                 ItemDTO dto = itemDAO.searchItemByID(ID);
                                                                                 if (dto != null) {
                                                                                          if (items.get(ID) <= dto.getQuantity()) {
                                                                                                   insertOrderDetails = orderDetailsDAO.insertOrderDetails(orderID, dto.getItemID(), items.get(ID));
                                                                                                   boolean updateQuantity = itemDAO.updateQuantityOfItem(dto.getItemID(), dto.getQuantity()-items.get(ID));
                                                                                                   if (!insertOrderDetails) {
                                                                                                            if(!updateQuantity){
                                                                                                                     break;
                                                                                                            }            
                                                                                                   }
                                                                                          }
                                                                                 }
                                                                        }
                                                                        if(insertOrderDetails){
                                                                                 session.removeAttribute("CUST_CART");
                                                                                 request.setAttribute("TOTAL_PRICE", totalPriceSTR);
                                                                                 request.setAttribute("ORDER_ID", orderID);
                                                                                 url = SUCCESS_PAGE;
                                                                        }
                                                               }
                                                      }
                                                      else{
                                                               url = VIEW_CART_PAGE;
                                                      }
                                             }
                               }
                          }catch(SQLException e)  {
                                   LOGGER.error("CheckoutServlet_SQLException", e);
                          }catch(NamingException e)  {
                                    LOGGER.error("CheckoutServlet_NamingException", e);
                              }catch(NumberFormatException e)  {
                                    LOGGER.error("CheckoutServlet_NumberFormatException", e);
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
