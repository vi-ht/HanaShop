/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.orderdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import vihtt.utils.DBHelper;

/**
 *
 * @author Thanh Vi
 */
public class orderDetailsDAO implements Serializable{
         
         List<orderDetailsHistory> listOrderDetails;
         
         public List<orderDetailsHistory> getListOrderDetails(){
                  return listOrderDetails;
         }
         
         public boolean insertOrderDetails(String orderID, String itemID, int quantity) throws SQLException, NamingException {
                  Connection con = null;
                  PreparedStatement prm = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                    String sql = "Insert tblOrderDetails (orderID, itemID, quantity) "
                                            + "Values(?,?,?)";
                                    prm = con.prepareStatement(sql);
                                    prm.setString(1, orderID);
                                    prm.setString(2, itemID);
                                    prm.setInt(3, quantity);
                                    int row = prm.executeUpdate();
                                    if (row > 0) {
                                             return true;
                                    }
                           }
                           }finally {
                                    if (prm != null) {prm.close();}
                                    if (con != null) {con.close();}
                          }
                           return false;
                  }
         
         public void getOrderDetailByOrderID(String ID) throws SQLException , NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                    String sql = "SELECT  d.orderID, e.itemID, d.quantity, e.price, e.itemName, e.image "
                                            + "FROM tblItem e, (SELECT itemID, quantity, orderID "
                                            + "                               FROM tblOrderDetails "
                                            + "                               WHERE orderID= ?) d "
                                            + "WHERE  e.itemID=d.itemID" ;
                                    prm = con.prepareStatement(sql);
                                    prm.setString(1,ID);
                                    rs = prm.executeQuery();
                           while (rs.next()) {
                                   String orderID = rs.getString("orderID");
                                   String itemID = rs.getString("itemID");
                                   int quantity = rs.getInt("quantity");
                                   float price = rs.getFloat("price");
                                   String itemName = rs.getString("itemName");
                                   String image = rs.getString("image");
                                   orderDetailsHistory dto = new orderDetailsHistory(orderID, itemID, itemName, price, quantity, image);
                                   if(listOrderDetails == null){
                                            listOrderDetails = new ArrayList<>();
                                   }
                                   listOrderDetails.add(dto);
                           }
                           }
                 } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
}
}
