<%-- 
    Document   : update
    Created on : Jan 15, 2021, 10:45:38 AM
    Author     : Thanh Vi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
        <head>
                  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
                  <link rel="stylesheet" href="CSS.css">
                  <title>Update Page</title>
         </head>
         <body>
                  <c:if test="${empty sessionScope.NAME}">
                           <c:redirect url="login.jsp"/>
                  </c:if>
                  <c:if test="${not empty sessionScope.NAME}">
                           <c:if test="${not sessionScope.ROLE_OF_USER}">
                                    <c:redirect url="404.html"/>
                           </c:if>
                  </c:if>
                  <c:if test="${not empty requestScope.NO_MODIFY_UPDATE}">
                           <script>
                                    window.alert("No modify to update!!!")
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
                                             <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" id="display_welcome" placeholder="Enter product name to search." />
                                    </c:if>
                                    <c:if test="${empty sessionScope.NAME}">
                                             <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" id="display_no_welcome" placeholder="Enter product name to search."/>
                                    </c:if>
                                    <input type="submit" name="btnAction" value="Search" id="searchButton" />
                                    <ul> 
                                              <c:if test="${not empty sessionScope.NAME}">
                                                                <c:if test="${not sessionScope.ROLE_OF_USER}">
                                                                        <li><a href="ShoppingServlet"><i class="fas fa-shopping-cart" title="Shopping" ></i></a></li>
                                                                        <li><a href="ViewCartServlet" title="Shopping cart"><i class="fas fa-shopping-bag"></i></a></li>
                                                                        <li><a href="ViewUserHistoryServlet" title="View History"><i class="fas fa-history"></i></a></li>
                                                                </c:if>
                                                               <c:if test="${ sessionScope.ROLE_OF_USER}">
                                                                        <li><a href="ShoppingServlet"><i class="fas fa-edit" title="Update" ></i></a></li>
                                                                        <li><a href="GetInfoToCreateServlet" title="Add New Product" ><i class="fas fa-cart-plus"></i></i></a></li>
                                                                        <li><a href="ViewSystemHistoryServlet" title="View History"><i class="fas fa-history"></i></a></li>
                                                               </c:if>
                                                      <li><a href="LogoutServlet" title="Logout"><i class="fas fa-sign-out-alt"></i></a></li>
                                                               </c:if>
                                             <c:if test="${empty sessionScope.NAME}">
                                                      <li><a href="login.jsp"  title="Login"><i class="fas fa-user-circle"></i></a></li>
                                             </c:if>
                                    </ul>
                           </form>
                  </div>
                  <div id="shopname">   
                           <h1 id="nameOfShop">HANA SH<i class="fas fa-cookie-bite" id="cookie"></i>P</h1>
                           <h5>In here, we buy coffee <i class="fas fa-coffee"></i>, cakes <i class="fas fa-cookie-bite"></i>, candies <i class="fas fa-candy-cane"></i> and others food.</h3>
                  </div>
                  <c:set var="error" value="${ requestScope.ERRORS}" />
                  <c:if test="${not empty requestScope.ITEM_DETAILS}">
                  <c:set var="Item" value="${requestScope.ITEM_DETAILS}"/>
                  <form action="DispatchServlet" method="POST"  id="create_form"  name="createForm" enctype="multipart/form-data">
                           <font id="create"><b>Update</b></font><br><br>
                           <div class="display_in_create_form">
                                    <b>Product Image:</b><br>
                                    <div id="item_image_small">
                                             <img src="Image/${Item.image}" id="img_small" />
                                    </div>
                                             Or choose another image: <input type="file" id="File" name="imgFile" accept="image/*" onchange="loadFile(event)" >
                                             <input type="hidden" name="txtItemID" value="${Item.itemID}"/>
                                    <p><input type="hidden" name="txtImage" value="${Item.image}" /></p>
                                    <b>Product Name:</b>
                                    <input type="text" name="txtProductName" value="${Item.itemName}" placeholder="Name's max length is 20." required maxlength="20"/>
                           <input type="submit" value="Update Product" name="btnAction" id="create_button"/>
                           </div>
                           <div class="display_in_create_form">
                                    <b>Product Category:</b>
                                    <c:if test="${ not empty error.noneCategoryERROR }">
                                             <span id="error_create">${error.noneCategoryERROR}</span><br>
                                    </c:if>
                                    <select name="cbxCategory" id="combobox" required>
                                             <c:forEach var="Category" items="${requestScope.LIST_CATEGORY}">
                                                      <c:if test="${Category.categoryName eq requestScope.CATEGORY_NAME}">
                                                               <option selected value="${Category.categoryID}">${Category.categoryName}</option>
                                                      </c:if>
                                                      <c:if test="${Category.categoryName ne requestScope.CATEGORY_NAME}">
                                                               <option value="${Category.categoryID}">${Category.categoryName}</option>
                                                      </c:if>
                                             </c:forEach>
                                    </select>
                                    <b>Product Description:</b>
                                    <textarea name="txtDescription" rows="1" cols="5" placeholder="Description's max length is 300." required maxlength="300">${Item.discription}</textarea>
                                    
                                    <b>Product Price:</b>
                                    <c:if test="${ not empty error.negativeNumberOfPriceERROR }">
                                             <span id="error_create">${error.negativeNumberOfPriceERROR}</span><br>
                                    </c:if>
                                             <input type="number" name="txtPrice" value="${Item.price}" placeholder="Price is a number and must be greater than 0!"" min="1000" id="number" required step="0.01" max="1000000000"/>
                                    <b>Product Quantity:</b>
                                    <input type="number" name="txtQuantity" value="${Item.quantity}" placeholder="Quantity is a number and must be greater than or equal 0!" min="0" id="number" required max="1000000"/>
                                    <b>Product Status:</b>
                                    <select name="cbxStatus" id="combobox" required>
                                             <c:if test="${Item.status}">
                                                      <option selected>Active</option>
                                                      <option>Inactive</option>
                                             </c:if>
                                             <c:if test="${not Item.status}">
                                                      <option>Active</option>
                                                      <option selected>Inactive</option>
                                             </c:if>
                                    </select>
                                    <input type="hidden" name="txtCreateDate" value="${Item.createDate}"  class="number" readonly="True"/>
                           </div>
                  </form>
                  </c:if>
                  <script>
                           var loadFile = function(event) {
                             var reader = new FileReader();
                             reader.onload = function(){
                               var output = document.getElementById('img_small');
                               output.src = reader.result;
                             };
                             reader.readAsDataURL(event.target.files[0]);
                           };
                  </script>
   
               
                  <div id="foot">
                           <p><i class="fas fa-copyright" id="p"></i> 2021 Hana Sh<i class="fas fa-cookie-bite" id="pCookie"></i>p, by Thanh Vi.<br>
                                    Contact me via <a href="https://www.facebook.com/merry.kute.31/"><i class="fab fa-facebook" id="p" ></i></a> or <a href="#"><i class="fas fa-envelope" id="p"></i></a></p>
                  </div>
         </body>
</html>
