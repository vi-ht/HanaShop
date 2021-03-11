/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import vihtt.role.roleDAO;
import vihtt.utils.DBHelper;

/**
 *
 * @author Thanh Vi
 */
public class UserDAO implements Serializable{
         public UserDTO checkLogin(String e, String pw)
            throws SQLException, NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                          if (con!=null) {
                                    String sql =  "Select email, password, fullname, roleID  "
                                                        + "from tblUser "
                                                        + "Where email  = ? And password = ? And status = ?";   
                          prm = con.prepareStatement(sql);
                          prm.setString(1, e);
                          prm.setString(2, pw);
                          prm.setBoolean(3, true);
                          rs = prm.executeQuery();
                                    if (rs.next()) {
                                             String email = rs.getString("email");
                                             String password = rs.getString("password");
                                             String fullname = rs.getString("fullname");
                                             String roleID = rs.getString("roleID");
                                             roleDAO roleDao = new roleDAO();
                                             String roleName = roleDao.getRoleName(roleID);
                                             if(roleName == null){
                                                      return null;
                                             }
                                             UserDTO dto = new UserDTO(email, password, fullname, roleName);
                                             return dto;
                                    }
                          }
                  } finally {
                      if (rs != null) {rs.close();}
                      if (prm != null) {prm.close();}
                      if (con != null) {con.close();}
                  }
                  return null;
    }

public UserDTO checkLoginByGoogle(String emailAdress)
            throws SQLException, NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                          if (con!=null) {
                                    String sql =  "Select email, password, fullname, roleID  "
                                                        + "from tblUser "
                                                        + "Where email  = ? And status = ? ";   
                          prm = con.prepareStatement(sql);
                          prm.setString(1, emailAdress);
                          prm.setBoolean(2, true);
                          rs = prm.executeQuery();
                                    if (rs.next()) {
                                             String email = rs.getString("email");
                                             String password = rs.getString("password");
                                             String fullname = rs.getString("fullname");
                                             String roleID = rs.getString("roleID");
                                             roleDAO roleDao = new roleDAO();
                                             String roleName = roleDao.getRoleName(roleID);
                                             if(roleName == null){
                                                      return null;
                                             }
                                             UserDTO dto = new UserDTO(email, password, fullname, roleName);
                                             return dto;
                                    }
                          }
                  } finally {
                      if (rs != null) {rs.close();}
                      if (prm != null) {prm.close();}
                      if (con != null) {con.close();}
                  }
                  return null;
    }


}

