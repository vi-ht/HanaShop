/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.system.history;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import vihtt.utils.DBHelper;

/**
 *
 * @author Thanh Vi
 */
public class systemHistoryDAO implements Serializable{
         
         List<systemHistoryToView> list;
         
         public List<systemHistoryToView> getSystemHistoryList(){
                  return list;
         }
         
         public boolean insertSystemHistory(systemHistoryDTO dto) throws SQLException, NamingException {
                  Connection con = null;
                  PreparedStatement prm = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                    String sql = "Insert tblSystemHistory (itemID, email, updateDate, content, contentDetail, image) "
                                            + "Values(?,?,?,?,?,?)";
                                    prm = con.prepareStatement(sql);
                                    prm.setString(1, dto.getItemID());
                                    prm.setString(2, dto.getEmail());
                                    prm.setString(3, Timestamp.valueOf(dto.getUpdateDate()).toString());
                                    prm.setString(4, dto.getContent());
                                    prm.setString(5, dto.getContentDetail());
                                    prm.setString(6, dto.getImage());
                                    int row = prm.executeUpdate();System.out.println("gh4555hhhh");
                                    if (row > 0) {
                                             System.out.println("ghhhhh");
                                             return true;
                                    }
                           }
                           }finally {
                                    if (prm != null) {prm.close();}
                                    if (con != null) {con.close();}
                          }
                           return false;
                  }
         
         public void getSystemHistory() throws SQLException , NamingException{
                  Connection con = null;
                  Statement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                    String sql = "Select itemID, updateDate, content, contentDetail, image, u.fullname  "
                                            + "From tblSystemHistory h, (SELECT fullname, email "
                                            + "                                               FROM tblUser )u "
                                            + "WHERE h.email = u.email "
                                            + "ORDER BY updateDate DESC ";
                                    prm = con.createStatement();
                                    rs = prm.executeQuery(sql);
                                    while (rs.next()) {
                                             String itemID = rs.getString("itemID");
                                             String name = rs.getString("fullname"); System.out.println(name);
                                             String updateDate = rs.getString("updateDate");
                                             String content = rs.getString("content");
                                             String contentDetail = rs.getString("contentDetail");
                                             String image = rs.getString("image");
                                             systemHistoryToView systemHistoryToView = new systemHistoryToView(itemID, name, content, contentDetail, updateDate, image);
                                             if(list == null){
                                                      list = new ArrayList<>();
                                             }
                                             list.add(systemHistoryToView);
                                    }
                           }
                  } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
         }
}
