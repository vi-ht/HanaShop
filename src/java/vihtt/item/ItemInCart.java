/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.item;

import java.io.Serializable;

/**
 *
 * @author Thanh Vi
 */
public class ItemInCart implements Serializable{
         private String itemID;
         private String itemName;
         private  String categoryID;
         private float price;
         private int quantityOder;
         private int quantity;
         private String image;

         public ItemInCart() {
         }

         public ItemInCart(String itemID, String itemName, String categoryID, float price, int quantityOder, int quantity, String image) {
                  this.itemID = itemID;
                  this.itemName = itemName;
                  this.categoryID = categoryID;
                  this.price = price;
                  this.quantityOder = quantityOder;
                  this.quantity = quantity;
                  this.image = image;
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

         public int getQuantityOder() {
                  return quantityOder;
         }

         public void setQuantityOder(int quantityOder) {
                  this.quantityOder = quantityOder;
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
