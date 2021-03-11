/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.user;

import java.io.Serializable;

/**
 *
 * @author Thanh Vi
 */
public class UserDTO implements Serializable{
         private  String email;
         private  String password;
         private  String fullname;
         private  String  roleName;

         public UserDTO() {
         }

         public UserDTO(String email, String password, String fullname, String roleName) {
                  this.email = email;
                  this.password = password;
                  this.fullname = fullname;
                  this.roleName = roleName;
         }

         public String getEmail() {
                  return email;
         }

         public void setEmail(String email) {
                  this.email = email;
         }

         public String getPassword() {
                  return password;
         }

         public void setPassword(String password) {
                  this.password = password;
         }

         public String getFullname() {
                  return fullname;
         }

         public void setFullname(String fullname) {
                  this.fullname = fullname;
         }

         public String getRoleName() {
                  return roleName;
         }

         public void setRole(String roleName) {
                  this.roleName = roleName;
         }

         

         

}
