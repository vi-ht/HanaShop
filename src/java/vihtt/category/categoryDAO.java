/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.category;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import vihtt.utils.DBHelper;

/**
 *
 * @author Thanh Vi
 */
public class categoryDAO implements Serializable{
         List<categoryDTO> listCategory;

         public List<categoryDTO> getListCategory() {
                  return listCategory;
         }
         
         public void getAllCategory()
            throws SQLException, NamingException{
                  Connection con = null;
                  Statement sm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                          if (con!=null) {
                                    String sql =  "Select categoryID, categoryName, icon  "
                                                        + "from tblCategory ";   
                          sm = con.createStatement();
                          rs = sm.executeQuery(sql);
                                    while(rs.next()) {
                                             String categoryID = rs.getString("categoryID");
                                             String categoryName = rs.getString("categoryName");
                                             String icon = rs.getString("icon");
                                             categoryDTO dto = new categoryDTO(categoryID, categoryName, icon);
                                             if(this.listCategory == null){
                                                      listCategory = new ArrayList<>();
                                             }
                                             listCategory.add(dto);
                                    }
                          }
                  } finally {
                      if (rs != null) {rs.close();}
                      if (sm != null) {sm.close();}
                      if (con != null) {con.close();}
                  }
    }
         
         public String getCategoryID(String cateName)
            throws SQLException, NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                          if (con!=null) {
                                    String sql =  "Select categoryID  "
                                                        + "from tblCategory "
                                                        + "Where categoryName = ?";   
                          prm = con.prepareStatement(sql);
                          prm.setString(1, cateName);
                          rs = prm.executeQuery();
                                    if (rs.next()) {
                                             String categoryID = rs.getString("categoryID");
                                             return categoryID;
                                    }
                          }
                  } finally {
                      if (rs != null) {rs.close();}
                      if (prm != null) {prm.close();}
                      if (con != null) {con.close();}
                  }
                  return null;
    }
         
         public String getCategoryName(String cateID)
            throws SQLException, NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                          if (con!=null) {
                                    String sql =  "Select categoryName  "
                                                        + "from tblCategory "
                                                        + "Where categoryID = ?";   
                          prm = con.prepareStatement(sql);
                          prm.setString(1, cateID);
                          rs = prm.executeQuery();
                                    if (rs.next()) {
                                             String categoryID = rs.getString("categoryName");
                                             return categoryID;
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





