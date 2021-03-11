/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.system.history;

import java.io.Serializable;

/**
 *
 * @author Thanh Vi
 */
public class systemHistoryToView implements Serializable{
         private String itemID;
         private String name;
         private String content;
         private String contentDetail;
         private String updateDate;
         private String image;

         public systemHistoryToView() {
         }

         public systemHistoryToView(String itemID, String name, String content, String contentDetail, String updateDate, String image) {
                  this.itemID = itemID;
                  this.name = name;
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

         public String getName() {
                  return name;
         }

         public void setName(String name) {
                  this.name = name;
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

         public String getUpdateDate() {
                  return updateDate;
         }

         public void setUpdateDate(String updateDate) {
                  this.updateDate = updateDate;
         }

         public String getImage() {
                  return image;
         }

         public void setImage(String image) {
                  this.image = image;
         }
         
         
}
