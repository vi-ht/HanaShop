<%-- 
    Document   : viewcart
    Created on : Jan 6, 2021, 9:03:58 AM
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
                  <title>View Cart Page</title>
                  <style>
                  .modal-open .modal {
                    overflow-x: hidden;
                    overflow-y: auto;
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
                  <title>View Cart Page</title>
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
                  <c:if test="${not empty requestScope.FAIL}">
                           <script>
                                     window.alert("Oh no, the remaining number of ${requestScope.SOLD_OUT_PRODUCT} is ${requestScope.REMAINING_NUMBER}!!");
                           </script>
                  </c:if>
                  <c:if test="${not empty requestScope.SOLD_OUT}">
                           <script>
                                     window.alert("Oh no, product ${requestScope.SOLD_OUT_PRODUCT} in your cart sold out!!");
                           </script>
                  </c:if>
                  <c:if test="${not empty requestScope.REMOVE_SUCCESS}">
                           <script>
                                    window.alert("Remove Successfully!");
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
                                                               <li><a href="ViewCartServlet" title="Shopping cart"  style="border-bottom: 3px solid black; background: #ffffff;"><i class="fas fa-shopping-bag"></i></a></li>
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
                  
                  <c:if test="${not empty sessionScope.CUST_CART}">
                           <c:if test="${not empty requestScope.LIST_ITEM_IN_CART}">
                                    <form action="DispatchServlet" id="viewcart_form" name="viewcartForm">
                                             <table border="1" id="tb">
                                                      <thead>
                                                               <tr>
                                                                        <th>No.</th>
                                                                        <th>Image</th>
                                                                        <th>Name</th>
                                                                        <th>Quantity</th>
                                                                        <th>Price</th>
                                                                        <th>Total Price</th>
                                                                        <th>Remove</th>
                                                               </tr>
                                                      </thead>
                                                      <tbody>
                                                               <c:forEach var="cart" items="${requestScope.LIST_ITEM_IN_CART}" varStatus="counter">
                                                                        <tr>
                                                                                 <td>${counter.count}</td>
                                                                                 <td><div id="item_imagecart">
                                                                                                   <img src="Image/${cart.image}" id="imgCart" />
                                                                                          </div></td>
                                                                                 <td>${cart.itemName}</td>
                                                                                 <td>
                                                                                          <c:url var="addQuantity" value="DispatchServlet">
                                                                                                   <c:param name="btnAction" value="addQuantity"/>
                                                                                                   <c:param name="txtItemID" value="${cart.itemID}"/>
                                                                                                   <c:param name="txtIProductName" value="${cart.itemName}"/>
                                                                                                   <c:param name="txtOderQuantity" value="${cart.quantityOder}"/> 
                                                                                          </c:url>
                                                                                          <c:url var="subtractQuantity" value="DispatchServlet">
                                                                                                   <c:param name="btnAction" value="subtractQuantity"/>
                                                                                                   <c:param name="txtItemID" value="${cart.itemID}"/>
                                                                                                   <c:param name="txtIProductName" value="${cart.itemName}"/>
                                                                                                   <c:param name="txtOderQuantity" value="${cart.quantityOder}"/> 
                                                                                          </c:url>
                                                                                          <c:if test="${(cart.quantityOder == cart.quantity) && (cart.quantity>1)}">
                                                                                                   
                                                                                                   <p><a href="${subtractQuantity}"><i class="fas fa-minus" id="minus" style="color:black"></i></a>
                                                                                                   <span id="quantity_of_order"> ${cart.quantityOder} </span>
                                                                                                   <i class="fas fa-plus" id="plus" style="color:gainsboro"></i></p>
                                                                                          </c:if>
                                                                                          <c:if test="${(cart.quantityOder == 1)&&(cart.quantity>1)}">
                                                                                          
                                                                                                   <p><i class="fas fa-minus" id="minus" style="color:gainsboro"></i>
                                                                                                   <span id="quantity_of_order"> ${cart.quantityOder} </span>
                                                                                                   <a href="${addQuantity}"><i class="fas fa-plus" id="plus" style="color:black"></i></a></p>
                                                                                          </c:if>
                                                                                          <c:if test="${(cart.quantityOder == cart.quantity) && (cart.quantity==1)}">
                                                                                                   
                                                                                                   <p><i class="fas fa-minus" id="minus" style="color:gainsboro"></i>
                                                                                                   <span id="quantity_of_order"> ${cart.quantityOder} </span>
                                                                                                   <i class="fas fa-plus" id="plus" style="color:gainsboro"></i></p>
                                                                                          </c:if>
                                                                                          <c:if test="${(cart.quantityOder < cart.quantity) && (cart.quantityOder > 1)}">
                                                                                                   <p><a href="${subtractQuantity}"><i class="fas fa-minus" id="minus" style="color:black"></i></a>
                                                                                                   <span id="quantity_of_order"> ${cart.quantityOder} </span>
                                                                                                   <a href="${addQuantity}"><i class="fas fa-plus" id="plus" style="color:black"></i></a></p>
                                                                                          </c:if>
                                                                                 </td>
                                                                                 <td>${cart.price}</td>
                                                                                 <td>${cart.price*cart.quantityOder}</td>
                                                                                 <td><input type="checkbox" name="chkItem" value="${cart.itemID}" /></td>
                                                                        </tr>
                                                               </c:forEach>    
                                                               <tr>
                                                                        <td colspan="3" >Total</td>
                                                                        <td>${requestScope.TOTAL_QUANTITY}</td>
                                                                        <td> </td>
                                                                        <td>
                                                                                 ${requestScope.TOTAL_PRICE}
                                                                        </td>
                                                                        <td>
                                                                                 <button type="button" data-toggle="modal" data-target="#ConfirmModal" id="remove">Remove</button>
                                                                        </td>
                                                               </tr>
                                                      </tbody>
                                             </table>
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
                                                                Do you really want to delete product in your cart?
                                                       </div>
                                                       <div class="modal-footer">
                                                                 <input type="submit" value="Remove"  name="btnAction" />
                                                                 <input type="submit" value="Close"  name="btnAction" data-dismiss="modal"/>
                                                       </div>
                                              </div>
                                     </div>
                            </div>
                            </form>
                            <form action="DispatchServlet" id="checkout_form" name="checkoutForm">        
                            <input type="hidden" name="txtTotalQuantity" value="${requestScope.TOTAL_QUANTITY}" />
                            <input type="hidden" name="txtTotalPrice" value="${requestScope.TOTAL_PRICE}" />
                            <input type="hidden" name="txtOrderDate" value="${param.txtOrderDate}" />
                            <input type="hidden" name="txtOrderTime" value="${param.txtOrderTime}" />
                            <div class="display_in_viewcart_form_big">
                                     <b id="payment_title">Shipment Details</b><br><br>
                                     <b>Name: </b><input type="text" name="txtCustName" value="${sessionScope.NAME}" placeholder="Please input your name, name's max length is 50." required maxlength="50"/>
                                     <b>Phone number: </b><input type="tel" id="phone" name="txtCustPhone" value="${param.txtCustPhone}" placeholder="(+84)123456789." pattern="[\+]84\d{9}" required><br>
                                     <b>Adress: </b><input type="text" name="txtCustAddress" value="${param.txtCustAddress}" placeholder="123, Lien Phuong street, district 9, HCN city." required/>                                   
                           </div>  
                           <div class="display_in_viewcart_form_small">
                                    <b id="payment_title">Payment Methods</b><br><br>
                                    - Payment on delivery
                                    <br><br><br><br><br><br><br><br><br>
                                    <input type="submit" value="Check out"  name="btnAction" id="checkout"/>
                           </div> 
                           </form>
                           </c:if>
                           <c:if test="${empty requestScope.LIST_ITEM_IN_CART}">
                                    <div id="invalid">
                                             <h1>No product in your cart!!!! <i class="far fa-laugh-squint"></i></h1><br>
                                             <a href="ShoppingServlet" id="login_Error">Click here to go to shopping page</a>
                                    </div>
                           </c:if>
                  </c:if>
                  <c:if test="${empty sessionScope.CUST_CART}">
                           <div id="invalid">
                                    <h1>No product in your cart!!!! <i class="far fa-laugh-squint"></i></h1><br>
                                    <a href="ShoppingServlet" id="login_Error">Click here to go to shopping page</a>
                           </div>
                  </c:if>
                  <div id="foot">
                           <p><i class="fas fa-copyright" id="p"></i> 2021 Hana Sh<i class="fas fa-cookie-bite" id="pCookie"></i>p, by Thanh Vi.<br>
                                    Contact me via <a href="https://www.facebook.com/merry.kute.31/"><i class="fab fa-facebook" id="p" ></i></a> or <a href="#"><i class="fas fa-envelope" id="p"></i></a></p>
                  </div>
                  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
                  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
                  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
         </body>
</html>
