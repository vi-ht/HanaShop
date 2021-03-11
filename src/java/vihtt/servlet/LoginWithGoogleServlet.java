/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.servlet;

import vihtt.google.GoogleInfo;
import vihtt.user.UserDAO;
import vihtt.user.UserDTO;
import vihtt.utils.Constant;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.http.client.fluent.Request;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import vihtt.category.categoryDAO;
import vihtt.category.categoryDTO;

/**
 *
 * @author Thanh Vi
 */
@WebServlet(name = "LoginWithGoogleServlet", urlPatterns = {"/LoginWithGoogleServlet"})
public class LoginWithGoogleServlet extends HttpServlet {
         private static final Logger LOGGER = LogManager.getLogger(LoginWithGoogleServlet.class);
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
                  boolean role = false;
                  try {
                           String idToken = request.getParameter("idToken");
                           if (idToken != null && !idToken.isEmpty()) {
                                    String link = Constant.GOOGLE_LINK_GET_TOKEN + idToken;
                                    String jsonResponse = Request.Get(link).execute().returnContent().asString();
                                    GoogleInfo googleInfo = new Gson().fromJson(jsonResponse, GoogleInfo.class);
                                    if (googleInfo.isValid()) {
                                             String email = googleInfo.getEmail();
                                             UserDAO dao = new UserDAO();
                                             UserDTO result = dao.checkLoginByGoogle(email);
                                             if(result != null){
                                                      HttpSession session = request.getSession();
                                                      session.setAttribute("NAME", result.getFullname());
                                                      session.setAttribute("EMAIL", result.getEmail());
                                                      if (result.getRoleName().equals("Admin")) {
                                                               role = true;
                                                      }
                                                      session.setAttribute("ROLE_OF_USER", role);
                                                      categoryDAO categoryDAO = new categoryDAO();
                                                      categoryDAO.getAllCategory();
                                                      List<categoryDTO> listCategory = categoryDAO.getListCategory();
                                                      if (listCategory != null) {
                                                               request.setAttribute("LIST_CATEGORY", listCategory);
                                                      }
                                                      out.write("VALID");
                                             }else{
                                                      out.write("NOTVALID");
                                             }
                                    }
                           }
                  } catch (SQLException e) {
                            LOGGER.error("LoginWithGoogleServlet_SQLException", e);
                  } catch (NamingException ex) {
                            LOGGER.error("LoginWithGoogleServlet_NamingException", ex);
                  } finally{
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
