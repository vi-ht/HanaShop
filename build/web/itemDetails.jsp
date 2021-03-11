<%-- 
    Document   : itemDetails
    Created on : Jan 12, 2021, 4:30:11 PM
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
                  <title>Item Detail Page</title>
                  <style>
                  .modal-open .modal {
                    overflow-x: hidden;
                    overflow-y: auto;
                  }
                  #confirm{
                  width: 90%;
                  background-color: #F7819F;
                   color: white;
                  padding: 5% 5%;
                  margin: 0 7%;
                  border: none;
                  border-radius: 4px;
                  cursor: pointer;
                  font-family: Helvetica;
                  font-size: 16px;
                  margin-right: 1%;
                  }
                  .modal {
                    position: fixed;
                    top: 0;
                    left: 0;
                    z-index: 1050;
                    display: none;
                    width: 100%;
                    height: 100%;
                    overflow: hidden;
                    outline: 0;
                  }
                  .modal-dialog {
                    position: relative;
                    width: auto;
                    margin: 0.5rem;
                    pointer-events: none;
                  }
                  .modal.fade .modal-dialog {
                    transition: -webkit-transform 0.3s ease-out;
                    transition: transform 0.3s ease-out;
                    transition: transform 0.3s ease-out, -webkit-transform 0.3s ease-out;
                    -webkit-transform: translate(0, -50px);
                    transform: translate(0, -50px);
                  }
                  .modal.show .modal-dialog {
                    -webkit-transform: none;
                    transform: none;
                  }
                  .modal-content {
                    position: relative;
                    display: -ms-flexbox;
                    display: flex;
                    -ms-flex-direction: column;
                    flex-direction: column;
                    width: 100%;
                    pointer-events: auto;
                    background-color: #fff;
                    background-clip: padding-box;
                    border: 1px solid rgba(0, 0, 0, 0.2);
                    border-radius: 0.3rem;
                    outline: 0;
                  }
                  .modal-header {
                    display: -ms-flexbox;
                    display: flex;
                    -ms-flex-align: start;
                    align-items: flex-start;
                    -ms-flex-pack: justify;
                    justify-content: space-between;
                    padding: 1rem 1rem;
                    border-bottom: 1px solid #dee2e6;
                    border-top-left-radius: 0.3rem;
                    border-top-right-radius: 0.3rem;
                  }
                  .modal-header .close {
                    background-color: #F7819F;
                    border: none;
                  }
                  .modal-title {
                    margin-bottom: 0;
                    line-height: 1.5;
                  }
                  .modal-body {
                    position: relative;
                    -ms-flex: 1 1 auto;
                    flex: 1 1 auto;
                    padding: 1rem;
                  }
                  .modal-footer {
                    display: -ms-flexbox;
                    display: flex;
                    -ms-flex-align: center;
                    align-items: center;
                    -ms-flex-pack: end;
                    justify-content: flex-end;
                    padding: 1rem;
                    border-top: 1px solid #dee2e6;
                    border-bottom-right-radius: 0.3rem;
                    border-bottom-left-radius: 0.3rem;
                  }
                  @media (min-width: 576px) {
                    .modal-dialog {
                      max-width: 500px;
                      margin: 1.75rem auto;
                    }
                  }

                  </style>
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
                                                               <li><a href="ViewUserHistoryServlet" title="View History"><i class="fas fa-history"></i></a></li>
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
                  <div id="listItemDetail">
                           <c:if test="${not empty requestScope.ITEM_DETAILS}">
                                    <c:set var="Item" value="${requestScope.ITEM_DETAILS}"/>
                                    <div id="item_image">
                                             <img src="Image/${Item.image}" alt="" id="img_big">
                                    </div>
                                    <form action="DispatchServlet" name="my_form" >
                                             <input type="hidden" name="txtImage" value="${Item.image}" />
                                             <div id="item_detail">
                                                      <p ><b id="name_item">${Item.itemName}</b></p>
                                                      <p><input type="hidden" name="txtItemID" value="${Item.itemID}" /></p>
                                                      <p>(${requestScope.CATEGORY_ID})</p>
                                                      <p>${Item.discription}</p>
                                                      <p><b>Price: ${Item.price}</b></p>
                                                      <c:if test="${not empty sessionScope.NAME}">
                                                      <c:if test="${not sessionScope.ROLE_OF_USER}">
                                                               <input type="hidden" name="txtQuantity" value="${Item.quantity}" id="maxQuantity" />
                                                               <input type="hidden" name="txtOderQuantity" value="1" />
                                                               <br><p><i class="fas fa-minus" onclick="minus()" id="minus"></i><span id="quantity_of_order">  1  </span><i class="fas fa-plus" onclick="plus()" id="plus"></i></p>
                                                               <p id="notice">The remaining number of this product is 0!!</p>
                                                               <div ><input type="submit" value="Add To Cart" name ="btnAction" id="Add_to_cart_button"/></div> 
                                                               </c:if>
                                                      <c:if test="${sessionScope.ROLE_OF_USER}">
                                                               <p>Quantity: ${Item.quantity}</p>
                                                               <p>Date Of Create: ${Item.createDate}</p>
                                                               <p>Status: 
                                                                        <c:if test="${Item.status}">
                                                                                 Active !
                                                                        </c:if>
                                                                        <c:if test="${not Item.status}">
                                                                                 Inactive!
                                                                        </c:if>
                                                               </p>
                                                               <input type="submit" value="Update" name ="btnAction" id="Add_to_cart_button"/>
                                                               <c:if test="${Item.status}">
                                                                       <button type="button" data-toggle="modal" data-target="#ConfirmModal" id="Add_to_cart_button">Delete</button>
                                                              </c:if>
                                                              <c:if test="${not Item.status}">
                                                                       <button type="button" id="Add_to_cart_button" style="background-color: gainsboro">Delete</button>
                                                              </c:if>
                                                               <div class="modal fade" id="ConfirmModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                               <div class="modal-dialog" role="document">
                                                                        <div class="modal-content">
                                                                                 <div class="modal-header">
                                                                                          <h5 class="modal-title" id="exampleModalLabel">Confirm</h5>
                                                                                          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                                   <span aria-hidden="true">&times;</span>
                                                                                          </button>
                                                                                 </div>
                                                                                 <div class="modal-body">
                                                                                          Do you really want to delete ${Item.itemName} ?
                                                                                 </div>
                                                                                 <div class="modal-footer">
                                                                                           <input type="submit" value="Delete" name ="btnAction" />
                                                                                           <input type="submit" value="Close"  name="btnAction" data-dismiss="modal"/>
                                                                                 </div>
                                                                        </div>
                                                               </div>
                                                      </div>       
                                                      </c:if>
                                                      </c:if>
                                                      <c:if test="${empty sessionScope.NAME}">
                                                                  <a href="login.jsp" id="Add_to_login">Please login to shopping!!!!</a>
                                                       </c:if>                                  
                                             </div>   
                                    </form>
                           </c:if>   
                  </div> 
                  <c:if test="${not empty sessionScope.NAME}">
                              <c:if test="${not sessionScope.ROLE_OF_USER}">
                                        <c:if test="${not empty requestScope.RECOMMEND_PRODUCT}">
                                       <b id="re_product">Recommend Product: </b>
                                             <div id="listItem">
                                                      <c:forEach var="Recommend_Item" items="${requestScope.RECOMMEND_PRODUCT}">
                                                               <form action="DispatchServlet">
                                                                                 <div id="item">
                                                                                          <img src="Image/${Recommend_Item.image}" alt="" id="img">
                                                                                          <input type="hidden" name="txtImage" value="${Recommend_Item.image}" />
                                                                                                   <p ><b id="name_of_item">${Recommend_Item.itemName}</b></p>
                                                                                                   <p><input type="hidden" name="txtItemID" value="${Recommend_Item.itemID}" /></p>
                                                                                                   <p><b>Price: ${Recommend_Item.price}</b></p>
                                                                                                   <p><input type="submit" value="View Details" name ="btnAction" id="view_detail"/></p>
                                                                                 </div> 
                                                               </form>
                                                      </c:forEach>
                                             </div>
                                 </c:if>   
                                    
                              </c:if>
