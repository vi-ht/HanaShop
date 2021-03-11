/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.category;

import java.io.Serializable;

/**
 *
 * @author Thanh Vi
 */
public class categoryDTO implements Serializable{
         private String categoryID;
         private String categoryName;
         private String icon;

         public categoryDTO() {
         }

         public categoryDTO(String categoryID, String categoryName, String icon) {
                  this.categoryID = categoryID;
                  this.categoryName = categoryName;
                  this.icon = icon;
         }

         public String getCategoryID() {
                  return categoryID;
         }

         public void setCategoryID(String categoryID) {
                  this.categoryID = categoryID;
         }

         public String getCategoryName() {
                  return categoryName;
         }

         public void setCategoryName(String categoryName) {
                  this.categoryName = categoryName;
         }

         public String getIcon() {
                  return icon;
         }

         public void setIcon(String icon) {
                  this.icon = icon;
         }
         
}
