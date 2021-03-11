/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.orderdetail;

/**
 *
 * @author Thanh Vi
 */
public class orderDetailsDTO {
         private String orderID;
         private String itemID;
         private int quantity;

         public orderDetailsDTO() {
         }

         public orderDetailsDTO(String orderID, String itemID, int quantity) {
                  this.orderID = orderID;
                  this.itemID = itemID;
                  this.quantity = quantity;
         }

         public String getOrderID() {
                  return orderID;
         }

         public void setOrderID(String orderID) {
                  this.orderID = orderID;
         }

         public String getItemID() {
                  return itemID;
         }

         public void setItemID(String itemID) {
                  this.itemID = itemID;
         }

         public int getQuantity() {
                  return quantity;
         }

         public void setQuantity(int quantity) {
                  this.quantity = quantity;
         }

         
         
}
