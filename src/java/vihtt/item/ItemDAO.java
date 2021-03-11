/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.item;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import javax.naming.NamingException;
import vihtt.utils.DBHelper;

/**
 *
 * @author Thanh Vi
 */
public class ItemDAO implements Serializable{
         List<ItemDTO> ItemList;

         public List<ItemDTO> getItemList() {
                  return ItemList;
         }
 //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         public void getItemByCategory(String CateID, boolean Status, int offset, int fetch) throws SQLException , NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                    String sql = "Select ItemID, itemName, discription, categoryID, price, quantity, image, createDate, status  "
                                            + "From tblItem "
                                            + "Where categoryID  = ? AND status = ? AND quantity > ? "
                                            + "ORDER BY createDate ASC "
                                            + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                                    prm = con.prepareStatement(sql);
                                    prm.setString(1, CateID);
                                    prm.setBoolean(2, Status);
                                    prm.setInt(3, 0);
                                    prm.setInt(4, offset);
                                    prm.setInt(5, fetch);
                                    rs = prm.executeQuery();
                                    while (rs.next()) {
                                             String itemID = rs.getString("ItemID");
                                             String itemName = rs.getString("itemName");
                                             String discription = rs.getString("discription");
                                             String categoryID = rs.getString("categoryID");
                                             float price = rs.getFloat("price");
                                             int quantity = rs.getInt("quantity");
                                             String image = rs.getString("image");
                                             LocalDate createDate = java.sql.Date.valueOf(rs.getString("createDate")).toLocalDate().plusDays(2);
                                             boolean status = rs.getBoolean("status");
                                             ItemDTO dto = new ItemDTO(itemID, itemName, discription, categoryID, price, quantity, image, createDate, status);
                                             if (ItemList == null) {
                                                      ItemList = new ArrayList<>();
                                             }
                                             ItemList.add(dto);
                                    }
                           }
                  } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
         }

         public void getItemByCategory(String CateID, int offset, int fetch) throws SQLException, NamingException {
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                    String sql = "Select ItemID, itemName, discription, categoryID, price, quantity, image, createDate, status  "
                                            + "From tblItem "
                                            + "Where categoryID  = ? "
                                            + "ORDER BY createDate ASC "
                                            + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                                    prm = con.prepareStatement(sql);
                                    prm.setString(1, CateID);
                                    prm.setInt(2, offset);
                                    prm.setInt(3, fetch);
                                    rs = prm.executeQuery();
                                    while (rs.next()) {
                                             String itemID = rs.getString("ItemID");
                                             String itemName = rs.getString("itemName");
                                             String discription = rs.getString("discription");
                                             String categoryID = rs.getString("categoryID");
                                             float price = rs.getFloat("price");
                                             int quantity = rs.getInt("quantity");
                                             String image = rs.getString("image");
                                             LocalDate createDate = java.sql.Date.valueOf(rs.getString("createDate")).toLocalDate().plusDays(2);
                                             boolean status = rs.getBoolean("status");
                                             ItemDTO dto = new ItemDTO(itemID, itemName, discription, categoryID, price, quantity, image, createDate, status);
                                             if (ItemList == null) {
                                                      ItemList = new ArrayList<>();
                                             }
                                             ItemList.add(dto);
                                    }
                           }
                 } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
         }
         
         public int getSizeOfListItem(String CateID, boolean status) throws NamingException, SQLException {
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                    String sql = "Select COUNT(*) as Total "
                                            + "From tblItem "
                                            + "Where categoryID  = ? AND status = ? AND quantity > ?";
                                    prm = con.prepareStatement(sql);
                                    prm.setString(1, CateID);
                                    prm.setBoolean(2, status);
                                    prm.setInt(3, 0);
                                    rs = prm.executeQuery();
                                    if (rs.next()) {
                                             return rs.getInt("Total");
                                    }
                           }
                  } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
                  return 0;
         }
         
         public int getSizeOfListItem(String CateID) throws NamingException, SQLException {
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                             String sql = "Select COUNT(*) as Total "
                                                     + "From tblItem "
                                                     + "Where categoryID  = ? ";
                                             prm = con.prepareStatement(sql);
                                             prm.setString(1, CateID);
                                    rs = prm.executeQuery();
                                    if (rs.next()) {
                                             return rs.getInt("Total");
                                    }
                           }
                  } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
                  return 0;
         }
  //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------       
