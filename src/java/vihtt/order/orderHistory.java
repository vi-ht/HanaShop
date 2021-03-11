/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.order;

import java.io.Serializable;

/**
 *
 * @author Thanh Vi
 */
public class orderHistory implements Serializable{
         private String orderID;
         private float totalPrice;
         private int totalQuantity;
         private String custName;
         private String custAdress;
         private String custPhone;
         private String orderDateTime;
         private String email;
         private boolean status;

         public orderHistory() {
         }

         public orderHistory(String orderID, float totalPrice, int totalQuantity, String custName, String custAdress, String custPhone, String orderDateTime, String email, boolean status) {
                  this.orderID = orderID;
                  this.totalPrice = totalPrice;
                  this.totalQuantity = totalQuantity;
                  this.custName = custName;
                  this.custAdress = custAdress;
                  this.custPhone = custPhone;
                  this.orderDateTime = orderDateTime;
                  this.email = email;
                  this.status = status;
         }

         public int getTotalQuantity() {
                  return totalQuantity;
         }

         public void setTotalQuantity(int totalQuantity) {
                  this.totalQuantity = totalQuantity;
         }

         

         public String getOrderID() {
                  return orderID;
         }

         public void setOrderID(String orderID) {
                  this.orderID = orderID;
         }

         public float getTotalPrice() {
                  return totalPrice;
         }

         public void setTotalPrice(float totalPrice) {
                  this.totalPrice = totalPrice;
         }

         public String getCustName() {
                  return custName;
         }

         public void setCustName(String custName) {
                  this.custName = custName;
         }

         public String getCustAdress() {
                  return custAdress;
         }

         public void setCustAdress(String custAdress) {
                  this.custAdress = custAdress;
         }

         public String getCustPhone() {
                  return custPhone;
         }

         public void setCustPhone(String custPhone) {
                  this.custPhone = custPhone;
         }

         public String getOrderDateTime() {
                  return orderDateTime;
         }

         public void setOrderDateTime(String orderDateTime) {
                  this.orderDateTime = orderDateTime;
         }

         public String getEmail() {
                  return email;
         }

         public void setEmail(String email) {
                  this.email = email;
         }

         public boolean isStatus() {
                  return status;
         }

         public void setStatus(boolean status) {
                  this.status = status;
         }

         
}
