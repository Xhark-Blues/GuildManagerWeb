<%@page contentType="text/html, charset=UTF-8"%>
<%@page import="java.util.*, model.*"%>

<%
  ArrayList<Product> products = (ArrayList<Product>)session.getAttribute("products");
  Cart cart = (Cart)session.getAttribute("cart");
  if( cart == null){
    ArrayList<Line> lines = new ArrayList<Line>();
    String userDNI = "35488513L";
    cart = new Cart(lines, userDNI, Calendar.getInstance().getTime());

    session.setAttribute("cart",cart);
  }
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
      <form method="post" action="Billvlet" class="storeForm">
        <div class="store">
          <%
          String pids = "";
          for(Product p:products) {%>
            <div class="product" onclick="selectProduct(this.id)">
              <input type="hidden" name="pids" value="<%= p.getId()%>"></input>
              <input type="hidden" name="names" value="<%= p.getName()%>"></input>
              <input type="hidden" name="prices" value="<%= p.getPrice()%>"></input>
              <img src="<%= p.getImgPath() %>"></img>
              <p class="name"><%= p.getName() %> </p>
              <div class="price">
                <p> <%= p.getPrice() + "€"%>  </p>
                <input type="number" name="cant"  value="<%= cart.getCantOf(p)%>" step="1" min="0" max="<%= p.getStock() %>"></input>
              </div>
            </div>
          <%
          pids += p.getId();
          } %>
        </div>
        <div class="submitCart">
          <input class="cartButon" type="submit" value="Añadir al carro" ></input>
        </div>
      </form>
    <section>
  </body>
</html>