public ItemDTO searchItemByID(String ID) throws SQLException , NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                    String sql = "Select ItemID, itemName, discription, categoryID, price, quantity, image, createDate, status  "
                                            + "From tblItem "
                                            + "Where ItemID = ? " ;
                                    prm = con.prepareStatement(sql);
                                    prm.setString(1,ID);
                                    rs = prm.executeQuery();
                                    if (rs.next()) {
                                             String itemID = rs.getString("ItemID") ;
                                             String itemName= rs.getString("itemName") ;
                                             String discription = rs.getString("discription") ;
                                             String categoryID = rs.getString("categoryID") ;
                                             float price = rs.getFloat("price") ;
                                             int quantity= rs.getInt("quantity");
                                             String image = rs.getString("image") ;
                                             LocalDate createDate = java.sql.Date.valueOf(rs.getString("createDate")).toLocalDate().plusDays(2);
                                             boolean status = rs.getBoolean("status");
                                             ItemDTO dto = new ItemDTO(itemID, itemName, discription, categoryID, price, quantity, image, createDate, status);
                                             return dto;
                           }
                           }
                 } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
    return  null;
}


public int searchQuantityByID(String ID) throws SQLException , NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                    String sql = "Select quantity "
                                            + "From tblItem "
                                            + "Where ItemID = ? ";
                                    prm = con.prepareStatement(sql);
                                    prm.setString(1,ID);
                                    rs = prm.executeQuery();
                                    if (rs.next()) {
                                             int quantity= rs.getInt("quantity");
                                             return quantity;
                           }
                           }
                 } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
    return 0;
}
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------               
         public void searchItem(String name, boolean Status,  int offset, int fetch) throws SQLException , NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                             String sql = "Select ItemID, itemName, discription, categoryID, price, quantity, image, createDate, status  "
                                                      + "From tblItem "
                                                      + "Where itemName Like ? AND status = ? AND quantity > ? "
                                                      + "ORDER BY createDate ASC "
                                                      + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                                              prm = con.prepareStatement(sql);
                                              prm.setString(1,"%"+name+"%");
                                              prm.setBoolean(2, Status);
                                              prm.setInt(3, 0);
                                              prm.setInt(4, offset);
                                              prm.setInt(5, fetch);
                                    rs = prm.executeQuery();
                                    while (rs.next()) {
                                             String itemID = rs.getString("ItemID") ;
                                             String itemName= rs.getString("itemName") ;
                                             String discription = rs.getString("discription") ;
                                             String categoryID = rs.getString("categoryID") ;
                                             float price = rs.getFloat("price");
                                             int quantity= rs.getInt("quantity");
                                             String image = rs.getString("image") ;
                                            LocalDate createDate = java.sql.Date.valueOf(rs.getString("createDate")).toLocalDate().plusDays(2);
                                             boolean status = rs.getBoolean("status");
                                             ItemDTO dto = new ItemDTO(itemID, itemName, discription, categoryID, price, quantity, image, createDate, status);
                                             if(ItemList == null){
                                                      ItemList = new ArrayList<>();
                                             }
                                             ItemList.add(dto);
                           }
                           }
                 } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
    
}

         public void searchItem(String name,  int offset, int fetch) throws SQLException , NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                             String sql = "Select ItemID, itemName, discription, categoryID, price, quantity, image, createDate, status  "
                                                      + "From tblItem "
                                                      + "Where itemName Like ? "
                                                      + "ORDER BY createDate ASC " 
                                                      + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                                              prm = con.prepareStatement(sql);
                                              prm.setString(1,"%"+name+"%");
                                              prm.setInt(2, offset);
                                              prm.setInt(3, fetch);
                                    rs = prm.executeQuery();
                                    while (rs.next()) {
                                             String itemID = rs.getString("ItemID") ;
                                             String itemName= rs.getString("itemName") ;
                                             String discription = rs.getString("discription") ;
                                             String categoryID = rs.getString("categoryID") ;
                                             float price = rs.getFloat("price") ;
                                             int quantity= rs.getInt("quantity");
                                             String image = rs.getString("image") ;
                                            LocalDate createDate = java.sql.Date.valueOf(rs.getString("createDate")).toLocalDate().plusDays(2);
                                             boolean status = rs.getBoolean("status");
                                             ItemDTO dto = new ItemDTO(itemID, itemName, discription, categoryID, price, quantity, image, createDate, status);
                                             if(ItemList == null){
                                                      ItemList = new ArrayList<>();
                                             }
                                             ItemList.add(dto);
                           }
                           }
                 } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
    
}
         
         public int getSizeOfListSearch(String name, boolean Status) throws NamingException, SQLException {
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                             String sql =  "Select COUNT(*) as Total "
                                                      + "From tblItem "
                                                      + "Where itemName Like ? AND status = ? AND quantity > ? ";
                                              prm = con.prepareStatement(sql);
                                              prm.setString(1,"%"+name+"%");
                                              prm.setBoolean(2, Status);
                                              prm.setInt(3, 0);
                                    rs = prm.executeQuery();
                                    if (rs.next()) {
                                             return rs.getInt("Total");
                                    }
                           }
                  } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
                  return 0;
         }
         
         public int getSizeOfListSearch(String name) throws NamingException, SQLException {
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                             String sql =  "Select COUNT(*) as Total "
                                                      + "From tblItem "
                                                      + "Where itemName Like ? ";
                                              prm = con.prepareStatement(sql);
                                              prm.setString(1,"%"+name+"%");
                                    rs = prm.executeQuery();
                                    if (rs.next()) {
                                             return rs.getInt("Total");
                                    }
                           }
                  } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
                  return 0;
         }
         
     //-------------------------------------------------------------------------------------------------------------------------------------------------------    
         
         public void searchItem(String name, String cateID, boolean Status,  int offset, int fetch) throws SQLException , NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                    
                                             String sql = "Select ItemID, itemName, discription, categoryID, price, quantity, image, createDate, status  "
                                                      + "From tblItem "
                                                      + "Where itemName Like ? AND categoryID =  ? AND status = ? AND quantity > ? "
                                                      + "ORDER BY createDate ASC " 
                                                      + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                                              prm = con.prepareStatement(sql);
                                              prm.setString(1,"%"+name+"%");
                                              prm.setString(2,cateID);
                                              prm.setBoolean(3, Status);
                                              prm.setInt(4, 0);
                                              prm.setInt(5, offset);
                                              prm.setInt(6, fetch);
                                    rs = prm.executeQuery();
                                    while (rs.next()) {
                                             String itemID = rs.getString("ItemID") ;
                                             String itemName= rs.getString("itemName") ;
                                             String discription = rs.getString("discription") ;
                                             String categoryID = rs.getString("categoryID") ;
                                             float price = rs.getFloat("price") ;
                                             int quantity= rs.getInt("quantity");
                                             String image = rs.getString("image") ;
                                             LocalDate createDate = java.sql.Date.valueOf(rs.getString("createDate")).toLocalDate().plusDays(2);
                                             boolean status = rs.getBoolean("status");
                                             ItemDTO dto = new ItemDTO(itemID, itemName, discription, categoryID, price, quantity, image, createDate, status);
                                             if(ItemList == null){
                                                      ItemList = new ArrayList<>();
                                             }
                                             ItemList.add(dto);
                           }
                           }
                 } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
         }
         
         public void searchItem(String name, String cateID,  int offset, int fetch) throws SQLException , NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                             String sql = "Select ItemID, itemName, discription, categoryID, price, quantity, image, createDate, status  "
                                                      + "From tblItem "
                                                      + "Where itemName Like ? AND categoryID =  ? "
                                                      + "ORDER BY createDate ASC "
                                                      + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                                              prm = con.prepareStatement(sql);
                                              prm.setString(1,"%"+name+"%");
                                              prm.setString(2,cateID);
                                              prm.setInt(3, offset);
                                              prm.setInt(4, fetch);
                                    rs = prm.executeQuery();
                                    while (rs.next()) {
                                             String itemID = rs.getString("ItemID") ;
                                             String itemName= rs.getString("itemName") ;
                                             String discription = rs.getString("discription") ;
                                             String categoryID = rs.getString("categoryID") ;
                                             float price = rs.getFloat("price") ;
                                             int quantity= rs.getInt("quantity");
                                             String image = rs.getString("image") ;
                                             LocalDate createDate = java.sql.Date.valueOf(rs.getString("createDate")).toLocalDate().plusDays(2);
                                             boolean status = rs.getBoolean("status");
                                             ItemDTO dto = new ItemDTO(itemID, itemName, discription, categoryID, price, quantity, image, createDate, status);
                                             if(ItemList == null){
                                                      ItemList = new ArrayList<>();
                                             }
                                             ItemList.add(dto);
                           }
                           }
                 } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
         }
         
         public int getSizeOfListSearch(String name, String cateID, boolean Status) throws NamingException, SQLException {
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                             String sql =  "Select COUNT(*) as Total "
                                                      + "From tblItem "
                                                      + "Where itemName Like ? AND categoryID =  ? AND status = ? AND quantity > ? ";
                                              prm = con.prepareStatement(sql);
                                              prm.setString(1,"%"+name+"%");
                                              prm.setString(2, cateID);
                                              prm.setBoolean(3, Status);
                                              prm.setInt(4, 0);
                                    rs = prm.executeQuery();
                                    if (rs.next()) {
                                             return rs.getInt("Total");
                                    }
                           }
                  } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
                  return 0;
         }
         
         public int getSizeOfListSearch(String name, String cateID) throws NamingException, SQLException {
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                             String sql =  "Select COUNT(*) as Total "
                                                      + "From tblItem "
                                                      + "Where itemName Like ? AND categoryID =  ? ";
                                              prm = con.prepareStatement(sql);
                                              prm.setString(1,"%"+name+"%");
                                              prm.setString(2, cateID);
                                    rs = prm.executeQuery();
                                    if (rs.next()) {
                                             return rs.getInt("Total");
                                    }
                           }
                  } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
                  return 0;
         }
     //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------    
         public void searchItem(String name, String cateID, int minPrice, int maxPrice, boolean Status, int offset, int fetch) throws SQLException , NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                             String sql = "Select ItemID, itemName, discription, categoryID, price, quantity, image, createDate, status  "
                                                      + "From tblItem "
                                                      + "Where itemName Like ? "
                                                      + "AND categoryID = ? AND status = ? AND quantity > ? "
                                                      + "AND price >= ? AND price <= ? "
                                                      + "ORDER BY createDate ASC "
                                                      + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                                              prm = con.prepareStatement(sql);
                                              prm.setString(1,"%"+name+"%");
                                              prm.setString(2,cateID);
                                              prm.setBoolean(3, Status);
                                              prm.setInt(4, 0);
                                              prm.setInt(5, minPrice);
                                              prm.setInt(6, maxPrice);
                                              prm.setInt(7, offset);
                                              prm.setInt(8, fetch);
                                    rs = prm.executeQuery();
                                    while (rs.next()) {
                                             String itemID = rs.getString("ItemID") ;
                                             String itemName= rs.getString("itemName") ;
                                             String discription = rs.getString("discription") ;
                                             String categoryID = rs.getString("categoryID") ;
                                             float price = rs.getFloat("price") ;
                                             int quantity= rs.getInt("quantity");
                                             String image = rs.getString("image") ;
                                             LocalDate createDate = java.sql.Date.valueOf(rs.getString("createDate")).toLocalDate().plusDays(2);
                                             boolean status = rs.getBoolean("status");
                                             ItemDTO dto = new ItemDTO(itemID, itemName, discription, categoryID, price, quantity, image, createDate, status);
                                             if(ItemList == null){
                                                      ItemList = new ArrayList<>();
                                             }
                                             ItemList.add(dto);
                           }
                           }
                 } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
         }
         
         public void searchItem(String name, String cateID, int minPrice, int maxPrice,  int offset, int fetch) throws SQLException , NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                             String sql = "Select ItemID, itemName, discription, categoryID, price, quantity, image, createDate, status  "
                                                      + "From tblItem "
                                                      + "Where itemName Like ? "
                                                      + "AND categoryID = ?  "
                                                      + "AND price >= ? AND price <= ? "
                                                      + "ORDER BY createDate ASC "
                                                      + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                                              prm = con.prepareStatement(sql);
                                              prm.setString(1,"%"+name+"%");
                                              prm.setString(2,cateID);
                                              prm.setInt(3, minPrice);
                                              prm.setInt(4, maxPrice);
                                              prm.setInt(5, offset);
                                              prm.setInt(6, fetch);
                                    rs = prm.executeQuery();
                                    while (rs.next()) {
                                             String itemID = rs.getString("ItemID") ;
                                             String itemName= rs.getString("itemName") ;
                                             String discription = rs.getString("discription") ;
                                             String categoryID = rs.getString("categoryID") ;
                                             float price = rs.getFloat("price") ;
                                             int quantity= rs.getInt("quantity");
                                             String image = rs.getString("image") ;
                                             LocalDate createDate = java.sql.Date.valueOf(rs.getString("createDate")).toLocalDate().plusDays(2);
                                             boolean status = rs.getBoolean("status");
                                             ItemDTO dto = new ItemDTO(itemID, itemName, discription, categoryID, price, quantity, image, createDate, status);
                                             if(ItemList == null){
                                                      ItemList = new ArrayList<>();
                                             }
                                             ItemList.add(dto);
                           }
                           }
                 } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
         }
         
         public int getSizeOfListSearch(String name, String cateID, int minPrice, int maxPrice, boolean Status) throws NamingException, SQLException {
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                             String sql =  "Select COUNT(*) as Total "
                                                      + "From tblItem "
                                                      + "Where itemName Like ? "
                                                      + "AND categoryID = ? AND status = ? AND quantity > ? "
                                                      + "AND price >= ? AND price <= ? ";
                                              prm = con.prepareStatement(sql);
                                              prm.setString(1,"%"+name+"%");
                                              prm.setString(2,cateID);
                                              prm.setBoolean(3, Status);
                                              prm.setInt(4, 0);
                                              prm.setInt(5, minPrice);
                                              prm.setInt(6, maxPrice);
                                    rs = prm.executeQuery();
                                    if (rs.next()) {
                                             return rs.getInt("Total");
                                    }
                           }
                  } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
                  return 0;
         }
         
         public int getSizeOfListSearch(String name, String cateID, int minPrice, int maxPrice) throws NamingException, SQLException {
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                             String sql =  "Select COUNT(*) as Total "
                                                      + "From tblItem "
                                                      + "Where itemName Like ? "
                                                      + "AND categoryID = ? "
                                                      + "AND price >= ? AND price <= ? ";
                                              prm = con.prepareStatement(sql);
                                              prm.setString(1,"%"+name+"%");
                                              prm.setString(2,cateID);
                                              prm.setInt(3, minPrice);
                                              prm.setInt(4, maxPrice);
                                    rs = prm.executeQuery();
                                    if (rs.next()) {
                                             return rs.getInt("Total");
                                    }
                           }
                  } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
                  return 0;
         }
 //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------        
         public void searchItem(String name, int minPrice, int maxPrice, boolean Status,  int offset, int fetch) throws SQLException , NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                             String sql = "Select ItemID, itemName, discription, categoryID, price, quantity, image, createDate, status  "
                                                      + "From tblItem "
                                                      + "Where itemName Like ? AND status = ? AND quantity > ? "
                                                      + "AND price >= ? AND price <= ? "
                                                      + "ORDER BY createDate ASC " 
                                                      + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                                              prm = con.prepareStatement(sql);
                                              prm.setString(1,"%"+name+"%");
                                              prm.setBoolean(2, Status);
                                              prm.setInt(3, 0);
                                              prm.setInt(4, minPrice);
                                              prm.setInt(5, maxPrice);
                                              prm.setInt(6, offset);
                                              prm.setInt(7, fetch);
                                    rs = prm.executeQuery();
                                    while (rs.next()) {
                                             String itemID = rs.getString("ItemID") ;
                                             String itemName= rs.getString("itemName") ;
                                             String discription = rs.getString("discription") ;
                                             String categoryID = rs.getString("categoryID") ;
                                             float price = rs.getFloat("price") ;
                                             int quantity= rs.getInt("quantity");
                                             String image = rs.getString("image") ;
                                             LocalDate createDate = java.sql.Date.valueOf(rs.getString("createDate")).toLocalDate().plusDays(2);
                                             boolean status = rs.getBoolean("status");
                                             ItemDTO dto = new ItemDTO(itemID, itemName, discription, categoryID, price, quantity, image, createDate, status);
                                             if(ItemList == null){
                                                      ItemList = new ArrayList<>();
                                             }
                                             ItemList.add(dto);
                           }
                           }
                 } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
         }
         
         public void searchItem(String name, int minPrice, int maxPrice,  int offset, int fetch) throws SQLException , NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                             String sql = "Select ItemID, itemName, discription, categoryID, price, quantity, image, createDate, status  "
                                                      + "From tblItem "
                                                      + "Where itemName Like ? "
                                                      + "AND price >= ? AND price <= ? "
                                                      + "ORDER BY createDate ASC " 
                                                      + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                                              prm = con.prepareStatement(sql);
                                              prm.setString(1,"%"+name+"%");
                                              prm.setInt(2, minPrice);
                                              prm.setInt(3, maxPrice);
                                              prm.setInt(4, offset);
                                              prm.setInt(5, fetch);
                                    rs = prm.executeQuery();
                                    while (rs.next()) {
                                             String itemID = rs.getString("ItemID") ;
                                             String itemName= rs.getString("itemName") ;
                                             String discription = rs.getString("discription") ;
                                             String categoryID = rs.getString("categoryID") ;
                                             float price = rs.getFloat("price") ;
                                             int quantity= rs.getInt("quantity");
                                             String image = rs.getString("image") ;
                                             LocalDate createDate = java.sql.Date.valueOf(rs.getString("createDate")).toLocalDate().plusDays(2);
                                             boolean status = rs.getBoolean("status");
                                             ItemDTO dto = new ItemDTO(itemID, itemName, discription, categoryID, price, quantity, image, createDate, status);
                                             if(ItemList == null){
                                                      ItemList = new ArrayList<>();
                                             }
                                             ItemList.add(dto);
                           }
                           }
                 } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
         }
         
         public int getSizeOfListSearch(String name, int minPrice, int maxPrice, boolean Status) throws NamingException, SQLException {
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                             String sql =  "Select COUNT(*) as Total "
                                                      + "From tblItem "
                                                      + "Where itemName Like ? AND status = ? AND quantity > ? "
                                                      + "AND price >= ? AND price <= ? " ;
                                              prm = con.prepareStatement(sql);
                                              prm.setString(1,"%"+name+"%");
                                              prm.setBoolean(2, Status);
                                              prm.setInt(3, 0);
                                              prm.setInt(4, minPrice);
                                              prm.setInt(5, maxPrice);
                                    rs = prm.executeQuery();
                                    if (rs.next()) {
                                             return rs.getInt("Total");
                                    }
                           }
                  } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
                  return 0;
         }
         
         public int getSizeOfListSearch(String name, int minPrice, int maxPrice) throws NamingException, SQLException {
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                             String sql =  "Select COUNT(*) as Total "
                                                      + "From tblItem "
                                                      + "Where itemName Like ?  "
                                                      + "AND price >= ? AND price <= ? ";
                                              prm = con.prepareStatement(sql);
                                              prm.setString(1,"%"+name+"%");
                                              prm.setInt(2, minPrice);
                                              prm.setInt(3, maxPrice);
                                    rs = prm.executeQuery();
                                    if (rs.next()) {
                                             return rs.getInt("Total");
                                    }
                           }
                  } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
                  return 0;
         }
         
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         public boolean ceateNewProduct(ItemDTO dto)
                 throws SQLException, NamingException {
                  Connection con = null;
                  PreparedStatement prm = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                    String sql = "Insert into tblItem(ItemID, itemName, discription, categoryID, price, quantity, image, createDate, status ) "
                                                         + "values(?, ?, ?, ?, ?, ?, ?, ?, ?) ";
                                    prm = con.prepareStatement(sql);
                                    prm.setString(1, dto.getItemID());
                                    prm.setString(2, dto.getItemName());
                                    prm.setString(3, dto.getDiscription());
                                    prm.setString(4, dto.getCategoryID());
                                    prm.setFloat(5, dto.getPrice());
                                    prm.setInt(6, dto.getQuantity());
                                    prm.setString(7, dto.getImage());
                                    prm.setString(8, Date.valueOf(dto.getCreateDate()).toString());
                                    prm.setBoolean(9, dto.isStatus());
                                    int row = prm.executeUpdate();
                                    if (row > 0) {
                                             return true;
                                    }
                           }
                  } finally {
                           if (prm != null) {
                                    prm.close();
                           }
                           if (con != null) {
                                    con.close();
                           }
                  }
                  return false;
         }
                 
         public boolean updateItem (ItemDTO dto) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement prm = null;
        try {
            con = DBHelper.makeConnection();
            if (con!=null) {
                String sql =  "Update tblItem "
                                     + "set  itemName = ?, discription =?, categoryID=?, price = ?, quantity = ?, image = ?,  createDate = ?, status = ? "
                                    + "Where itemID  = ? "; 
            prm = con.prepareStatement(sql);
                     prm.setString(1, dto.getItemName());
                     prm.setString(2, dto.getDiscription());
                     prm.setString(3, dto.getCategoryID());
                     prm.setFloat(4, dto.getPrice());
                     prm.setInt(5, dto.getQuantity());
                     prm.setString(6, dto.getImage());
                     prm.setString(7, Date.valueOf(dto.getCreateDate()).toString());
                     prm.setBoolean(8, dto.isStatus());
                     prm.setString(9, dto.getItemID());
            int row =prm.executeUpdate();
            if(row>0){
                return true;}
                }
        } finally {
            if (prm != null) {prm.close();}
            if (con != null) {con.close();}
        }
        return false;
     }
      
         public boolean deleteItem (String itemID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement prm = null;
        try {
            con = DBHelper.makeConnection();
            if (con!=null) {
                String sql =  "Update tblItem "
                                     + "set status = ? "
                                     + "Where itemID  = ? "; 
            prm = con.prepareStatement(sql);
                     prm.setBoolean(1, false);
                     prm.setString(2, itemID);
            int row =prm.executeUpdate();
            if(row>0){
                return true;}
                }
        } finally {
            if (prm != null) {prm.close();}
            if (con != null) {con.close();}
        }
        return false;
     }
      
          public boolean updateQuantityOfItem (String itemID, int quantity) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement prm = null;
        try {
            con = DBHelper.makeConnection();
            if (con!=null) {
                String sql =  "Update tblItem "
                                     + "set quantity = ? "
                                    + "Where itemID  = ? "; 
            prm = con.prepareStatement(sql);
                     prm.setInt(1, quantity);
                     prm.setString(2, itemID);
            int row =prm.executeUpdate();
            if(row>0){
                return true;}
                }
        } finally {
            if (prm != null) {prm.close();}
            if (con != null) {con.close();}
        }
        return false;
     }
          
          public void getRecomendProduct(String ID) throws SQLException , NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                           if (con != null) {
                                    String sql = "Select k.itemID, itemName, discription, categoryID, price, quantity, image, createDate, status "
                                            + "FROM tblItem i, (SELECT d.itemID "
                                            + "                             FROM tblOrderDetails d "
                                            + "                             where d.orderID IN (SELECT orderID "
                                            + "                                                                 FROM tblOrderDetails "
                                            + "                                                                 WHERE itemID = ?) "
                                            + "                             AND d.itemID NOT IN(SELECT itemID "
                                            + "                                                                    FROM tblOrderDetails "
                                            + "                                                                    WHERE itemID = ?) "
                                            + "                             GROUP BY d.itemID HAVING SUM(d.quantity) >= ALL (SELECT SUM(d.quantity) "
                                            + "                                                                                                                            FROM tblOrderDetails d "
                                            + "                                                                                                                            where d.orderID IN (SELECT orderID "
                                            + "                                                                                                                                                                FROM tblOrderDetails "
                                            + "                                                                                                                                                                WHERE itemID = ? ) "
                                             + "                                                                                                                           AND d.itemID NOT IN (SELECT itemID "
                                            + "                                                                                                                                                                FROM tblOrderDetails "
                                            + "                                                                                                                                                                WHERE itemID = ?) "
                                            + "                                                                                                                            GROUP BY d.itemID))k "
                                            + "WHERE K.itemID = I.itemID "
                                            + "AND i.status = ? AND i.quantity > ? ";	
                                    prm = con.prepareStatement(sql);
                                    prm.setString(1, ID);
                                    prm.setString(2, ID);
                                    prm.setString(3, ID);
                                    prm.setString(4, ID);
                                    prm.setBoolean(5, true);
                                    prm.setInt(6, 0);
                                    rs = prm.executeQuery();
                                    while (rs.next()) {
                                             String itemID = rs.getString("ItemID") ;
                                             String itemName= rs.getString("itemName") ;
                                             String discription = rs.getString("discription") ;
                                             String categoryID = rs.getString("categoryID") ;
                                             float price = rs.getFloat("price") ;
                                             int quantity= rs.getInt("quantity");
                                             String image = rs.getString("image") ;
                                             LocalDate createDate = java.sql.Date.valueOf(rs.getString("createDate")).toLocalDate().plusDays(2);
                                             boolean status = rs.getBoolean("status");
                                             ItemDTO dto = new ItemDTO(itemID, itemName, discription, categoryID, price, quantity, image, createDate, status);
                                             if(ItemList == null){
                                                      ItemList = new ArrayList<>();
                                             }
                                             ItemList.add(dto);
                           }
                           }
                 } finally {
                           if (rs != null) {rs.close();}
                           if (prm != null) {prm.close();}
                           if (con != null) {con.close();}
                  }
}
      
         
}
