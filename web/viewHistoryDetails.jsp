<%-- 
    Document   : viewHistoryDetailsServlet
    Created on : Jan 21, 2021, 4:51:49 PM
    Author     : Thanh Vi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
         <head>
                  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                  <title>View History Details Page</title>
                  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
                  <link rel="stylesheet" href="CSS.css">
                  <title>History Details Page</title>
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
                  <div id="navigation">   
                           <form action="DispatchServlet">
                                    <i class="fas fa-smile"></i>
                                    <c:if test="${not empty sessionScope.NAME}">
                                             <c:if test="${sessionScope.ROLE_OF_USER}">
                                                      <span id="welcome">Welcome, ${sessionScope.NAME} (Admin)</span>
                                             </c:if>
                                             <c:if test="${not sessionScope.ROLE_OF_USER}">
                                                      <span id="welcome">Welcome, ${sessionScope.NAME}</span>
                                             </c:if>
                                    <input type="text"  name="txtSearchValue" value="${param.txtSearchValue}" id="display_welcome" placeholder="Enter product name to search."/>
                                    </c:if>
                                    <c:if test="${empty sessionScope.NAME}">
                                             <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" id="display_no_welcome" placeholder="Enter product name to search."/>
                                    </c:if>
                                    <input type="submit" name="btnAction" value="Search" id="searchButton" />
                                    <ul>
                                             <c:if test="${not empty sessionScope.NAME}">
                                                      <c:if test="${not sessionScope.ROLE_OF_USER}">
                                                               <li><a href="ShoppingServlet" ><i class="fas fa-shopping-cart" title="Shopping" ></i></a></li>
                                                               <li><a href="ViewCartServlet" title="Shopping cart" ><i class="fas fa-shopping-bag"></i></a></li>
                                                               <li><a href="ViewUserHistoryServlet" title="View History"><i class="fas fa-history"></i></a></li>
                                                                        </c:if>
                                                                        <c:if test="${ sessionScope.ROLE_OF_USER}">
                                                               <li><a href="ShoppingServlet" ><i class="fas fa-edit" title="Update" ></i></a></li>
                                                               <li><a href="GetInfoToCreateServlet" title="Add New Product"><i class="fas fa-cart-plus"></i></i></a></li>
                                                               <li><a href="ViewSystemHistoryServlet" title="View History"><i class="fas fa-history"></i></a></li>
                                                               </c:if>
                                                      
                                                      <li><a href="LogoutServlet" title="Logout"><i class="fas fa-sign-out-alt"></i></a></li>
                                                               </c:if>
                                                               <c:if test="${empty sessionScope.NAME}">
                                                      <li><a href="ShoppingServlet" ><i class="fas fa-shopping-cart" title="Shopping" ></i></a></li>
                                                      <li><a href="login.jsp"  title="Login"><i class="fas fa-user-circle"></i></a></li>
                                                               </c:if>
                                    </ul>
                           </form>
                  </div>
                  <div id="shopname">   
                           <h1 id="nameOfShop" style=""><b>HANA SH<i class="fas fa-cookie-bite" id="cookie"></i>P</b></h1>
                           <h5><b>In here, we buy coffee <i class="fas fa-coffee"></i>, cakes <i class="fas fa-cookie-bite"></i>, candies <i class="fas fa-candy-cane"></i> and others food.</b></h5>
                  </div>
                  <div id="viewcart_form">
                           <c:if test="${not empty requestScope.HISTORY_DETAILS}">
                                             <table border="1" id="tb">
                                                      <thead>
                                                               <tr>
                                                                        <th>No.</th>
                                                                        <th>Image</th>
                                                                        <th>Name</th>
                                                                        <th>Quantity</th>
                                                                        <th>Price</th>
                                                                        <th>Total Price</th>
                                                               </tr>
                                                      </thead>
                                                      <tbody>
                                                               <c:forEach var="history" items="${requestScope.HISTORY_DETAILS}" varStatus="counter">
                                                                        <tr>
                                                                                 <td>${counter.count}</td>
                                                                                 <td><div id="item_imagecart">
                                                                                                   <img src="Image/${history.image}" id="imgCart" />
                                                                                          </div></td>
                                                                                 <td>${history.itemName}</td>
                                                                                 <td>${history.quantity}</td>
                                                                                 <td>${history.price}</td>
                                                                                 <td>${history.price*history.quantity}</td>
                                                                        </tr>
                                                               </c:forEach>    
                                                               <tr>
                                                                        <td colspan="3" >Total</td>
                                                                        <td>${requestScope.TOTAL_QUANTITY}</td>
                                                                        <td> </td>
                                                                        <td>
                                                                                 ${requestScope.TOTAL_PRICE}
                                                                        </td>
                                                               </tr>
                                                      </tbody>
                                             </table>
                           </c:if>
                          </div>
                           <c:if test="${empty requestScope.HISTORY_DETAILS}">
                                    <div id="invalid">
                                             <h1>No History Details!!!! <i class="far fa-laugh-squint"></i></h1><br>
                                    </div>
                           </c:if>
                  <div id="foot">
                           <p><i class="fas fa-copyright" id="p"></i> 2021 Hana Sh<i class="fas fa-cookie-bite" id="pCookie"></i>p, by Thanh Vi.<br>
                                    Contact me via <a href="https://www.facebook.com/merry.kute.31/"><i class="fab fa-facebook" id="p" ></i></a> or <a href="#"><i class="fas fa-envelope" id="p"></i></a></p>
                  </div>
         </body>
</html>