</c:if> 
         <div id="foot">
                  <p><i class="fas fa-copyright" id="p"></i> 2021 Hana Sh<i class="fas fa-cookie-bite" id="pCookie"></i>p, by Thanh Vi.<br>
                           Contact me via <a href="https://www.facebook.com/merry.kute.31/"><i class="fab fa-facebook" id="p" ></i></a> or <a href="#"><i class="fas fa-envelope" id="p"></i></a></p>
         </div>
         <script>
                  var max_String = document.getElementById("maxQuantity").value;
                  var max_number = parseInt(max_String);
                  if (max_number === 1){
                                    document.getElementById("plus").style.color = "gainsboro";
                                    document.getElementById("minus").style.color = "gainsboro";
                                    document.getElementById("notice").style.display = "block";
                  }
                  function plus() {
                           var quantity_String = document.getElementById("quantity_of_order").innerHTML;
                           var quantity_number = parseInt(quantity_String);
                           var sum = quantity_number + 1;
                           if (sum < max_number) {
                                    document.getElementById("quantity_of_order").innerHTML = sum.toString();
                                    document.forms["my_form"]["txtOderQuantity"].value = sum.toString();
                                    document.getElementById("minus").style.color = "black";
                                    document.getElementById("notice").style.display = "none";
                           } else if (sum == max_number) {
                                    document.getElementById("quantity_of_order").innerHTML = sum.toString();
                                    document.forms["my_form"]["txtOderQuantity"].value = sum.toString();
                                    document.getElementById("plus").style.color = "gainsboro";
                                    document.getElementById("notice").style.display = "block";
                                    document.getElementById("minus").style.color = "black";
                           }
                  }
                  function minus() {
                           var min = 1;
                           var quantity_String = document.getElementById("quantity_of_order").innerHTML;
                           var quantity_number = parseInt(quantity_String);
                           var sum = quantity_number - 1;
                           if (sum > min) {
                                    document.getElementById("quantity_of_order").innerHTML = sum.toString();
                                    document.forms["my_form"]["txtOderQuantity"].value = sum.toString();
                                    document.getElementById("plus").style.color = "black";
                                    document.getElementById("notice").style.display = "none";
                           } else if (sum === min) {
                                    document.getElementById("quantity_of_order").innerHTML = sum.toString();
                                    document.getElementById("minus").style.color = "gainsboro";
                                    document.forms["my_form"]["txtOderQuantity"].value = sum.toString();
                                    document.getElementById("notice").style.display = "none";
                           }
                  }
         </script>
         <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
                  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
                  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
