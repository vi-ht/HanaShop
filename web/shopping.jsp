<%-- 
    Document   : shopping
    Created on : Jan 6, 2021, 8:48:44 AM
    Author     : Thanh Vi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
         <head>
                  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
                  <link rel="stylesheet" href="CSS.css">
                  <title>Shopping Page</title>
         </head>
         <body>
                  <c:if test="${not empty requestScope.DELETE_SUCCESS}">
                           <script>
                                    window.alert("Delete successfully!!!")
                           </script>
                  </c:if>
                  <c:if test="${not empty requestScope.UPDATE_SUCCESS}">
                           <script>
                                    window.alert("Update successfully!!!")
                           </script>
                  </c:if>
                  <c:if test="${not empty requestScope.ADD_SUCCESS}">
                           <script>
                                    window.alert("Add to cart successfully!!!")
                           </script>
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
                                                      <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" id="display_no_welcome" placeholder="Enter product name to search."/>
                                             </c:if>
                                             <input type="submit" name="btnAction" value="Search" id="searchButton" />
                                             <ul>
                                                      <c:if test="${not empty sessionScope.NAME}">
                                                               <c:if test="${not sessionScope.ROLE_OF_USER}">
                                                                        <li><a href="ShoppingServlet" style="border-bottom: 3px solid black; background: #ffffff;" ><i class="fas fa-shopping-cart" title="Shopping" ></i></a></li>
                                                                        <li><a href="ViewCartServlet" title="Shopping cart"><i class="fas fa-shopping-bag"></i></a></li>
                                                                        <li><a href="ViewUserHistoryServlet" title="View History"><i class="fas fa-history"></i></a></li>
                                                               </c:if>
                                                               <c:if test="${ sessionScope.ROLE_OF_USER}">
                                                                        <li><a href="ShoppingServlet"  style="border-bottom: 3px solid black; background: #ffffff;" ><i class="fas fa-edit" title="Update" ></i></a></li>
                                                                        <li><a href="GetInfoToCreateServlet" title="Add New Product"><i class="fas fa-cart-plus"></i></i></a></li>
                                                                        <li><a href="ViewSystemHistoryServlet" title="View History"><i class="fas fa-history"></i></a></li>
                                                               </c:if>
                                                               
                                                               <li><a href="LogoutServlet" title="Logout"><i class="fas fa-sign-out-alt"></i></a></li>
                                                      </c:if>
                                                      <c:if test="${empty sessionScope.NAME}">
                                                               <li><a href="ShoppingServlet" style="border-bottom: 3px solid black; background: #ffffff;" ><i class="fas fa-shopping-cart" title="Shopping" ></i></a></li>
                                                               <li><a href="login.jsp"  title="Login"><i class="fas fa-user-circle"></i></a></li>
                                                      </c:if>
                                             </ul>
                                    </form>
                           </div>
                  <div id="shopname">   
                           <h1 id="nameOfShop">HANA SH<i class="fas fa-cookie-bite" id="cookie"></i>P</h1>
                           <h5>In here, we buy coffee <i class="fas fa-coffee"></i>, cakes <i class="fas fa-cookie-bite"></i>, candies <i class="fas fa-candy-cane"></i> and others food.</h3>
                  </div>
                  <div id="categories">   
                           <c:forEach var="category" items="${requestScope.LIST_CATEGORY}">
                                    <c:url var="urlrewriting" value="DispatchServlet">
                                             <c:param name="btnAction" value="Shopping"/>
                                             <c:param name="Category" value="${category.categoryID}"/>
                                    </c:url>
                                    <c:if test="${requestScope.DISPLAY_CATEGORY eq category.categoryID}">
                                             <a href="${urlrewriting}" class="ctgr" style="border-bottom: 3px solid black;background: #F5A9BC;">${category.categoryName}<i class="${category.icon}"></i></a>
                                    </c:if>
                                    <c:if test="${requestScope.DISPLAY_CATEGORY ne category.categoryID}">
                                             <a href="${urlrewriting}" class="ctgr"">${category.categoryName}<i class="${category.icon}"></i></a>
                                    </c:if>
                                    
                           </c:forEach>
                  </div>
                  <div id="listItem">
                           <c:if test="${not empty requestScope.ITEM}">
                                    <c:forEach var="ItemDTO" items="${requestScope.ITEM}">
                                             <form action="DispatchServlet">
                                                               <div id="item">
                                                                        <img src="Image/${ItemDTO.image}" alt="" id="img">
                                                                        <p><b id="name_of_item">${ItemDTO.itemName}</b></p>
                                                                        <p>${ItemDTO.price}</p>
                                                                        <input type="hidden" name="txtItemID" value="${ItemDTO.itemID}" />
                                                                        <p><input type="submit" value="View Details" name ="btnAction" id="view_detail"/></p>
                                                               </div> 
                                             </form>
                                    </c:forEach>
                           </c:if>   
                  </div>
                  <div>
                           <c:if test="${empty requestScope.ITEM}">
                                    <h2>No Item to show!! </h2>
                           </c:if>  
                  </div>
                  <div id="paging">
                           <c:set var="pageNumber" value="${requestScope.PAGE_NUMBER}"/>
                           <c:set var="totalPage" value="${requestScope.TOTAL_PAGE - 1}"/>
                           <c:if test="${(pageNumber lt totalPage) && (pageNumber gt 0)}">
                                    <c:url var="last" value="DispatchServlet">
                                             <c:param name="btnAction" value="Shopping"/>
                                             <c:param name="txtPageNumber" value="${pageNumber-1}"/> 
                                             <c:param name="Category" value="${requestScope.DISPLAY_CATEGORY}"/> 
                                    </c:url>
                                    <a href="${last}" ><i class="fas fa-angle-left" id="paging" ></i></a>
                                    <c:url var="next" value="DispatchServlet">
                                             <c:param name="btnAction" value="Shopping"/>
                                             <c:param name="txtPageNumber" value="${pageNumber+1}"/>
                                             <c:param name="Category" value="${requestScope.DISPLAY_CATEGORY}"/> 
                                    </c:url>
                                    <a href="${next}"><i class="fas fa-angle-right" id="paging"></i></a>
                           </c:if>
                           <c:if test="${(totalPage eq 0) || (totalPage lt 0)}">
                                    <i class="fas fa-angle-left" id="paging" style="color:gainsboro" ></i>
                                    <i class="fas fa-angle-right" id="paging" style="color:gainsboro"></i>
                           </c:if>
                           <c:if test="${(pageNumber eq totalPage) && (totalPage gt 0)}">
                                    <c:url var="last" value="DispatchServlet">
                                             <c:param name="btnAction" value="Shopping"/>
                                             <c:param name="txtPageNumber" value="${pageNumber-1}"/>
                                             <c:param name="Category" value="${requestScope.DISPLAY_CATEGORY}"/> 
                                    </c:url>
                                    <a href="${last}" ><i class="fas fa-angle-left" id="paging" ></i></a>
                                    <i class="fas fa-angle-right" id="paging" style="color:gainsboro"></i>
                           </c:if>
                           <c:if test="${(pageNumber eq 0) && (totalPage gt 0)}">
                                    <c:url var="next" value="DispatchServlet">
                                             <c:param name="btnAction" value="Shopping"/>
                                             <c:param name="txtPageNumber" value="${pageNumber+1}"/>
                                             <c:param name="Category" value="${requestScope.DISPLAY_CATEGORY}"/> 
                                    </c:url>
                                    <i class="fas fa-angle-left" id="paging" style="color:gainsboro" ></i>
                                    <a href="${next}"><i class="fas fa-angle-right" id="paging"></i></a>
                           </c:if> 
                  </div>
                  <div id="foot">
                           <p><i class="fas fa-copyright" id="p"></i> 2021 Hana Sh<i class="fas fa-cookie-bite" id="pCookie"></i>p, by Thanh Vi.<br>
                           Contact me via <a href="https://www.facebook.com/merry.kute.31/"><i class="fab fa-facebook" id="p" ></i></a> or <a href="#"><i class="fas fa-envelope" id="p"></i></a></p>
                  </div>
         </body>
</html>
