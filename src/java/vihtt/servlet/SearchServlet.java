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
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
         private final String SEARCH_DETAIL_PAGE = "search.jsp";
         private static final Logger LOGGER = LogManager.getLogger(SearchServlet.class);
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
                  String url = SEARCH_DETAIL_PAGE;
                  HttpSession session = request.getSession();
                  int pageNumber = 0;
                  String pageNumberSTR = request.getParameter("txtPageNumber");
                  if(pageNumberSTR != null && !(pageNumberSTR.isEmpty())){
                           pageNumber = Integer.parseInt(pageNumberSTR);
                  }
                   boolean role = false;
                  if(session.getAttribute("ROLE_OF_USER")!=null){
                           role = (boolean)session.getAttribute("ROLE_OF_USER");
                  }
                  String searchValue = request.getParameter("txtSearchValue");
                  String categoryID = request.getParameter("cbxCategory");
                  String minPriceSTR = request.getParameter("txtMinPrice");
                  String maxPriceSTR = request.getParameter("txtMaxPrice");
                  if(searchValue == null){
                           searchValue="";
                  }
                  if(categoryID == null){
                           categoryID="";
                  }
                  if(minPriceSTR== null){
                           minPriceSTR="";
                  }
                  if(maxPriceSTR== null){
                           maxPriceSTR="";
                  }
                  boolean isSearchValueEmpty = searchValue.isEmpty();
                  boolean isCategoryEmpty = categoryID.isEmpty();
                  boolean isMinPriceSTREmpty = minPriceSTR.isEmpty();
                  boolean isMaxPriceSTREmpty = maxPriceSTR.isEmpty();
                  boolean foundError = false;
                  try {
                           categoryDAO categoryDAO = new categoryDAO();
                           categoryDAO.getAllCategory();
                           List<categoryDTO> listCategory = categoryDAO.getListCategory();
                           if (listCategory != null) {
                                    request.setAttribute("LIST_CATEGORY", listCategory);
                           }
                           if (!isMaxPriceSTREmpty && !isMinPriceSTREmpty) {
                                    float minPrice = Float.parseFloat(minPriceSTR);
                                    float maxPrice = Float.parseFloat(maxPriceSTR);
                                    if (maxPrice < minPrice) {
                                             foundError = true;
                                             request.setAttribute("ERROR", "Max price must be greater than min price!");
                                    }
                           }
                           if (isMaxPriceSTREmpty && !isMinPriceSTREmpty) {
                                    foundError = true;
                                    request.setAttribute("ERROR", "Please enter max price!");
                           }
                           if (!isMaxPriceSTREmpty && isMinPriceSTREmpty) {
                                    foundError = true;
                                    request.setAttribute("ERROR", "Please enter min price!");
                           }
                          if(!foundError){
                                    List<ItemDTO> listItem = new ArrayList<>();
                                    ItemDAO  itemDAO = new ItemDAO();
                                    int totalPage = 0;
                                    int sizeOfList = 0;
                                    if (isSearchValueEmpty && (isCategoryEmpty || categoryID.equals("All")) && isMinPriceSTREmpty && isMaxPriceSTREmpty) {
                                             request.setAttribute("SEARCH", true);
                                    }else if (!isSearchValueEmpty && (categoryID.equals("All")||isCategoryEmpty) && isMinPriceSTREmpty && isMaxPriceSTREmpty) {
                                             if(role){sizeOfList =  itemDAO.getSizeOfListSearch(searchValue);}
                                             else{sizeOfList = itemDAO.getSizeOfListSearch(searchValue,true);}
                                             totalPage = sizeOfList/Constant.PAGESIZE;
                                             int remainderList = sizeOfList%Constant.PAGESIZE;
                                             if(remainderList >0){totalPage += 1;}
                                             int offset = pageNumber * Constant.PAGESIZE;
                                             int fetch = 0;
                                             if(pageNumber<(totalPage-1)){fetch = Constant.PAGESIZE;}
                                             if(pageNumber==(totalPage-1)){
                                                      if(remainderList==0){fetch = Constant.PAGESIZE;}
                                                      else{fetch = remainderList;}
                                             }
                                             if(role){
                                                      itemDAO.searchItem(searchValue,offset, fetch);
                                             }else{
                                                      itemDAO.searchItem(searchValue,true, offset, fetch);
                                             }
                                             listItem=itemDAO.getItemList();
                                    } else if (!categoryID.equals("All") && isMinPriceSTREmpty && isMaxPriceSTREmpty) {
                                             if(role){sizeOfList =  itemDAO.getSizeOfListSearch(searchValue, categoryID);}
                                             else{sizeOfList = itemDAO.getSizeOfListSearch(searchValue, categoryID, true);}
                                             totalPage = sizeOfList/Constant.PAGESIZE;
                                             int remainderList = sizeOfList%Constant.PAGESIZE;
                                             if(remainderList >0){totalPage += 1;}
                                             int offset = pageNumber * Constant.PAGESIZE;
                                             int fetch = 0;
                                             if(pageNumber<(totalPage-1)){fetch = Constant.PAGESIZE;}
                                             if(pageNumber==(totalPage-1)){
                                                      if(remainderList==0){fetch = Constant.PAGESIZE;}
                                                      else{fetch = remainderList;}
                                             }
                                             if(role){
                                                      itemDAO.searchItem(searchValue, categoryID,offset, fetch);
                                             }else{
                                                      itemDAO.searchItem(searchValue, categoryID, true,offset, fetch);
                                             }
                                             listItem=itemDAO.getItemList();
                                    } else if (categoryID.equals("All") && !isMinPriceSTREmpty && !isMaxPriceSTREmpty) {
                                             int minPrice = Integer.parseInt(minPriceSTR);
                                             int maxPrice = Integer.parseInt(maxPriceSTR);
                                             if(role){sizeOfList =  itemDAO.getSizeOfListSearch(searchValue, minPrice, maxPrice);}
                                             else{sizeOfList = itemDAO.getSizeOfListSearch(searchValue, minPrice, maxPrice, true);}
                                             totalPage = sizeOfList/Constant.PAGESIZE;
                                             int remainderList = sizeOfList%Constant.PAGESIZE;
                                             if(remainderList >0){totalPage += 1;}
                                             int offset = pageNumber * Constant.PAGESIZE;
                                             int fetch = 0;
                                             if(pageNumber<(totalPage-1)){fetch = Constant.PAGESIZE;}
                                             if(pageNumber==(totalPage-1)){
                                                      if(remainderList==0){fetch = Constant.PAGESIZE;}
                                                      else{fetch = remainderList;}
                                             }
                                             if(role){
                                                      itemDAO.searchItem(searchValue, minPrice, maxPrice, offset, fetch);
                                             }else{
                                                      itemDAO.searchItem(searchValue, minPrice, maxPrice, true, offset, fetch);
                                             }
                                             listItem=itemDAO.getItemList();
                                    }else {
                                             int minPrice = Integer.parseInt(minPriceSTR);
                                             int maxPrice = Integer.parseInt(maxPriceSTR);
                                             if(role){sizeOfList =  itemDAO.getSizeOfListSearch(searchValue, categoryID, minPrice, maxPrice);}
                                             else{sizeOfList = itemDAO.getSizeOfListSearch(searchValue, categoryID, minPrice, maxPrice, true);}
                                             totalPage = sizeOfList/Constant.PAGESIZE;
                                             int remainderList = sizeOfList%Constant.PAGESIZE;
                                             if(remainderList >0){totalPage += 1;}
                                             int offset = pageNumber * Constant.PAGESIZE;
                                             int fetch = 0;
                                             if(pageNumber<(totalPage-1)){fetch = Constant.PAGESIZE;}
                                             if(pageNumber==(totalPage-1)){
                                                      if(remainderList==0){fetch = Constant.PAGESIZE;}
                                                      else{fetch = remainderList;}
                                             }
                                             if(role){
                                                      itemDAO.searchItem(searchValue, categoryID, minPrice, maxPrice, offset, fetch);
                                             }else{
                                                      itemDAO.searchItem(searchValue, categoryID, minPrice, maxPrice, true, offset, fetch);
                                             }
                                             listItem=itemDAO.getItemList();
                                    }
                                    if (listItem != null) {
                                             request.setAttribute("SEARCH_RESULT", listItem);
                                             request.setAttribute("PAGE_NUMBER", pageNumber);
                                             request.setAttribute("TOTAL_PAGE", totalPage-1);
                                    }
                           }
                  }catch(NumberFormatException e)  {
                           LOGGER.error("SearchServlet_NumberFormatException", e);
                  }catch(SQLException e)  {
                           LOGGER.error("SearchServlet_SQLException ", e);
                  }catch(NamingException ex)  {
                           LOGGER.error("SearchServlet_NamingException ", ex);
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
