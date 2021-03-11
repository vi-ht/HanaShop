/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.item;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Thanh Vi
 */
public class ItemDTO implements Serializable{
         private String itemID;
         private String itemName;
         private String discription;
         private  String categoryID;
         private float price;
         private int quantity;
         private String image;
         private LocalDate createDate;
         private boolean status;

         public ItemDTO() {
         }

         public ItemDTO(String itemID, String itemName, String discription, String categoryID, float price, int quantity, String image, LocalDate createDate, boolean status) {
                  this.itemID = itemID;
                  this.itemName = itemName;
                  this.discription = discription;
                  this.categoryID = categoryID;
                  this.price = price;
                  this.quantity = quantity;
                  this.image = image;
                  this.createDate = createDate;
                  this.status = status;
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

         public String getDiscription() {
                  return discription;
         }

         public void setDiscription(String discription) {
                  this.discription = discription;
         }

         public String getCategoryID() {
                  return categoryID;
         }

         public void setCategoryID(String categoryID) {
                  this.categoryID = categoryID;
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

         public LocalDate getCreateDate() {
                  return createDate;
         }

         public void setCreateDate(LocalDate createDate) {
                  this.createDate = createDate;
         }

         public boolean isStatus() {
                  return status;
         }

         public void setStatus(boolean status) {
                  this.status = status;
         }

         @Override
         public String toString() {
                  
                  String state = "Inactive";
                  if(status){
                           state = "Active";
                  }
                  return  "itemName=" + itemName + ", discription=" + discription + ", price=" + price + ", quantity=" 
                          + quantity + ", status=" + state ;
         }
         
         
}
