<%@page contentType="text/html"%>
<%@page import="java.util.*, model.*"%>

<html>
  <head>
    <meta charset="UTF-8">
    <meta name="keywords" content="Gw, playercommunity, api access, gw2 api, guild wars, player, character, achievements, guild">
    <meta name="description" content="Panel with the informnation of every character of the account">
    <meta name="author" content="Martin Rubio">

    <link type="text/css" rel="stylesheet" href="/GMW/css/main.css"/>
    <link type="text/css" rel="stylesheet" href="/GMW/css/characters.css"/>

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.js"></script>

    <script src="../../jQuery/jquery-3.1.1.js"></script>
    <script src="../../jQuery/dropdown.js"></script>

    <title>Characters</title>
  </head>


  <body ng-app="characters">
    <header>
        <a href="characterPanel.html"><img src="/GMW/imgs/logo.png"></a>
        <h1>GUILD MANAGER WEB</h1>
        <div class="iconCTN">
          <div class="icons">
            <a href="https://github.com/Xhark-Blues/GuildManagerWeb" target="_blank"><img src="/GMW/imgs/github.png"></a>
            <a href="https://www.guildwars2.com/es/" target="_blank"><img src="/GMW/imgs/gw2.png"></a>
          </div>
          <div>
            <h3><a href="/GMW/Storvlet"> Store </a></h3>
          </div>
          <div>
            <h3> <a href="../../index.jsp"> Cerrar Sesion </a></h3>
          </div>
          <div>
            <h3> Nombre: <%= ((User)session.getAttribute("log")).getName() %> </h3>
          </div>
        </div>

    </header>

    <nav><!-- Control Panels Navigation bar -->
      <ul>
        <li class="actual"><a href="characterPanel.html">Characters</a></li>
        <li><a href="/GMW/html/achivementsPanel.html">Achivements</a></li>
        <li class="dropBtn"><a href="#">Guild</a>
          <ul class="dropContent">
            <li><a href="/GMW/html/guild/guildMembersPanel.html">Guild1</a></li>
            <li><a href="/GMW/html/guild/guildMembersPanel.html">Guild2</a></li>
            <li><a href="/GMW/html/guild/guildMembersPanel.html">Guild3</a></li>
          </ul>
        </li>
      </ul>
    </nav>

    <section class="main" ng-controller="CharController">
     <aside><!-- List of characters  (D)-->
       <h2 >Characters</h2>
       <ul >
         <li class="menuitem" ng-repeat="char in chars" ng-click="selectChar($index)">
           <img width="40px"  ng-src="imgs/profesions/{{ char.profession | lowercase }}.png" >
           <a id="{{ char.name }}"  href="#char1">{{ char.name }}</a>
         </li>
       </ul>
     </aside>

     <div class="content ng-view"></div>
</section>


    <script src="/GMW/angular/services/dao.js"></script>
    <script src="/GMW/angular/controllers/CharController.js"></script>
    <script src="/GMW/angular/guildManager.js"></script>

  </body>
</html>
