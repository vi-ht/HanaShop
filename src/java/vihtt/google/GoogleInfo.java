/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.google;

import java.util.Date;
import vihtt.utils.Constant;
/**
 *
 * @author Thanh Vi
 */
public class GoogleInfo {
          private String iss;
          private String aud;
          private String email;
          private boolean email_verified;
          private long exp;
          private String error;
    
    public boolean isValid() {
        return error == null
                && (iss.equals("accounts.google.com") || iss.equals("https://accounts.google.com"))
                && aud.equals(Constant.GOOGLE_CLIENT_ID)
                && email_verified
                && new Date().getTime() / 1000 <= exp;
    }

    public String getEmail() {
        return email;
    }
}
