/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.google;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import vihtt.utils.Constant;

/**
 *
 * @author Thanh Vi
 */
public class GoogleUtils {
         public static String getEmail(String accessToken) throws ClientProtocolException, IOException {
	String link = Constant.GOOGLE_LINK_GET_TOKEN + accessToken; 
	String response = Request.Get(link).execute().returnContent().asString();
	GoogleInfo googleInfo = new Gson().fromJson(response, GoogleInfo.class);
	if (googleInfo.isValid()) {
                               String email = googleInfo.getEmail();
                               return email;
                       }
                       return null;
         }
}
