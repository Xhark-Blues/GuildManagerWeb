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
        <div class="bill">
          <h2>Factura</h2>
          <div class="billHead"><span>Producto</span><span>Precio </span><span>Unds.</span><span>Total</span><span>Total CON IVA</span></div>

            <%

            //Pedir carrito de la sesion:

            String[] cants = request.getParameterValues("cant");
            String[] pids = request.getParameterValues("pids");
            String[] names = request.getParameterValues("names");
            String[] prices = request.getParameterValues("prices");

            Double total = 0.0;
            for(int i=0;i< cants.length; i++){
              int cant = Integer.parseInt(cants[i]);

              Line line = new Line(dao.getProductById(Integer.parseInt(pids[i])), cant, 21);
              cart.addLine(line);
            }

            session.setAttribute("cart",cart);

            for(Line ln: cart.getLines()){
              double price = ln.getProduct().getPrice();
              int cant = ln.getCant();
              if( cant > 0) {
                total += price * cant;
                %>
                <div class="billRow">
                  <div class="billCell"><%= ln.getProduct().getName()%></div>
                  <div class="billCell"><%= price %></div>
                  <div class="billCell"><%= cant %></div>
                  <div class="billCell"><%= price * cant %></div>
                  <div class="billCell"><%= String.format("%.2f",(price*0.21+price)*cant) %></div>
                </div>
              <%}%>
            <%
            } %>

            <div class="billBottom"><span>Total</span><span></span><span></span><span><%= String.format("%.2f",total) %></span><span><%= String.format("%.2f",total*0.21+total) %></span></div>
        </div>
        <div class="billButtons">
          <a href="Storvlet"> Store </a>
          <a class="cartButon" href="Confvlet"> Confirmar compra</a>
        </div>
    <section>
  </body>
</html>
