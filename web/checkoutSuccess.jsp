<%-- 
    Document   : checkoutSuccess
    Created on : Jan 19, 2021, 10:01:09 PM
    Author     : Thanh Vi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
         <head>
                  <title>Checkout Success Page</title>
                  <meta charset="UTF-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1.0">
                  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
                  <link rel="stylesheet" href="CSS.css">
         </head>
         <body>
                  <c:if test="${empty sessionScope.NAME}">
                           <c:redirect url="login.jsp"/>
                  </c:if>
                  <c:if test="${not empty sessionScope.NAME}">
                           <c:if test="${sessionScope.ROLE_OF_USER}">
                                    <c:redirect url="404.html"/>
                           </c:if>
                  </c:if>
                  <div id="shopname">   
                           <h1 id="nameOfShop">HANA SH<i class="fas fa-cookie-bite" id="cookie"></i>P</h1>
                           <h5>In here, we buy coffee <i class="fas fa-coffee"></i>, cakes <i class="fas fa-cookie-bite"></i>, candies <i class="fas fa-candy-cane"></i> and others food.</h3>
                  </div>
                  <div id="invalid">
                           <h1>Check out Successfully, thank for shopping! <i class="far fa-laugh-squint"></i></h1>
                           <font>Your order ID is <b>${requestScope.ORDER_ID}</b>, please prepare enough <b>${requestScope.TOTAL_PRICE}</b> (VNĐ) to pay for this order.</font><br>
                           <br><a href="ShoppingServlet" id="login_Error">Back to shopping page.</a>
                  </div>
                  <div id="foot">
                           <p><i class="fas fa-copyright" id="p"></i> 2021 Hana Sh<i class="fas fa-cookie-bite" id="pCookie"></i>p, by Thanh Vi.<br>
                                    Contact me via <a href="https://www.facebook.com/merry.kute.31/"><i class="fab fa-facebook" id="p" ></i></a> or <a href="#"><i class="fas fa-envelope" id="p"></i></a></p>
                 </div>
         </body>
</html>
