<%-- 
    Document   : searchUserHistory
    Created on : Jan 22, 2021, 2:02:44 PM
    Author     : Thanh Vi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
         <head>
                  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                  <title>Search History Page</title>
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
                                             <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" id="display_welcome" placeholder="Enter product name to search."/>
                                    </c:if>
                                    <c:if test="${empty sessionScope.NAME}">
                                             <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" id="display_no_welcome" placeholder="Enter product name to search." />
                                    </c:if>
                                    <input type="submit" name="btnAction" value="Search" id="searchButton" />
                                    <ul>
                                             <c:if test="${not empty sessionScope.NAME}">
                                                      <c:if test="${not sessionScope.ROLE_OF_USER}">
                                                               <li><a href="ShoppingServlet"><i class="fas fa-shopping-cart" title="Shopping" ></i></a></li>
                                                               <li><a href="ViewCartServlet" title="Shopping cart"><i class="fas fa-shopping-bag"></i></a></li>
                                                               <li><a href="ViewUserHistoryServlet" title="View History" style="border-bottom: 3px solid black; background: #ffffff;"><i class="fas fa-history"></i></a></li>
                                                      </c:if>
                                                      <c:if test="${ sessionScope.ROLE_OF_USER}">
                                                                 <li><a href="ShoppingServlet"><i class="fas fa-edit" title="Update" ></i></a></li>
                                                                  <li><a href="GetInfoToCreateServlet" title="Add New Product"  ><i class="fas fa-cart-plus"></i></i></a></li>
                                                                  <li><a href="ViewSystemHistoryServlet" title="View History"><i class="fas fa-history"></i></a></li>
                                                      </c:if>
                                                      <li><a href="LogoutServlet" title="Logout"><i class="fas fa-sign-out-alt"></i></a></li>
                                                               </c:if>
                                                               <c:if test="${empty sessionScope.NAME}">
                                                               <li><a href="ShoppingServlet"><i class="fas fa-shopping-cart" title="Shopping" ></i></a></li>
                                                      <li><a href="login.jsp"  title="Login"><i class="fas fa-user-circle"></i></a></li>
                                             </c:if>
                                    </ul>
                           </form>
                  </div>
                  <div id="shopname">   
                           <h1 id="nameOfShop">HANA SH<i class="fas fa-cookie-bite" id="cookie"></i>P</h1>
                           <h5>In here, we buy coffee <i class="fas fa-coffee"></i>, cakes <i class="fas fa-cookie-bite"></i>, candies <i class="fas fa-candy-cane"></i> and others food.</h3>
                  </div>
                  <div id="categories_history">   
                                    <a href="ViewUserHistoryServlet" class="ctgr" >View History</a>
                                    <a href="searchUserHistory.jsp" class="ctgr" style="border-bottom: 3px solid black;background: #F5A9BC;">Search History</a>
                  </div>
                  <form action="DispatchServlet" id="search_history_form">
                           <font id="login"><b>Search History</b></font><br><br>
                           <b>Name: </b><input type="text" name="txtSearchHistory" value="${param.txtSearchHistory}" id="search_history" />
                           <b>Date: </b><input type="date" name="txtDate" value="${param.txtDate}" >
                           <input type="submit" name="btnAction" value="Search History" id ="Search_history"  /><br>
                           <c:if test="${not empty requestScope.DATE_ERROR}">
                                    <script>
                                             window.alert("Incorrect date format.")
                                    </script>
                           </c:if>
                  </form>
                           <div id="viewcart_form">
                           <c:if test="${not empty requestScope.HISTORY}">
                                             <table border="1" id="tb">
                                                      <thead>
                                                               <tr>
                                                                        <th>No.</th>
                                                                        <th>Order Date</th>
                                                                        <th>OrderID</th>
                                                                        <th>Shipment Details</th>
                                                                        <th>Total Quantity</th>
                                                                        <th>Total Price</th>
                                                                        <th>View Details</th>
                                                               </tr>
                                                      </thead>
                                                      <tbody>
                                                               <c:forEach var="history" items="${requestScope.HISTORY}" varStatus="counter">
                                                                        <form action="DispatchServlet"  name="viewcartForm">
                                                                                 <tr>
                                                                                          <td><p>${counter.count}</p></td>
                                                                                          <td><p>${history.orderDateTime}</p></td>
                                                                                          <td>
                                                                                                   <p>${history.orderID}</p>
                                                                                                   <input type="hidden" name="txtOrderID" value="${history.orderID}" />
                                                                                          </td>
                                                                                          <td><p><b>name:</b> ${history.custName}</p>
                                                                                          <p><b>Phone number:</b>${history.custPhone}</p>
                                                                                          <p><b>Adress: </b>${history.custAdress}</p></td>
                                                                                          <td><p>${history.totalQuantity}</p></td>
                                                                                          <td><p>${history.totalPrice}</p></td>
                                                                                          <input type="hidden" name="txtTotalPrice" value="${history.totalPrice}" />
                                                                                          <input type="hidden" name="txtTotalQuantity" value="${history.totalQuantity}" />
                                                                                          <td><input type="submit" value="History Details"  name="btnAction" id="remove"/></td>
                                                                                 </tr>
                                                                        </form>
                                                               </c:forEach>    
                                                      </tbody>
                                             </table>
                           </c:if>
                                    </div>
                           <c:if test="${not empty param.txtSearchHistory || not empty param.txtDate}">
                           <c:if test="${empty requestScope.DATE_ERROR}">
                                    <c:if test="${empty requestScope.HISTORY}">
                                             <div id="invalid">
                                                      <h1>No history to matches!!!! <i class="far fa-laugh-squint"></i></h1><br>
                                                      <a href="ShoppingServlet" id="login_Error">Click here to go to shopping page</a>
                                             </div>
                                    </c:if>
                           </c:if>
                           </c:if>                               
         <div id="foot">
                  <p><i class="fas fa-copyright" id="p"></i> 2021 Hana Sh<i class="fas fa-cookie-bite" id="pCookie"></i>p, by Thanh Vi.<br>
                           Contact me via <a href="https://www.facebook.com/merry.kute.31/"><i class="fab fa-facebook" id="p" ></i></a> or <a href="#"><i class="fas fa-envelope" id="p"></i></a></p>
         </div>
 </body>
</html>
