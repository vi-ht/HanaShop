/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.system.history;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Thanh Vi
 */
public class systemHistoryDTO implements Serializable{
         private String itemID;
         private String email;
         private String content;
         private String contentDetail;
         private LocalDateTime updateDate;
         private String image;

         public systemHistoryDTO() {
         }

         public systemHistoryDTO(String itemID, String email, String content, String contentDetail, LocalDateTime updateDate, String image) {
                  this.itemID = itemID;
                  this.email = email;
                  this.content = content;
                  this.contentDetail = contentDetail;
                  this.updateDate = updateDate;
                  this.image = image;
         }

         public String getItemID() {
                  return itemID;
         }

         public void setItemID(String itemID) {
                  this.itemID = itemID;
         }

         public String getEmail() {
                  return email;
         }

         public void setEmail(String email) {
                  this.email = email;
         }

         public String getContent() {
                  return content;
         }

         public void setContent(String content) {
                  this.content = content;
         }

         public String getContentDetail() {
                  return contentDetail;
         }

         public void setContentDetail(String contentDetail) {
                  this.contentDetail = contentDetail;
         }

         public LocalDateTime getUpdateDate() {
                  return updateDate;
         }

         public void setUpdateDate(LocalDateTime updateDate) {
                  this.updateDate = updateDate;
         }

         public String getImage() {
                  return image;
         }

         public void setImage(String image) {
                  this.image = image;
         }

         
         
}
