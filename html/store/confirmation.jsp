<%@page contentType="text/html, charset=UTF-8"%>
<%@page import="java.util.*, model.*"%>


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
            <h3> <a href="Cartvlet"> ${ log.getName()} </a> </h3>
          </div>
        </div>
    </header>
    <body>
      <section class="main">
        <div class="confirmation">
          <h2> Compra realizada con exito</h2>
          <form action="Billvlet" method="post">
                <div class="dir"><label>Dirección de envio:</label><input type="text" name="sentAddress" width="200" placeholder="Direccion de envio" required="required"></div>
                <div class="dir"><label>Dirección de facturación:</label><input type="text" name="billAddress" width="200"  placeholder="Direccion de facturacion"></div>
            <div class="confButtons">
              <a href="Cartvlet"> Atras </a>
              <input class="cartButon" type="submit" value="Completar Compra" ></input>
            </div>
          </form>
        </div>
      <section>
    </body>
  </html>
