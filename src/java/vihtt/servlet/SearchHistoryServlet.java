/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
import vihtt.order.orderDAO;
import vihtt.order.orderHistory;

/**
 *
 * @author Thanh Vi
 */
@WebServlet(name = "SearchHistoryServlet", urlPatterns = {"/SearchHistoryServlet"})
public class SearchHistoryServlet extends HttpServlet {
         private final String ERROR_PAGE = "error.html";
         private final String VIEW_HISTORY_PAGE = "searchUserHistory.jsp";
         private static final Logger LOGGER = LogManager.getLogger(SearchHistoryServlet.class);
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
                  String url = ERROR_PAGE;
                  PrintWriter out = response.getWriter();
                  HttpSession session = request.getSession();
                  String email = (String)session.getAttribute("EMAIL");
                  String searchValue = request.getParameter("txtSearchHistory");
                  String dateSTR = request.getParameter("txtDate");
                  LocalDateTime minDateTime=null, maxDateTime = null;
                  if(searchValue == null){
                            response.sendRedirect("404.html");
                            out.close();
                  }else{
                           try{ 
                                    if(dateSTR == null || dateSTR.isEmpty()){
                                             dateSTR = "";
                                    }else{
                                             LocalDate date = LocalDate.parse(dateSTR);
                                             minDateTime = date.atTime(00,00,00);
                                             maxDateTime = date.atTime(23,59,59);
                                    }
                                    orderDAO dao = new orderDAO();
                                    if(!searchValue.isEmpty() && dateSTR.isEmpty()){
                                             dao.searchHistorybyItemName(searchValue, email);
                                    }else if(searchValue.isEmpty() && !dateSTR.isEmpty()){
                                             dao.searchHistoryByDate(minDateTime, maxDateTime);
                                    }else if(!searchValue.isEmpty() && !dateSTR.isEmpty()){
                                            dao.searchHistory(minDateTime, maxDateTime, searchValue, email);
                                    }
                                    List<orderHistory> list = dao.getListOrder();
                                             url = VIEW_HISTORY_PAGE;
                                             request.setAttribute("HISTORY", list);
                           }catch(SQLException e)  {
                                   LOGGER.error("SearchHistoryServlet_SQLException", e);
                                    url = ERROR_PAGE;
                          }catch(NamingException e)  {
                                    LOGGER.error("SearchHistoryServlet_NamingException", e);
                                    url = ERROR_PAGE;
                           } catch (DateTimeParseException e ) {
                                     LOGGER.error("SearchHistoryServlet_DateTimeParseException", e);
                                    request.setAttribute("DATE_ERROR", true);
                                    url = VIEW_HISTORY_PAGE;
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
