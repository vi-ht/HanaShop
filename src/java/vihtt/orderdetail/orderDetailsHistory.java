/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.orderdetail;

import java.io.Serializable;

/**
 *
 * @author Thanh Vi
 */
public class orderDetailsHistory implements Serializable{
         private String orderID;
         private String itemID;
         private String itemName;
         private float price;
         private int quantity;
         private String image;

         public orderDetailsHistory() {
         }

         public orderDetailsHistory(String orderID, String itemID, String itemName, float price, int quantity, String image) {
                  this.orderID = orderID;
                  this.itemID = itemID;
                  this.itemName = itemName;
                  this.price = price;
                  this.quantity = quantity;
                  this.image = image;
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

         public String getItemName() {
                  return itemName;
         }

         public void setItemName(String itemName) {
                  this.itemName = itemName;
         }

         public float getPrice() {
                  return price;
         }

         public void setPrice(float price) {
                  this.price = price;
         }

         public int getQuantity() {
                  return quantity;
         }

         public void setQuantity(int quantity) {
                  this.quantity = quantity;
         }

         public String getImage() {
                  return image;
         }

         public void setImage(String image) {
                  this.image = image;
         }
         
}
