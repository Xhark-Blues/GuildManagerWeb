<%@page contentType="text/html, charset=UTF-8"%>
<%@page import="java.util.*, model.*"%>

<%
  final String USR="martinrubiofernandez";
  final String PAS="etse";
  DAO dao = null;
  try{
    dao = new DAO(USR, PAS);
  }catch(Exception ex){}
  Cart cart = (Cart)session.getAttribute("cart");

  dao.insertSale(cart);
  cart = null;
  session.setAttribute("cart", cart);
 %>

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
            <h3> Nombre: <%= ((User)session.getAttribute("log")).getName() %> </h3>
          </div>
        </div>
    </header>
    <body>
      <section class="main">
        <div class="confirmation">
          <p> Compra realizada con exito</p>
          <a href="Storvlet"> Atras</a>
        </div>
        <section>
      </body>
    </html>
