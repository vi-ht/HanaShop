<%-- 
    Document   : search
    Created on : Jan 6, 2021, 8:52:54 AM
    Author     : Thanh Vi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
         <head>
                  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                  <title>Search Page</title>
                  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
                  <link rel="stylesheet" href="CSS.css">
         </head>
         <body>
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
                                    </c:if>
                                    <ul>
                                             <li><a href="SearchServlet" style="border-bottom: 3px solid black; background: #ffffff;" ><i class="fas fa-search" title="Search" ></i></a></li>
                                             <c:if test="${not empty sessionScope.NAME}">
                                                                        <c:if test="${not sessionScope.ROLE_OF_USER}">
                                                               <li><a href="ShoppingServlet" ><i class="fas fa-shopping-cart" title="Shopping" ></i></a></li>
                                                               <li><a href="ViewCartServlet" title="Shopping cart"><i class="fas fa-shopping-bag"></i></a></li>
                                                               <li><a href="ViewUserHistoryServlet" title="View History"><i class="fas fa-history" style="border-bottom: 3px solid black; background: #ffffff;"></i></a></li>
                                                                        </c:if>
                                                                        <c:if test="${ sessionScope.ROLE_OF_USER}">
                                                               <li><a href="ShoppingServlet" ><i class="fas fa-edit" title="Update" ></i></a></li>
                                                               <li><a href="GetInfoToCreateServlet" title="Add New Product"><i class="fas fa-cart-plus"></i></i></a></li>
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
                  <form action="DispatchServlet" id="search_form">
                           <font id="login"><b>Search</b></font><br><br>
                           <input type="hidden" name="txtPageNumber" value="${requestScope.PAGE_NUMBER}" />
                           <b>Category: </b>
                           <select name="cbxCategory" id="display_combobox">
                                    <c:if test="${empty param.cbxCategory}">
                                             <option selected value="All">All category</option>
                                    </c:if>
                                    <c:if test="${not empty param.cbxCategory}">
                                             <option value="All">All category</option>
                                    </c:if>
                                    <c:forEach var="Category" items="${requestScope.LIST_CATEGORY}">
                                             <c:if test="${Category.categoryID eq param.cbxCategory}">
                                                      <option selected value="${Category.categoryID}">${Category.categoryName}</option>
                                             </c:if>
                                             <c:if test="${Category.categoryID ne param.cbxCategory}">
                                                      <option value="${Category.categoryID}">${Category.categoryName}</option>
                                             </c:if>
                                    </c:forEach>
                           </select>
                           <b>Min price: </b><input type="number" name="txtMinPrice" value="${param.txtMinPrice}" placeholder="Defaut" class="Min_Max" min="0" max="100000000"/>
                           <b>Max price: </b><input type="number" name="txtMaxPrice" value="${param.txtMaxPrice}"  placeholder="Defaut" class="Min_Max" min="0" max="1000000000"/>
                           <b>Name:</b> <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" id="search" />
                           <input type="submit" name="btnAction" value="Search" id ="Search_button"  /><br>
                           <c:if test="${not empty requestScope.ERROR}">
                                    ${requestScope.ERROR}
                           </c:if>
                  </form>
                  <div id="listItem">
                           <c:if test="${not empty requestScope.SEARCH_RESULT}">
                                    <c:forEach var="ItemDTO" items="${requestScope.SEARCH_RESULT}" varStatus="counter">
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
                    <c:if test="${empty requestScope.ERROR}">
                           <c:if test="${not requestScope.SEARCH}">
                                    <c:if test="${empty requestScope.SEARCH_RESULT}">
                                             <div id="result">
                                                    <h2>No Item to show!! </h2>
                                           </div>
                                    </c:if>  
                           </c:if>                 
                  </c:if>
                  <div id="paging">
                           <c:set var="pageNumber" value="${requestScope.PAGE_NUMBER}"/>
                           <c:set var="totalPage" value="${requestScope.TOTAL_PAGE}"/>
                           <c:if test="${(pageNumber lt totalPage) && (pageNumber gt 0)}">
                                    <c:url var="last" value="DispatchServlet">
                                             <c:param name="btnAction" value="Search"/>
                                             <c:param name="txtPageNumber" value="${pageNumber-1}"/> 
                                             <c:param name="cbxCategory" value="${param.cbxCategory}"/> 
                                             <c:param name="txtMinPrice" value="${param.txtMinPrice}"/> 
                                             <c:param name="txtMaxPrice" value="${param.txtMaxPrice}"/> 
                                             <c:param name="txtSearchValue" value="${param.txtSearchValue}"/> 
                                    </c:url>
                                    <a href="${last}" ><i class="fas fa-angle-left" id="paging" ></i></a>
                                    <c:url var="next" value="DispatchServlet">
                                             <c:param name="btnAction" value="Search"/>
                                             <c:param name="txtPageNumber" value="${pageNumber+1}"/>
                                             <c:param name="cbxCategory" value="${param.cbxCategory}"/> 
                                             <c:param name="txtMinPrice" value="${param.txtMinPrice}"/> 
                                             <c:param name="txtMaxPrice" value="${param.txtMaxPrice}"/> 
                                             <c:param name="txtSearchValue" value="${param.txtSearchValue}"/> 
                                    </c:url>
                                    <a href="${next}"><i class="fas fa-angle-right" id="paging"></i></a>
                           </c:if>
                           <c:if test="${(totalPage eq 0) || (totalPage lt 0)}">
                                    <i class="fas fa-angle-left" id="paging" style="color:gainsboro" ></i>
                                    <i class="fas fa-angle-right" id="paging" style="color:gainsboro"></i>
                           </c:if>
                           <c:if test="${(pageNumber eq totalPage) && (totalPage gt 0)}">
                                    <c:url var="last" value="DispatchServlet">
                                             <c:param name="btnAction" value="Search"/>
                                             <c:param name="txtPageNumber" value="${pageNumber-1}"/>
                                             <c:param name="cbxCategory" value="${param.cbxCategory}"/> 
                                             <c:param name="txtMinPrice" value="${param.txtMinPrice}"/> 
                                             <c:param name="txtMaxPrice" value="${param.txtMaxPrice}"/> 
                                             <c:param name="txtSearchValue" value="${param.txtSearchValue}"/> 
                                    </c:url>
                                    <a href="${last}" ><i class="fas fa-angle-left" id="paging" ></i></a>
                                    <i class="fas fa-angle-right" id="paging" style="color:gainsboro"></i>
                           </c:if>
                           <c:if test="${(pageNumber eq 0) && (totalPage gt 0) }">
                                    
                                    <c:url var="next" value="DispatchServlet">
                                             <c:param name="btnAction" value="Search"/>
                                             <c:param name="txtPageNumber" value="${pageNumber+1}"/>
                                             <c:param name="cbxCategory" value="${param.cbxCategory}"/> 
                                             <c:param name="txtMinPrice" value="${param.txtMinPrice}"/> 
                                             <c:param name="txtMaxPrice" value="${param.txtMaxPrice}"/> 
                                             <c:param name="txtSearchValue" value="${param.txtSearchValue}"/> 
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
