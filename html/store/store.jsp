<%@page contentType="text/html, charset=UTF-8"%>
<%@page import="java.util.*, model.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false" %>

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
          <h4><a href="Storvlet"> Store </a></h4>
        </div>
        <div>
          <h4> <a href="index.jsp"> Cerrar Sesion </a></h4>
        </div>
        <div>
          <h3> <a href="Cartvlet"> ${ log.getName()} </a> </h3>
        </div>
      </div>

  </header>

  <body>
    <section class="main">
      <form method="post" action="Cartvlet" class="storeForm">
        <div class="store">

          <c:forEach var="p" items="${products}">
            <div class="product">
              <input type="hidden" name="pids" value="${ p.getId() }"></input>
              <input type="hidden" name="names" value="${ p.getName() }"></input>
              <input type="hidden" name="prices" value="${ p.getPrice() }"></input>
              <img src= "${p.getImgPath()}"></img>
              <p class="name"> ${ p.getName() } </p>
              <div class="price">
                <p> ${ p.getPrice()}${"€"} </p>
                <input type="number" name="cant"  value="0" step="1" min="0" max="${ p.getStock() - cart.getCantOf(p) }"></input>
              </div>
            </div>
          </c:forEach>
        </div>
        <div class="submitCart">
          <input class="cartButon" type="submit" value="Añadir al carro" ></input>
        </div>
      </form>
    <section>
  </body>
</html>
