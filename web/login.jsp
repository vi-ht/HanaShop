<%-- 
    Document   : login
    Created on : Jan 8, 2021, 5:00:53 PM
    Author     : Thanh Vi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
         <head>
                  <title>Login Page</title>
                  <meta charset="UTF-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1.0">
                  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
                  <link rel="stylesheet" href="CSS.css">
                  <script src="https://apis.google.com/js/platform.js" async defer></script>
                  <meta name="google-signin-client_id" content="802563450887-scevedk8ph8knp0u8o3feaqv8f7reab8.apps.googleusercontent.com">
         </head>
         <body>
                  <div id="navigation">   
                           <form action="DispatchServlet">
                                    <i class="fas fa-smile"></i>
                                    <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" id="display_no_welcome" placeholder="Enter product name to search."/>
                                    <input type="submit" name="btnAction" value="Search" id="searchButton" />
                                    <ul>
                                             <li><a href="ShoppingServlet" title="Shopping"><i class="fas fa-shopping-cart"></i></a></li>
                                             <li><a href="login.jsp" title="Login" style="border-bottom: 3px solid black; background: #ffffff;"><i class="fas fa-user-circle"></i></a></li>
                                    </ul>
                           </form>
                  </div>
                  <div id="shopname">   
                           <h1 id="nameOfShop">HANA SH<i class="fas fa-cookie-bite" id="cookie"></i>P</h1>
                           <h5>In here, we buy coffee <i class="fas fa-coffee"></i>, cakes <i class="fas fa-cookie-bite"></i>, candies <i class="fas fa-candy-cane"></i> and others food.</h3>
                  </div>
                  <form action="login" method="POST" id="loginform">
                           <font id="login"><b>Login</b></font><br>
                           <p id="p1">If you have an account, login here: </p>
                           <b>Email:</b>
                           <input type="email" name="txtEmail" value="" required />
                           <b>Password:</b>
                           <input type="password" name="txtPassword" value="" required/>
                           <input type="submit" name="btnAction" value="Login" /><br>
                           <p id="p1">Or login with <i class="fab fa-google"></i> : </p>
                                    <div class="g-signin2" data-onsuccess="onSignIn"></div>
                                    <p id="error"></p>
                  </form>   
                  <div id="foot">   
                           <p><i class="fas fa-copyright" id="p"></i> 2021 Hana Sh<i class="fas fa-cookie-bite" id="pCookie"></i>p, by Thanh Vi.<br>
                                    Contact me via <a href="https://www.facebook.com/merry.kute.31/"><i class="fab fa-facebook" id="p" ></i></a> or <a href="#"><i class="fas fa-envelope" id="p"></i></a></p>
                  </div>
                  <script>
                          document.getElementById("error").style.display = "none";
                          function onSignIn(googleUser) {
                                    var id_token = googleUser.getAuthResponse().id_token;
                                    var xhr = new XMLHttpRequest();
                                    xhr.open('POST', 'http://localhost:8084/HanaShop_2/LoginWithGoogleServlet');
                                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                                    xhr.onload = function() {
                                             var response = xhr.responseText;
                                             if (response == "VALID") {
                                                       window.location.href = "ShoppingServlet";
                                             } else {
                                                      document.getElementById("error").innerHTML = 'Your account is not valid. Please try again!';
                                                      document.getElementById("error").style.display = "block";
                                             }
                                             gapi.auth2.getAuthInstance().disconnect();
                                    };
                                    xhr.send('idToken=' + id_token);
                          }
                      </script>
                  
         </body>
</html>

