/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import vihtt.orderdetail.orderDetailsDAO;
import vihtt.utils.DBHelper;

/**
 *
 * @author Thanh Vi
 */
public class orderDAO implements Serializable{
         
         List<orderHistory> listOrder;
         
         public List<orderHistory> getListOrder(){
                  return listOrder;
         }
         
         public boolean insertOrder(orderDTO dto) throws SQLException, NamingException {
                  Connection con = null;
                  PreparedStatement prm = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                    String sql = "Insert tblOrder (orderID, totalPrice, custName, custAdress, custPhone, orderDate, email, status, totalQuantity) "
                                            + "Values(?,?,?,?,?,?,?,?,?)";
                                    prm = con.prepareStatement(sql);
                                    prm.setString(1, dto.getOrderID());
                                    prm.setFloat(2, dto.getTotalPrice());
                                    prm.setString(3, dto.getCustName());
                                    prm.setString(4, dto.getCustAdress());
                                    prm.setString(5, dto.getCustPhone());
                                    prm.setString(6, Timestamp.valueOf(dto.getOrderDate()).toString());
                                    prm.setString(7, dto.getEmail());
                                    prm.setBoolean(8, dto.isStatus());
                                    prm.setInt(9, dto.getTotalQuantity());
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
         
         public boolean checkDuplicatedOrderID(String orderID) throws SQLException , NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                    String sql = "Select orderID "
                                            + "From tblOrder "
                                            + "Where orderID = ? " ;
                                    prm = con.prepareStatement(sql);
                                    prm.setString(1,orderID);
                                    rs = prm.executeQuery();
                           if (rs.next()) {
                                   return true;
                           }
                           }
                 } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
    return false;
}
         
         public void getHistoryByUserEmail(String Email) throws SQLException , NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                    String sql = "Select orderID, totalPrice, custName, custAdress, custPhone, orderDate, email, status, totalQuantity "
                                            + "From tblOrder "
                                            + "Where email = ?  AND status = ? " 
                                            + "ORDER BY orderDate DESC ";
                                    prm = con.prepareStatement(sql);
                                    prm.setString(1, Email);
                                    prm.setBoolean(2, true);
                                    rs = prm.executeQuery();
                           while (rs.next()) {
                                   String orderID = rs.getString("orderID");
                                   String custName = rs.getString("custName");
                                   String custAdress = rs.getString("custAdress");
                                   String custPhone = rs.getString("custPhone");
                                   String email = rs.getString("email");
                                   boolean status = rs.getBoolean("status");
                                   String orderDate = rs.getString("orderDate");
                                   float price = rs.getFloat("totalPrice");
                                   int totalQuantity = rs.getInt("totalQuantity");
                                   orderDetailsDAO dao = new orderDetailsDAO();
                                   dao.getOrderDetailByOrderID(orderID);
                                   if(listOrder == null){
                                            listOrder = new ArrayList<>();
                                   }
                                   orderHistory orderHistory = new orderHistory(orderID, price, totalQuantity, custName, custAdress, custPhone, orderDate, email, status);
                                   listOrder.add(orderHistory);
                           }
                           }
                 } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
}
         
         public void searchHistory(LocalDateTime minDateTime, LocalDateTime maxDateTime,String itemName , String Email) throws SQLException , NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                    String sql = "SELECT e.orderID, e.totalPrice, e.custName, e.custAdress, e.custPhone, e.orderDate, e.email, e.status, e.totalQuantity "
                                            + "FROM tblOrder e, (SELECT orderID  "
                                            + "                                 FROM tblOrderDetails o, (SELECT itemID   "
                                            + "                                                                              FROM tblItem "
                                            + "                                                                              WHERE itemName= ? )i "
                                            + "                                 WHERE o.itemID= i.itemID)d " 
                                            + "WHERE  e.orderID=d.orderID "
                                            + "AND email = ? "
                                            + "AND orderDate <= ?  AND orderDate >= ? "
                                            + "AND status = ? "
                                            + "ORDER BY orderDate DESC  ";
                                    prm = con.prepareStatement(sql);
                                    prm.setString(1, itemName);
                                    prm.setString(2, Email);
                                    prm.setString(3, Timestamp.valueOf(maxDateTime).toString());
                                    prm.setString(4, Timestamp.valueOf(minDateTime).toString());
                                    prm.setBoolean(5, true);
                                    rs = prm.executeQuery();
                           while (rs.next()) {
                                   String orderID = rs.getString("orderID");
                                   String custName = rs.getString("custName");
                                   String custAdress = rs.getString("custAdress");
                                   String custPhone = rs.getString("custPhone");
                                   String email = rs.getString("email");
                                   boolean status = rs.getBoolean("status");
                                   String orderDate = rs.getString("orderDate");
                                   float price = rs.getFloat("totalPrice");
                                   int totalQuantity = rs.getInt("totalQuantity");
                                   orderDetailsDAO dao = new orderDetailsDAO();
                                   dao.getOrderDetailByOrderID(orderID);
                                   if(listOrder == null){
                                            listOrder = new ArrayList<>();
                                   }
                                   orderHistory orderHistory = new orderHistory(orderID, price, totalQuantity, custName, custAdress, custPhone, orderDate, email, status);
                                   listOrder.add(orderHistory);
                           }
                           }
                 } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
}
         
                  public void searchHistoryByDate(LocalDateTime minDateTime, LocalDateTime maxDateTime) throws SQLException , NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                    String sql = "Select orderID, totalPrice, custName, custAdress, custPhone, orderDate, email, status, totalQuantity "
                                            + "From tblOrder "
                                            + "Where  orderDate <= ?  AND orderDate >= ? "
                                            + "AND status = ? " 
                                            + "ORDER BY orderDate DESC ";
                                    prm = con.prepareStatement(sql);
                                    prm.setString(1, Timestamp.valueOf(maxDateTime).toString());
                                    prm.setString(2, Timestamp.valueOf(minDateTime).toString());
                                    prm.setBoolean(3, true);
                                    rs = prm.executeQuery();
                           while (rs.next()) {
                                    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                                   String orderID = rs.getString("orderID");
                                   String custName = rs.getString("custName");
                                   String custAdress = rs.getString("custAdress");
                                   String custPhone = rs.getString("custPhone");
                                   String email = rs.getString("email");
                                   boolean status = rs.getBoolean("status");
                                   String orderDate = rs.getString("orderDate");
                                   float price = rs.getFloat("totalPrice");
                                   int totalQuantity = rs.getInt("totalQuantity");
                                   orderDetailsDAO dao = new orderDetailsDAO();
                                   dao.getOrderDetailByOrderID(orderID);
                                   if(listOrder == null){
                                            listOrder = new ArrayList<>();
                                   }
                                   orderHistory orderHistory = new orderHistory(orderID, price, totalQuantity, custName, custAdress, custPhone, orderDate, email, status);
                                   listOrder.add(orderHistory);
                           }
                           }
                 } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
}
         public void searchHistorybyItemName(String itemName, String Email) throws SQLException , NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                    String sql = "SELECT e.orderID, e.totalPrice, e.custName, e.custAdress, e.custPhone, e.orderDate, e.email, e.status, e.totalQuantity "
                                            + "FROM tblOrder e, (SELECT orderID  "
                                            + "                                 FROM tblOrderDetails o, (SELECT itemID   "
                                            + "                                                                              FROM tblItem "
                                            + "                                                                              WHERE itemName= ? )i "
                                            + "                                 WHERE o.itemID= i.itemID)d " 
                                            + "WHERE  e.orderID=d.orderID "
                                            + "AND email = ? "
                                            + "AND status = ? "
                                            + "ORDER BY orderDate DESC  ";
                                    prm = con.prepareStatement(sql);
                                    prm.setString(1, itemName);
                                    prm.setString(2, Email);
                                    prm.setBoolean(3, true);
                                    rs = prm.executeQuery();
                           while (rs.next()) {
                                   String orderID = rs.getString("orderID");
                                   String custName = rs.getString("custName");
                                   String custAdress = rs.getString("custAdress");
                                   String custPhone = rs.getString("custPhone");
                                   String email = rs.getString("email");
                                   boolean status = rs.getBoolean("status");
                                   String orderDate = rs.getString("orderDate");
                                   float price = rs.getFloat("totalPrice");
                                   int totalQuantity = rs.getInt("totalQuantity");
                                   orderDetailsDAO dao = new orderDetailsDAO();
                                   dao.getOrderDetailByOrderID(orderID);
                                   if(listOrder == null){
                                            listOrder = new ArrayList<>();
                                   }
                                   orderHistory orderHistory = new orderHistory(orderID, price, totalQuantity, custName, custAdress, custPhone, orderDate, email, status);
                                   listOrder.add(orderHistory);
                           }
                           }
                 } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
}
         
         }
