<%@page contentType="text/html, charset=UTF-8"%>
<%@page import="java.util.*, model.*"%>

<%
  final String USR="martinrubiofernandez";
  final String PAS="etse";
  DAO dao = null;
  try{
    dao = new DAO(USR, PAS);
  }catch(Exception ex){}
 %>

<%
  String log = request.getParameter("log");
  String psswd = request.getParameter("psswd");

  if(log == null){%>
  <html>
    <head>
      <meta charset="UTF-8">
      <meta name="keywords" content="Gw, playercommunity, api access, gw2 api, guild wars, player, character, achievements, guild">
      <meta name="description" content="Web service that allow the users to view and organize information about Guild Wars 2 in order to give them tools to improve their performance in end-game.">
      <meta name="author" content="Martin Rubio">

      <link type="text/css" rel="stylesheet" href="css/login.css"/>

      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.js"></script>

      <title>Login</title>
      </head>
    <body>
      <form action="index.jsp" method="post">
        <input name="log" type="text" placeholder="User name" required="required"/>
        <input name="psswd" type="password" placeholder="********" required="required"/>
        <div class="itemContent">
          <a id="signup" href="html/signup.html">Sign up</a>
          <input id="signin" type="submit" value="Sing in"/>
        </div>
        <p>23/10/2016</p>
        <a href="html/query.html">Encuesta</a>
      </form>
    </body>
  </html>
  <%}else{
    if(dao.logIn(log,psswd)){
      System.out.println("++++++++2"+log+"  "+psswd);
      User user = dao.getUserByDNI(log);
      session.setAttribute("log",user);

      RequestDispatcher dispatcher = request.getRequestDispatcher("html/characters/characterPanel.jsp");
      dispatcher.forward(request,response);
    }else{%>
      <html>
        <head>
          <meta charset="UTF-8">
          <meta name="keywords" content="Gw, playercommunity, api access, gw2 api, guild wars, player, character, achievements, guild">
          <meta name="description" content="Web service that allow the users to view and organize information about Guild Wars 2 in order to give them tools to improve their performance in end-game.">
          <meta name="author" content="Martin Rubio">

          <link type="text/css" rel="stylesheet" href="css/login.css"/>

          <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
          <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.js"></script>

          <title>Login</title>
          </head>
        <body>
          <form action="index.jsp">
            <input name="log" type="text" placeholder="User name" required="required"/>
            <input name="psswd" type="password" placeholder="********" required="required"/>
            <p>Credenciales Incorrectas.</p>
            <div class="itemContent">
              <a id="signup" href="html/signup.html">Sign up</a>
              <input id="signin" type="submit" value="Sing in"/>
            </div>
            <p>23/10/2016</p>
            <a href="html/query.html">Encuesta</a>
          </form>

        </body>
      </html>
    <%}
  }
%>
