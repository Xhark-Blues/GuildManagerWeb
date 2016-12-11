<%@page contentType="text/html, charset=UTF-8"%>
<%@page import="java.util.*, model.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>


<html>
  <jsp:include page="../head.jsp"/>

  <header>
    <a href="html/characters/characterPanel.jsp"><img src="imgs/logo.png"></a>
      <h1>GUILD MANAGER WEB</h1>
      <div class="iconCTN">
        <div class="icons">
          <a href="https://github.com/Xhark-Blues/GuildManagerWeb" target="_blank"><img src="imgs/github.png"></a>
          <a href="https://www.guildwars2.com/es/" target="_blank"><img src="imgs/gw2.png"></a>
        </div>
        <div>
          <h3><h3><a href="Storvlet"> Store </a></h3></h3>
        </div>
        <div>
          <h3> <a href="index.jsp"> Cerrar Sesion </a></h3>
        </div>
        <div>
          <h3> <a href="Cartvlet"> ${ log.getName() } </a> </h3>
        </div>
      </div>

  </header>

  <body>
    <section class="main">
        <div class="bill">
          <h2>Factura</h2>
          <fmt:setLocale value="es_ES"/>
          <c:set var="total" scope="page" value="${0.0}"/>
          <div class="billHead">
            <div class="clientData">
              <h3>Cliente: ${ log.getName() } </h3>
              <h3>NIF: ${ log.getDNI() } </h3>
              <h4>Dirección de envio: ${ sentAddress }</h4>
              <c:if test="empty billAddress">
                <c:set var="billAddress" scope="page" value="${ sentAddress }"/>
              </c:if>
              <h4>Dirección de facturacion: ${ billAdress }</h4>
            </div>
            <div class="storeData">
                <h3>GMW Store</h3>
                <h4>CIF:  "B73347494"</h4>
                <h4> Número de factura: ${ nSale } </h4>
            </div>
          </div>
          <div class="billTableHead">
            <span>Producto</span><span>Precio </span><span>Unds.</span><span>Total</span><span>Total CON IVA</span>
          </div>

            <c:forEach var="ln" items="${cart.getLines()}">
              <c:set var="cant" scope="page" value="${ln.getCant()}"/>
              <c:set var="price" scope="page" value="${ln.getProduct().getPrice()}"/>

              <c:if test="${ cant > 0}">
                <c:set var="total" scope="page" value="${total + price * cant}"/>
                <div class="billRow">
                    <div class="billCell">${ ln.getProduct().getName() } </div>
                    <div class="billCell">${ price } </div>
                    <div class="billCell">${ cant } </input></div>
                    <div class="billCell"> <fmt:formatNumber value="${ price * cant }" type="currency"/> </div>
                    <div class="billCell"> <fmt:formatNumber value="${ (price*0.21+price)*cant }" type="currency"/> </div>
                </div>
              </c:if>

            </c:forEach>

          <div class="billBottom"><span>Total</span><span></span><span></span><span><fmt:formatNumber value="${ total }" type="currency"/> </span><span><fmt:formatNumber value="${ (total*0.21+total) }" type="currency"/></span></div>
        </div>
        <div class="billButtons">
          <a href="Storvlet"> Volver a la tienda </a>
        </div>
    <section>
  </body>
</html>
